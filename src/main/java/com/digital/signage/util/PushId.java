package com.digital.signage.util;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author -Ravi Kumar created on 12/28/2022 2:02 AM
 * @project - Digital Sign-edge
 */
public enum PushId {

    ID_CLIENT_DELETE_CONTENT(1),         //: instruct client to delete its content
    ID_CLIENT_UPDATE_APP(2),        //: instruct client to check for app update
    //ID_CLIENT_TURN_ON_TV_PANEL(3),       //: instruct client to turn ON their TV panel
    //ID_CLIENT_TURN_OFF_TV_PANEL(4),      //: instruct client to turn OFF their TV panel
    ID_CLIENT_DISABLE(5),                //: instruct client that you are disabled
    ID_CLIENT_REMOVE(6),                 //: instruct client that you are removed
    //ID_CLIENT_REFRESH(7),                //: instruct client that you are refresh
    ID_CLIENT_TURN_OFF_AUDIO(8),         //: instruct client to turn off it's audio
    ID_CLIENT_FETCH_SCHEDULE_AGAIN(9),          //: instruct client to turn on it's audio
    ID_CLIENT_REDOWNLOAD_CONFIG(10),   //: instruct client to fetch it's schedule again
    ID_CLIENT_TURN_ON_AUDIO(11),     //: instruct client to re-download config
    ID_CLIENT_ENABLE(12),                      //: instruct client that you are enabled (new)
    ID_CLIENT_ALL_PANELS_OF_DEVICES_ON(13),    //: instruct client to turn all your panels on (new)
    ID_CLIENT_ALL_PANELS_OF_DEVICES_OFF(14),   //: instruct client to turn all your panels off (new)
    ID_CLIENT_TURN_ON_ALL_PANEL_AUDIO(15),         //: instruct client to turn all panel audio on (new)
    ID_CLIENT_TURN_OFF_ALL_PANEL_AUDIO(16),        //: instruct client to turn all panel audio off (new)
    ID_CLIENT_REBOOT(17),                      //: instruct client to reboot (new)
    ID_CLIENT_INDIVIDUAL_PANELS_OF_DEVICES_ON(18),  //: instruct client to turn individual panel on (new)
    ID_CLIENT_INDIVIDUAL_PANELS_OF_DEVICES_OFF(19),  //: instruct client to turn individual panel off (new)
    ID_CLIENT_TURN_ON_INDIVIDUAL_PANEL_AUDIO(20),  //: instruct client to turn individual panel audio on (new)
    ID_CLIENT_TURN_OFF_INDIVIDUAL_PANEL_AUDIO(21),    //: instruct client to turn individual panel audio off (new)
    ID_CLIENT_UPDATE_YOUR_DEVICE_MODEL(22),
    ID_CLIENT_START_SENDING_DOWNLOAD_PROGRESS(23),   // instruct client to start sending download progress (new)
    ID_CLIENT_STOP_SENDING_DOWNLOAD_PROGRESS(24),    // instruct client to stop sending download progress (new)
    ID_CLIENT_START_SENDING_PANEL_STATUS(25),   // instruct client to start sending panel status (only send where there is a diff is status)
    ID_CLIENT_STOP_SENDING_PANEL_STATUS(26),    // instruct client to stop sending panel status (only send where there is a diff is status)
    ID_CLIENT_CUSTOMER_DEFAULT_IMAGE_UPLOADED(27),
    ID_CLIENT_SEND_A_SNAPSHOT_NOW(28),            // instruct client to send a snapshot immediately;
    ID_CLIENT_LOCAL_SERVER_HAS_DOWNLOADED_FILES(29),
    ID_CLIENT_REDOWNLOAD_CONTENT_ID(30),     // instruct client to re-download content ID mentioned in payload
    ID_CLIENT_CUSTOMER_DEFAULT_IMAGE_LOCAL_SERVER_DOWNLOADED(31), // instruct client to send download files from local server
    ID_CLIENT_UPLOAD_LOGS_NOW(32),   // instruct client to upload logs right now
    ID_CLIENT_TPA_UPDATE(33),  // instruct client that a new upgrade is available for a tpapp
    ID_CLIENT_DEMOGRAPHY_RULE_CHANGED(34),  // instruct client that a demography rules has changed
    ID_LOCAL_SERVER_BUILD_UPDATED(35);  // instruct local server that local server build has updated

    private int value;

    PushId(int value) {
        this.value = value;
    }

    private static Map<Integer, PushId> map = new HashMap<>();

    static {
        for (PushId pushId : PushId.values()) {
            map.put(pushId.value, pushId);
        }
    }

    @JsonCreator
    public static PushId valueOf(int pushId) {
        return map.get(pushId);
    }

    @JsonValue
    public int getValue() {
        return value;
    }

    @Converter(autoApply = true)
    public static class PushIdConverter
            implements AttributeConverter<PushId, Integer> {

        @Override
        public Integer convertToDatabaseColumn(PushId pushId) {
            if (null == pushId) return null;
            return pushId.getValue();
        }

        @Override
        public PushId convertToEntityAttribute(Integer dbData) {
            if (null == dbData) return null;
            return PushId.valueOf(dbData);
        }
    }
}
