package com.digital.signage.service.impl

import com.digital.signage.database.aspectRatioUsesCountQuery
import com.digital.signage.context.TenantContext
import com.digital.signage.database.parser.AspectRatioUsesCountResultParser
import com.digital.signage.dto.AspectBulkDeleteRequestDTO
import com.digital.signage.dto.AspectRatioCalculateDTO
import com.digital.signage.dto.AspectRatioDefaultWH
import com.digital.signage.dto.AspectRatioRequestDto
import com.digital.signage.util.ApplicationConstants.ASPECT_RATIO_DEFAULT_CONSTANT_DIVIDER
import com.digital.signage.util.Message.AspectRatio.*
import com.digital.signage.models.AspectRatio
import com.digital.signage.validators.impl.aspectRatioCalculateValidation
import com.digital.signage.validators.impl.parseAspectRatioForCalculation
import com.digital.signage.models.CommonMultiActionResultObject
import com.digital.signage.models.FieldMessage
import com.digital.signage.models.Response
import com.digital.signage.models.ResponseExt
import com.digital.signage.models.ValidationErrorResponse
import com.digital.signage.repository.AspectRatioRepository
import com.digital.signage.service.AspectRatioService
import com.digital.signage.util.Status
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.ResultSetExtractor
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.math.RoundingMode
import javax.sql.DataSource

/**
 * @author -Ravi Kumar created on 1/23/2023 4:22 PM
 * @project - Digital Sign-edge
 */
@Service
class AspectRatioServiceImpl : BaseServiceImpl(), AspectRatioService {

    @Autowired
    private lateinit var aspectRatioRepository: AspectRatioRepository

    @Autowired
    private lateinit var dataSource: DataSource

    override fun getAspectRatiosForApi(): Response<List<AspectRatio>>? = ResponseExt(
        result = findAspectRatioFromDb()
    )

    override fun getAspectRatios(): List<AspectRatio>? = findAspectRatioFromDb()

    override fun getAspectRatioById(aspectRatioId: Long): AspectRatio? = findAspectRatioById(
        aspectRatioId
    )

    override fun calculateAspectRatio(
        aspectRatioCalculateDTO: AspectRatioCalculateDTO?
    ): Response<out Any?> {
        val (height, width) = parseAspectRatioForCalculation(aspectRatioCalculateDTO)
        val validationResponse = aspectRatioCalculateValidation(
            height = height,
            width = width,
            message = message
        )

        if (validationResponse != null) {
            return validationResponse
        }

        val aspectRationResult = calculateAspectRatio(
            height = height!!,
            width = width!!
        )

        return ResponseExt(
            result = aspectRationResult,
            httpStatusCode = 200,
            message = "AspectRatioCalculated",
            name = "Aspect Ratio calculated"
        )
    }

    override fun calculateAspectRatio(width: Long, height: Long): String {
        val gsdData = getGCD(width, height)
        return "${width / gsdData}:${height / gsdData}"
    }

    private fun getGCD(width: Long, height: Long): Long {
        return if (height == 0L)
            width
        else
            getGCD(height, width % height)
    }

    override fun addCustomAspectRatio(
        aspectRatioRequestDto: AspectRatioRequestDto
    ): Response<out Any?> {
        val customerId = TenantContext.getCustomerId()
        //check if customer is basic then throw not support response
        if (TenantContext.isCurrentCustomerIsBasic()) {
            return Response.notSupportedForBasicCustomer(message)
        }

        validateAddOrUpdateAspectRatio(aspectRatioRequestDto, null)?.takeIf { return it }
        val (actualHeightInPixel, actualWidthInPixel) = parseAndValidateAspectRatio(
            aspectRatioRequestDto
        )
        val defaultValues = calculateDefaultDimension(aspectRatioRequestDto)
        val aspectRatio = AspectRatio(
            customerId = customerId,
            aspectRatio = aspectRatioRequestDto.aspectRatio,
            actualHeightInPixel = actualHeightInPixel,
            actualWidthInPixel = actualWidthInPixel,
            defaultHeightInPixel = defaultValues.defaultHeight,
            defaultWidthInPixel = defaultValues.defaultWidth
        )
        aspectRatioRepository.save(aspectRatio)
        return ResponseExt(message = "Aspect ratio added")
    }

