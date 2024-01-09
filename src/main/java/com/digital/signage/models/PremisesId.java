package com.digital.signage.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.*;

@Entity(name="premisesId")
public class PremisesId implements EntityModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "pk_id")
    @JsonIgnore
    private Long pkID;

    @Column(name = "premiseid")
    @JsonProperty("premiseId")
    private Long premiseid;

    @Column(name = "server_fcm_key")
    private String serverFcmKey;

    public Long getPkID() {
        return pkID;
    }

    public void setPkID(Long pkID) {
        this.pkID = pkID;
    }

    public Long getPremiseid() {
        return premiseid;
    }

    public void setPremiseid(Long premiseid) {
        this.premiseid = premiseid;
    }

    public String getServerFcmKey() {
        return serverFcmKey;
    }

    public void setServerFcmKey(String serverFcmKey) {
        this.serverFcmKey = serverFcmKey;
    }

    @Override
    public String toString() {
        return "PremisesId{" +
                "pkID=" + pkID +
                ", premiseid=" + premiseid +
                ", serverFcmKey='" + serverFcmKey + '\'' +
                '}';
    }
}
