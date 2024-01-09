package com.digital.signage.models;

import com.digital.signage.util.PushNotificationStatus;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @author -Ravi Kumar created on 12/28/2022 2:10 AM
 * @project - Digital Sign-edge
 */
@Data
@Entity
@Table(name="push_notification")
public class PushNotification {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    public Long id;
    @Column(name = "device_id")
    private Long deviceId;
    @Column(name = "push_id")
    private Integer pushId;
    @Column(name = "status")
    public PushNotificationStatus status;
    @Column(name = "send_time")
    private Date sendTime;
    @Column(name = "ack_time")
    private Date ackTime;
    @Column(name = "send_by")
    private Long sendBy;
    @Column(name = "customer_id")
    private Long customerId;
    @Column(name = "attempts")
    private Integer attempts;
    @Column(name = "failure_reason")
    public String failureReason;

    @Column(name = "unique_request_id")
    private String uniqueRequestId;

    @Column(name = "batch_id")
    private String batchId;
    @Column(name = "content_id")
    private Long contentId;
    @Column(name = "push_payload_json")
    public String pushPayloadJson;
}