    override fun updateCustomAspectRatio(
        aspectRatioRequestDto: AspectRatioRequestDto,
        aspectRatioId: Long
    ): Response<out Any?> {
        //check if customer is basic then throw not support response
        if (TenantContext.isCurrentCustomerIsBasic()) {
            return Response.notSupportedForBasicCustomer(message)
        }

        val aspectFromDb = findAspectRatioById(aspectRatioId)
            ?: return idNotFoundErrorResponse(aspectRatioId)
        //check is editable
        if (aspectFromDb.isDefaultAspectRatio) {
            return ValidationErrorResponse(
                "DefaultAspectRatio",
                message[ASPECT_RATIO_EDITABLE_ERROR]
            )
        }
        if (!aspectFromDb.isEditable) {
            return ValidationErrorResponse(
                "NotEditable",
                message[ASPECT_RATIO_EDITABLE_ERROR]
            )
        }
        validateAddOrUpdateAspectRatio(aspectRatioRequestDto, aspectRatioId)?.takeIf {
            return it
        }
        val (actualHeightInPixel, actualWidthInPixel) = parseAndValidateAspectRatio(
            aspectRatioRequestDto
        )
        val defaultValues = calculateDefaultDimension(aspectRatioRequestDto)
        aspectFromDb.aspectRatio = aspectRatioRequestDto.aspectRatio
        aspectFromDb.actualHeightInPixel = actualHeightInPixel
        aspectFromDb.actualWidthInPixel = actualWidthInPixel
        aspectFromDb.defaultHeightInPixel = defaultValues.defaultHeight
        aspectFromDb.defaultWidthInPixel = defaultValues.defaultWidth
        aspectRatioRepository.save(aspectFromDb)
        return ResponseExt(message = "Aspect ratio edited")
    }

    override fun getAspectRatioByIdForApi(
        aspectRatioId: Long
    ): Response<out Any>? {
        val aspectRatio = findAspectRatioById(aspectRatioId)
            ?: return idNotFoundErrorResponse(aspectRatioId)
        return ResponseExt(result = aspectRatio)
    }

    override fun deleteAspectRatioByIdForApi(aspectRatioId: Long): Response<out Any>? {
        //check if customer is basic then throw not support response
        if (TenantContext.isCurrentCustomerIsBasic()) {
            return Response.notSupportedForBasicCustomer(message)
        }

        val aspectRatioForDelete = findAspectRatioById(aspectRatioId)
            ?: return idNotFoundErrorResponse(aspectRatioId)
        if (!aspectRatioForDelete.isEditable) {
            return ValidationErrorResponse(
                "AlreadyInUse",
                message[ASPECT_RATIO_EDITABLE_DELETE_ERROR]
            )
        }
        if (aspectRatioForDelete.isDefaultAspectRatio) {
            return ValidationErrorResponse(
                "defaultAspectRatio",
                message[ASPECT_RATIO_DEFAULT_DELETE_ERROR]
            )
        }
        aspectRatioForDelete.status = Status.DELETED
        aspectRatioRepository.save(aspectRatioForDelete)
        return ResponseExt(message = "Aspect ratio deleted successfully")
    }

