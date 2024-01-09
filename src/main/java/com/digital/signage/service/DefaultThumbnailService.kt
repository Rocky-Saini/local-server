package com.digital.signage.service;

import org.springframework.stereotype.Service
import java.io.IOException

@Service
interface DefaultThumbnailService : BaseService {

    enum class ThumbnailType(val fileName: String, val urlPath: String) {
        NO_THUMBNAIL("no-thumbnail.png", "no-thumbnail"),
        TWITTER("twitter.png", "twitter"),
        FACEBOOK("facebook.png", "facebook"),
        DOC("doc.png", "doc"),
        PDF("pdf.png", "pdf"),
        RSS("rss.png", "rss");

        companion object {
            val map: MutableMap<String, ThumbnailType> = HashMap()

            init {
                values().forEach {
                    map.put(it.urlPath, it)
                }
            }

            fun fromUrlPath(urlPath: String?): ThumbnailType? = map[urlPath]
        }

    }

    /*@Throws(IOException::class)
    fun uploadDefaultThumbnail(inputFilePath: Path, thumbnailType: ThumbnailType,
            deleteInputFile: Boolean)

    @Throws(IOException::class)
    fun uploadDefaultThumbnail(inputFileStream: InputStream, thumbnailType: ThumbnailType)*/

    @Throws(IOException::class)
    fun getDefaultThumbnailUrl(thumbnailType: ThumbnailType): String

    /*@Throws(IOException::class)
    fun downloadThumbnailFile(thumbnailType: ThumbnailType,
            httpServletResponse: HttpServletResponse)

    @Throws(IOException::class)
    fun downloadThumbnailFile(thumbnailUrlPath: String?,
            httpServletResponse: HttpServletResponse)*/
}