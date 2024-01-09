package com.digital.signage.models

import javax.persistence.*

@Entity
@Table(name = CustomerLevelPushViaRabbitMq.TABLE_NAME)
data class CustomerLevelPushViaRabbitMq(

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null,

    @Column(name = COL_CUSTOMER_ID)
    var customerId: Long? = null

) {
    companion object {
        const val TABLE_NAME = "customer_level_push_via_rabbit_mq"
        const val COL_CUSTOMER_ID = "customer_id"
    }
}