    override fun deleteAspectRatioInBulk(dto: AspectBulkDeleteRequestDTO): Response<out Any>? {
        //check if customer is basic then throw not support response
        if (TenantContext.isCurrentCustomerIsBasic()) {
            return Response.notSupportedForBasicCustomer(message)
        }

        val aspectRatios = if (dto.aspectRatioIds.isNullOrEmpty()) emptyList() else
            findAspectRatioFromDb(dto.aspectRatioIds)
        val foundInDbIds = aspectRatios.map { t -> t.aspectRatioId!! }
        val badRequestIds = mutableListOf<FieldMessage>()
        val toBeDeletedAspectRatios = mutableListOf<AspectRatio>()
        val notFoundIds = dto.aspectRatioIds.filter { !foundInDbIds.contains(it) }
        for (it in aspectRatios) {
            if (!it.isEditable) {
                badRequestIds.add(
                    FieldMessage(
                        it.aspectRatioId,
                        message[ASPECT_RATIO_EDITABLE_DELETE_ERROR]
                    )
                )
            } else if (it.isDefaultAspectRatio) {
                badRequestIds.add(
                    FieldMessage(
                        it.aspectRatioId,
                        message[ASPECT_RATIO_DEFAULT_DELETE_ERROR]
                    )
                )
            } else {
                toBeDeletedAspectRatios.add(it)
            }
        }
        if (badRequestIds.isEmpty() && notFoundIds.isEmpty()) {
            // delete only if bad request and not found is empty
            toBeDeletedAspectRatios.parallelStream().forEach { it.status = Status.DELETED }
            aspectRatioRepository.saveAll(toBeDeletedAspectRatios)
        } else {
            toBeDeletedAspectRatios.removeAll { true }
        }

        val multipleResponse = CommonMultiActionResultObject()
        multipleResponse.notFound = notFoundIds
        multipleResponse.success = toBeDeletedAspectRatios.map { it.aspectRatioId }
        multipleResponse.badRequest = badRequestIds
        return ResponseExt(result = multipleResponse)
    }

    fun roundOffToLong(bigDecimal: BigDecimal): Long = bigDecimal
        .setScale(0, RoundingMode.HALF_UP)
        .toLong()

    fun roundOffToTwoDecimalPlaces(bigDecimal: BigDecimal): BigDecimal = bigDecimal
        .setScale(2, RoundingMode.HALF_UP)

    fun calculateDefaultDimension(dto: AspectRatioRequestDto): AspectRatioDefaultWH {
        // get aspect ratio first
        val aspectRatios = dto.aspectRatio!!.split(":").map { s -> s.toInt() }

        if (dto.actualHeightInPixel!! == dto.actualWidthInPixel!!) {
            return AspectRatioDefaultWH(
                ASPECT_RATIO_DEFAULT_CONSTANT_DIVIDER.toLong(),
                ASPECT_RATIO_DEFAULT_CONSTANT_DIVIDER.toLong(),
                "$ASPECT_RATIO_DEFAULT_CONSTANT_DIVIDER",
                "$ASPECT_RATIO_DEFAULT_CONSTANT_DIVIDER"
            )
        }

        //find large pixels
        val aspectRatioW: Int = aspectRatios.first()
        val aspectRatioH: Int = aspectRatios.last()

        // check if aspect ratio is greater than the max value
        if (aspectRatioH > ASPECT_RATIO_DEFAULT_CONSTANT_DIVIDER
            || aspectRatioW > ASPECT_RATIO_DEFAULT_CONSTANT_DIVIDER
        ) {
            // then make sure both are less than or equal to 800
            val defH: BigDecimal
            val defW: BigDecimal
            if (aspectRatioW < aspectRatioH) {
                defH = ASPECT_RATIO_DEFAULT_CONSTANT_DIVIDER.toBigDecimal()
                defW =
                    ((1.0 * aspectRatioW * ASPECT_RATIO_DEFAULT_CONSTANT_DIVIDER) / aspectRatioH).toBigDecimal()
                println("defW:defH = $defW:$defH")
            } else {
                defW = ASPECT_RATIO_DEFAULT_CONSTANT_DIVIDER.toBigDecimal()
                defH =
                    ((1.0 * aspectRatioH * ASPECT_RATIO_DEFAULT_CONSTANT_DIVIDER) / aspectRatioW).toBigDecimal()
                println("defW:defH = $defW:$defH")
            }
            return AspectRatioDefaultWH(
                roundOffToLong(defW),
                roundOffToLong(defH),
                roundOffToTwoDecimalPlaces(defW).toPlainString(),
                roundOffToTwoDecimalPlaces(defH).toPlainString()
            )
        }

        val isWidthLarger: Boolean =
            dto.actualHeightInPixel!!.toLong() < dto.actualWidthInPixel!!.toLong()

        val defaultWidth: Int
        val defaultHeight: Int
        val multiplyingFactor: Int

        if (isWidthLarger) {
            multiplyingFactor = ASPECT_RATIO_DEFAULT_CONSTANT_DIVIDER.div(aspectRatioW)
            defaultWidth = multiplyingFactor * aspectRatioW
            defaultHeight = multiplyingFactor * aspectRatioH
        } else {
            multiplyingFactor = ASPECT_RATIO_DEFAULT_CONSTANT_DIVIDER.div(aspectRatioH)
            defaultWidth = multiplyingFactor * aspectRatioW
            defaultHeight = multiplyingFactor * aspectRatioH
        }

        return AspectRatioDefaultWH(
            defaultWidth.toLong(),
            defaultHeight.toLong(),
            "$defaultWidth",
            "$defaultHeight"
        )
    }

