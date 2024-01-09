package com.digital.signage.dto;

import com.digital.signage.models.Device;
import com.digital.signage.models.PushNotification;
import com.digital.signage.models.PushPayload;
import com.digital.signage.models.WnsPushStatus;

public class HandlePushServiceDTO {

    private WnsPushStatus wnsPushStatus;
    private Device device;
    private PushPayload pushPayload;
    private PushNotification pushNotification;

    public HandlePushServiceDTO(WnsPushStatus wnsPushStatus,
                                Device device, PushPayload pushPayload, PushNotification pushNotification) {
        this.wnsPushStatus = wnsPushStatus;
        this.device = device;
        this.pushPayload = pushPayload;
        this.pushNotification = pushNotification;
    }

    public WnsPushStatus getWnsPushStatus() {
        return wnsPushStatus;
    }

    public Device getDevice() {
        return device;
    }

    public PushPayload getPushPayload() {
        return pushPayload;
    }

    public PushNotification getPushNotification() {
        return pushNotification;
    }

    //Builder Class
    public static class HandlePushServiceDTOBuilder{

        private WnsPushStatus wnsPushStatus;
        private Device device;
        private PushPayload pushPayload;
        private PushNotification pushNotification;


        public HandlePushServiceDTOBuilder setWnsPushStatus(WnsPushStatus wnsPushStatus) {
            this.wnsPushStatus = wnsPushStatus;
            return this;
        }

        public HandlePushServiceDTOBuilder setDevice(Device device) {
            this.device = device;
            return this;
        }

        public HandlePushServiceDTOBuilder setPushPayload(PushPayload pushPayload) {
            this.pushPayload = pushPayload;
            return this;
        }

        public HandlePushServiceDTOBuilder setPushNotification(PushNotification pushNotification) {
            this.pushNotification = pushNotification;
            return this;
        }

        public HandlePushServiceDTO build(){
            return new HandlePushServiceDTO(wnsPushStatus,device,pushPayload,pushNotification);
        }
    }
}



