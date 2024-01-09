package com.digital.signage.models;

import com.digital.signage.util.EntityUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Date;

/**
 * @author -Ravi Kumar created on 12/22/2022 3:34 AM
 * @project - Digital Sign-edge
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = EntityUtils.DEVICE_TOKEN_TABLE)
public class DeviceToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Column(name = "device_id")
    private Long deviceId;
    @Column(name = "expiry_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date expiryDate;

}
