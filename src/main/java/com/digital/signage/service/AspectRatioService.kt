package com.digital.signage.service

import com.digital.signage.dto.AspectBulkDeleteRequestDTO
import com.digital.signage.dto.AspectRatioCalculateDTO
import com.digital.signage.dto.AspectRatioRequestDto
import com.digital.signage.models.AspectRatio
import com.digital.signage.models.Response

/**
 * @author -Ravi Kumar created on 1/23/2023 4:19 PM
 * @project - Digital Sign-edge
 */
interface AspectRatioService : BaseService {
    fun getAspectRatiosForApi(): Response<List<AspectRatio>>?

    fun getAspectRatios(): List<AspectRatio>?

    fun getAspectRatioById(aspectRatioId: Long): AspectRatio?

    fun calculateAspectRatio(
        aspectRatioCalculateDTO: AspectRatioCalculateDTO?
    ): Response<out Any?>

    fun calculateAspectRatio(width: Long, height: Long): String

    fun addCustomAspectRatio(aspectRatioRequestDto: AspectRatioRequestDto): Response<out Any?>

    fun updateCustomAspectRatio(
        aspectRatioRequestDto: AspectRatioRequestDto,
        aspectRatioId: Long
    ): Response<out Any?>

    fun getAspectRatioByIdForApi(aspectRatioId: Long): Response<out Any>?

    fun deleteAspectRatioByIdForApi(aspectRatioId: Long): Response<out Any>?

    fun deleteAspectRatioInBulk(dto: AspectBulkDeleteRequestDTO): Response<out Any>?
}