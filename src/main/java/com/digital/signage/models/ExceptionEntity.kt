package com.digital.signage.models

import java.util.*
import javax.persistence.*

/**
 * @author -Ravi Kumar created on 1/29/2023 8:44 PM
 * @project - Digital Sign-edge
 */
@Entity
@Table(name = "all_exceptions")
data class ExceptionEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null,

    @Column(name = "time_of_exception")
    var timeOfException: Date = Date(),

    @Column(name = "exception")
    var exception: String? = null,

    @Column(name = "request_api")
    var requestApi: String? = null
) {
    constructor(
        exception: String,
        requestApi: String
    ) : this(
        id = null,
        exception = exception,
        requestApi = requestApi,
        timeOfException = Date()
    )
}
