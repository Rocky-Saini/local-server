package com.digital.signage.models

/**
 * @author -Ravi Kumar created on 1/23/2023 4:33 PM
 * @project - Digital Sign-edge
 */
class ResponseExt<T>(
    result: T? = null,
    pagination: Pagination? = null,
    name: String? = null,
    code: Int? = null,
    message: String? = null,
    httpStatusCode: Int? = null
) : Response<T>(
    result,
    pagination,
    name,
    code,
    message,
    httpStatusCode
)