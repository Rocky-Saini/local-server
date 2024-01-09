package com.digital.signage.dto.common;

public class CustomerBrandingSmtpDTO {

    private String host;
    private Integer port;
    private String senderEmailAddress;
    private Boolean useTLS;
    private String password;
    private String receiverEmailAddress;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getSenderEmailAddress() {
        return senderEmailAddress;
    }

    public void setSenderEmailAddress(String senderEmailAddress) {
        this.senderEmailAddress = senderEmailAddress;
    }

    public Boolean getUseTLS() {
        return useTLS;
    }

    public void setUseTLS(Boolean useTLS) {
        this.useTLS = useTLS;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getReceiverEmailAddress() {
        return receiverEmailAddress;
    }

    public void setReceiverEmailAddress(String receiverEmailAddress) {
        this.receiverEmailAddress = receiverEmailAddress;
    }
}
