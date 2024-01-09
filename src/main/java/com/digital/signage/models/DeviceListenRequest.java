package com.digital.signage.models;

import com.digital.signage.util.EntityUtils;
import com.digital.signage.util.StartStopEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

/**
 * @author -Ravi Kumar created on 12/28/2022 1:44 AM
 * @project - Digital Sign-edge
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = EntityUtils.DEVICE_LISTEN_REQUEST_TABLE)
public class DeviceListenRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "device_id")
    private Long deviceId;

    @Column(name = "fb_registration_id")
    private String fbRegistrationId;

    @Column(name = "start_time")
    private Date startTime;

    @Column(name = "state")
    private StartStopEnum state;

    @Column(name = "user_id")
    private Long userId;
}