    private fun findAspectRatioFromDb(
        aspectRatioIds: List<Long>? = null
    ): List<AspectRatio> {
        val customerIdsForAspect = setOf(
            TenantContext.getCustomerId(),
            CUSTOMER_ID_FOR_DEFAULT_ASPECT_RATIO
        )
        // Note:- aspectRatioIds will be come as null from get all api. please be ensure before changing null and empty check logic.
        val allAspectRatios = if (aspectRatioIds == null) {
            aspectRatioRepository.findAllAspectRatios(customerIdsForAspect)
        } else {
            aspectRatioRepository.findAllByAspectRatioId(aspectRatioIds, customerIdsForAspect)
        }
        setIsEditable(allAspectRatios)
        return allAspectRatios
    }

    private fun findAspectRatioById(aspectRatioId: Long): AspectRatio? {
        val aspectRatios = findAspectRatioFromDb(aspectRatioIds = listOf(aspectRatioId))
        return if (aspectRatios.isNullOrEmpty()) null else aspectRatios.first()
    }

    private fun setIsEditable(aspectRatios: List<AspectRatio>) {
        if (aspectRatios.isNullOrEmpty()) {
            return
        }
        val aspectRatioIds = aspectRatios
            .filter { !it.isDefaultAspectRatio }
            .map { it.aspectRatioId!! }
            .toList()
        if (aspectRatioIds.isEmpty()) {
            aspectRatios.forEach { it.isEditable = false }
            return
        }
        val aspectUseCountMap: HashMap<Long, Long>
        val paramMap: HashMap<String, List<Long>> = HashMap()
        val paramName = "aspectIds"
        paramMap[paramName] = aspectRatioIds
        aspectUseCountMap = NamedParameterJdbcTemplate(dataSource).query(
            aspectRatioUsesCountQuery(paramName),
            paramMap,
            AspectRatioUsesCountResultParser()
        )
        aspectRatios.forEach {
            it.isEditable = !(
                    it.isDefaultAspectRatio
                            || (
                            aspectUseCountMap != null
                                    && aspectUseCountMap.containsKey(it.aspectRatioId)
                            )
                    )
        }
    }

