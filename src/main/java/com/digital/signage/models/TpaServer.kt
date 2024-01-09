package com.digital.signage.models

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import javax.persistence.*

/**
 * @author -Ravi Kumar created on 1/22/2023 11:48 PM
 * @project - Digital Sign-edge
 */
@Entity(name = "tpa_server")
class TpaServer(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "tpa_server_id")
    var tpaServerId: Long? = null,

    @Column(name = "tpa_server_name")
    var tpaServerName: String? = null,

    @Column(name = "tpa_server_description")
    var tpaServerDescription: String? = null,

    @Column(name = "tpa_server_key_hash")
    @JsonIgnore
    var tpaServerKeyHash: String? = null,

    @Column(name = "customer_id")
    var customerId: Long? = null,

    @Column(name = "tp_app_id")
    var tpAppId: Long? = null
) {
    @JsonIgnore
    @Transient
    private var tpaServerKey: String? = null

    @JsonProperty("tpaServerKey")
    fun getTpaServerKey(): String? {
        return tpaServerKey
    }

    fun setTpaServerKey(tpaServerKey: String?) {
        this.tpaServerKey = tpaServerKey
    }
}