    private fun validateAddOrUpdateAspectRatio(
        dto: AspectRatioRequestDto,
        aspectRatioIdInPutRequest: Long?
    ): ValidationErrorResponse? {
        val customerIdsForAspect = setOf(
            TenantContext.getCustomerId(),
            CUSTOMER_ID_FOR_DEFAULT_ASPECT_RATIO
        )
        if (dto.aspectRatio.isNullOrBlank() || dto.aspectRatio!!.trim().contains(" ")) {
            return ValidationErrorResponse(
                "aspectRatio",
                message.get(ASPECT_RATIO_INVALID_ERROR, dto.aspectRatio)
            )
        }

        dto.aspectRatio = dto.aspectRatio!!.trim()

        val aspectRatioCountFromDb = if (aspectRatioIdInPutRequest != null) {
            aspectRatioRepository.countByAspectRatioExceptOne(
                dto.aspectRatio!!,
                customerIds = customerIdsForAspect,
                aspectRatioId = aspectRatioIdInPutRequest
            )
        } else {
            aspectRatioRepository.countByAspectRatio(
                dto.aspectRatio!!,
                customerIds = customerIdsForAspect
            )
        }
        if (aspectRatioCountFromDb > 0) {
            return ValidationErrorResponse(
                "aspectRatio",
                message.get(ASPECT_RATIO_DUPLICATE_ERROR, dto.aspectRatio)
            )
        }
        val (actualHeightInPixel, actualWidthInPixel) = parseAndValidateAspectRatio(dto)

        if (actualWidthInPixel == null || actualWidthInPixel < 0 || actualWidthInPixel > MAX_ASPECT_RATIO_LIMIT) {
            return ValidationErrorResponse(
                "actualWidthInPixel",
                message.get(
                    ASPECT_RATIO_MIN_MAX_ERROR,
                    MAX_ASPECT_RATIO_LIMIT
                )
            )
        }
        if (actualHeightInPixel == null || actualHeightInPixel < 0 || actualHeightInPixel > MAX_ASPECT_RATIO_LIMIT) {
            return ValidationErrorResponse(
                "actualHeightInPixel",
                message.get(
                    ASPECT_RATIO_MIN_MAX_ERROR,
                    MAX_ASPECT_RATIO_LIMIT
                )
            )
        }
        val aspectRationCalculatedThroughAlgo = calculateAspectRatio(
            actualWidthInPixel,
            actualHeightInPixel
        )
        if (!dto.aspectRatio.equals(aspectRationCalculatedThroughAlgo)) {
            return ValidationErrorResponse(
                "aspectRatio",
                message.get(ASPECT_RATIO_CALCULATION_ERROR, dto.aspectRatio)
            )
        }
        return null
    }

    private fun idNotFoundErrorResponse(
        aspectRatioId: Long
    ) = ValidationErrorResponse(
        "aspectRatioId",
        message.get(ASPECT_RATIO_ID_ERROR, aspectRatioId)
    )

    private fun parseAndValidateAspectRatio(
        aspectRatioRequestDto: AspectRatioRequestDto
    ): Pair<Long?, Long?> {
        var actualHeightInPixel: Long? = null
        var actualWidthInPixel: Long? = null
        try {
            actualHeightInPixel = aspectRatioRequestDto.actualHeightInPixel!!.toLong()
        } catch (ex: Exception) {
            logger.error(
                "error in parse aspect ration height::{}",
                aspectRatioRequestDto.actualHeightInPixel
            )
        }
        try {
            actualWidthInPixel = aspectRatioRequestDto.actualWidthInPixel!!.toLong()
        } catch (ex: Exception) {
            logger.error(
                "error in parse aspect ration width::{}",
                aspectRatioRequestDto.actualWidthInPixel
            )
        }
        return Pair(actualHeightInPixel, actualWidthInPixel)
    }

    companion object {
        private val logger: Logger = LoggerFactory.getLogger(AspectRatioServiceImpl::class.java)
        private var CUSTOMER_ID_FOR_DEFAULT_ASPECT_RATIO = 0L
        private const val MAX_ASPECT_RATIO_LIMIT = 1000000L
    }

}