package com.digital.signage.models;

import java.util.*;
import java.util.stream.Collectors;

import javax.persistence.*;

import com.digital.signage.annotations.PdfColumn;
import com.digital.signage.annotations.PdfTitle;
import com.digital.signage.annotations.ReportColumn;
import com.digital.signage.util.ApplicationConstants;
import com.digital.signage.util.ReportConstants;
import com.digital.signage.util.Status;
import com.digital.signage.util.UserTypeEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 * @author -Ravi Kumar created on 12/16/2022 12:27 AM
 * @project - Digital Sign-edge
 */
@Entity
@Table(name = User.DB_TABLE_NAME)
@PdfTitle(title = ReportConstants.USER_MODEL_TITLE)
public class User implements EntityModel, IPoc {

    public static final String DB_TABLE_NAME = "dse_user";
    public static final String JSON_KEY_EMAIL = "email";
    public static final String JSON_KEY_FULL_NAME = "fullName";
    public static final String JSON_KEY_USER_ID = "userId";
    public static final String JSON_KEY_ROLES = "roles";
    public static final String JSON_KEY_PASSWORD = "password";
    public static final String JSON_KEY_LOCATION_IDS = "locationIds";
    public static final String JSON_KEY_STATUS = "status";
    public static final int PASSWORD_NOT_SET = 4;
    public static final String JSON_KEY_CUSTOMER = "customer";

    public static final String DB_COL_USER_ID = "user_id";
    public static final String DB_COL_FULL_NAME = "full_name";

    public static Map<String, ClassParam> paramTypeMap;

    static {
        paramTypeMap = new HashMap<>();
        paramTypeMap.put("email", new ClassParam(String.class, "email"));
        paramTypeMap.put("fullname", new ClassParam(String.class, "fullName"));
        paramTypeMap.put("created_date", new ClassParam(Date.class, "u.createdOn"));
        paramTypeMap.put("role", new ClassParam(Long.class, "ur.roleId"));
        paramTypeMap.put("location_access", new ClassParam(Long.class, null));
        paramTypeMap.put("status", new ClassParam(Status.class, "u.status"));
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = DB_COL_USER_ID)
    @JsonProperty(JSON_KEY_USER_ID)
    private Long userId;

    @Column(name = DB_COL_FULL_NAME)
    @JsonProperty(JSON_KEY_FULL_NAME)
    @PdfColumn(order = 1, columnName = "User Name")
    @ReportColumn(order = 1, columnName = "User Name")
    private String fullName;

    @ReportColumn(order = 2, columnName = "Email")
    @PdfColumn(order = 2, columnName = "Email")
    @Column(name = "email", unique = true)
    @JsonProperty(JSON_KEY_EMAIL)
    private String email;

    @Column(name = "password")

    private String password;
    @Column(name = "profile_image")
    private String profileImage;

    @ReportColumn(order = 4, columnName = "Mobile")
    @PdfColumn(order = 4, columnName = "Mobile")
    @Column(name = "mobile")
    private String mobile;

    @ReportColumn(order = 3, columnName = "Country Code")
    @PdfColumn(order = 3, columnName = "Country Code")
    @Column(name = "country_code")
    private String countryCode;

    @JsonIgnore
    @Column(name = "created_by")
    private Long createdBy;
    @Column(name = "created_on")
    private Date createdOn;
    @Column(name = "modified_on")
    private Date modifiedOn;

    @Column(name = "status")
    private Status status;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @ReportColumn(order = 6, columnName = "Address")
    @PdfColumn(order = 6, columnName = "Address")
    @Column(name = "address")
    private String address;

//    @ManyToMany(fetch = FetchType.EAGER)
//    @JoinTable(name = "user_location_rel", joinColumns = @JoinColumn(
//            name = "user_id", referencedColumnName = "user_id"),
//            inverseJoinColumns = @JoinColumn(name = "location_id", referencedColumnName = "location_id"))
    @Transient
    private Set<Location> locations;

    @JsonIgnore
    @Transient
    private Set<Long> locationIds;

    @JsonProperty(JSON_KEY_LOCATION_IDS)
    public Set<Long> getLocationIdsFromLocations() {
        return locations == null || locations.isEmpty() ? null :
                locations.stream().map(Location::getLocationId).collect(Collectors.toSet());
    }

    public Set<Long> getLocationIds() {
        return locationIds;
    }

    @JsonProperty("locationIds")
    public void setLocationIds(Set<Long> locationIds) {
        this.locationIds = locationIds;
    }

    public Set<Location> getLocations() {
        return locations;
    }

    public void setLocations(Set<Location> locations) {
        this.locations = locations;
    }

    @Transient
    private Set<Long> roleIds;

    @JsonProperty(JSON_KEY_ROLES)
    public Set<RoleDTO> getRoleDTOSet() {
        return roles != null ? this.roles.stream()
                .map(x -> new RoleDTO(x.getId(), x.getName()))
                .collect(Collectors.toSet()) : null;
    }

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_role_mapping",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "user_id"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "role_id"))
    private Set<Role> roles;

//    @JsonIgnore
//    @ManyToMany(fetch = FetchType.EAGER)
//    @Fetch(FetchMode.JOIN)
//    @JoinTable(name = "user_customer_association",
//            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "user_id"),
//            inverseJoinColumns = @JoinColumn(name = "customer_id", referencedColumnName = "customer_id"))
//    private Set<Customer> customers;

    @JsonIgnore
    @ReportColumn(order = 7, columnName = "User Role")
    @PdfColumn(order = 7, columnName = "User Role")
    @Transient
    private String userRoleForPdf;

    @ReportColumn(order = 5, columnName = "Status")
    @PdfColumn(order = 5, columnName = "Status")
    @JsonIgnore
    @Transient
    private String statusForPdfReport;

    @JsonIgnore
    public String getStatusForPdfReport() {
        return statusForPdfReport;
    }

    @JsonIgnore
    public void setStatusForPdfReport(String statusForPdfReport) {
        this.statusForPdfReport = statusForPdfReport;
    }

    @JsonIgnore
    public String getUserRoleForPdf() {
        return userRoleForPdf;
    }

    @JsonIgnore
    public void setUserRoleForPdf(String userRoleForPdf) {
        this.userRoleForPdf = userRoleForPdf;
    }

//    public Set<Customer> getCustomers() {
//        return customers;
//    }

    @JsonProperty("canAddAndEditMasterLayouts")
    public boolean canAddAndEditMasterLayouts() {
        return locations == null || locations.isEmpty();
    }

//    @JsonIgnore
//    public List<Long> getCustomerIds() {
//        return this.customers == null ? new ArrayList<>(0)
//                : this.customers.stream().map(Customer::getCustomerId).collect(
//                Collectors.toList());
//    }
//
//    public void setCustomers(Set<Customer> customers) {
//        this.customers = customers;
//    }

//    @JsonProperty("customer")
//    public Customer getCustomerJSON() {
//        return this.getIsPanasonicUser() ? null : this.getCustomers().iterator().next();
//    }

    public User() {
        // need not implement
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    private Set<Long> immutableRoleIds() {
        if (roleIds == null) return new HashSet<>(0);
        return new HashSet<>(roleIds);
    }

    public Set<Role> getRoles() {
        return immutableRoles();
    }

    public void setRoles(Set<Role> roles) {
        if (roles == null || roles.isEmpty()) throw new IllegalStateException("Roles cannot be empty");
        this.roles = roles;
    }

    private Set<Role> immutableRoles() {
        if (roles == null) return null;
        return roles.stream().map(Role::clone).collect(Collectors.toSet());
    }

    public Set<Long> getRoleIds() {
        if (roles != null && !roles.isEmpty()) {
            roleIds = new HashSet<>(roles.size());
            for (Role role : roles) {
                roleIds.add(role.getId());
            }
        }
        return immutableRoleIds();
    }

    public void setRoleIds(Set<Long> roleIds) {
        if (roleIds == null || roleIds.isEmpty()) {
            throw new IllegalStateException("roleIds cannot be empty");
        }
        this.roleIds = roleIds;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    @JsonProperty(JSON_KEY_PASSWORD)
    public void setPassword(String password) {
        this.password = password;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Date getModifiedOn() {
        return modifiedOn;
    }

    public void setModifiedOn(Date modifiedOn) {
        this.modifiedOn = modifiedOn;
    }

    @JsonIgnore
    public Status getStatus() {
        return status;
    }

    @JsonProperty(value = JSON_KEY_STATUS, access = JsonProperty.Access.WRITE_ONLY)
    public void setStatus(Status status) {
        this.status = status;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    @Transient
    private Boolean isPanasonicUser;
    @Transient
    private Boolean isCustomerAdmin;
    @Transient
    private Boolean isPanasonicAdmin;

    @JsonIgnore
    public Boolean getIsCustomerAdmin() {
        if (isCustomerAdmin != null) return isCustomerAdmin;
        isCustomerAdmin = false;
        if (roles != null) {
            List<String> intersect = roles.stream().map(Role::getName)
                    .filter(ApplicationConstants.SET_CUSTOMER_ADMIN_ROLES::contains)
                    .collect(Collectors.toList());
            isCustomerAdmin = !intersect.isEmpty();
        }
        return isCustomerAdmin;
    }

    @JsonIgnore
    public Boolean getIsPansonicAdmin() {
        if (isPanasonicAdmin != null) return isPanasonicAdmin;
        isPanasonicAdmin = false;
        if (roles != null) {
            List<String> intersect = roles.stream().map(Role::getName)
                    .filter(ApplicationConstants.SET_PANASONIC_ADMIN_ROLES::contains)
                    .collect(Collectors.toList());
            isPanasonicAdmin = !intersect.isEmpty();
        }
        return isPanasonicAdmin;
    }

    @JsonIgnore
    public Boolean getIsPanasonicCustRep() {
        boolean retVal = false;
        if (roles != null) {
            List<String> intersect = roles.stream().map(Role::getName)
                    .filter(ApplicationConstants.SET_PAN_CUST_REP_ROLES::contains)
                    .collect(Collectors.toList());
            retVal = !intersect.isEmpty();
        }
        return retVal;
    }

    /**
     * @return true if Pan admin, cust admin, cust rep
     */
    @JsonIgnore
    public Boolean getIsAdminUser() {
        boolean retVal = false;
        if (roles != null) {
            List<String> intersect = roles.stream().map(Role::getName)
                    .filter(ApplicationConstants.SET_ALL_ADMIN_ROLES::contains)
                    .collect(Collectors.toList());
            retVal = !intersect.isEmpty();
        }
        return retVal;
    }

    @JsonIgnore
    public Boolean getIsPanasonicAdmin() {
        boolean retVal = false;
        if (roles != null) {
            List<String> intersect = roles.stream().map(Role::getName)
                    .filter(ApplicationConstants.SET_PANASONIC_ADMIN_ROLES::contains)
                    .collect(Collectors.toList());
            retVal = !intersect.isEmpty();
        }
        return retVal;
    }

    @JsonIgnore
    public Boolean getIsPDNServerUser() {
        boolean retVal = false;
        if (roles != null) {
            List<String> intersect = roles.stream().map(Role::getName)
                    .filter(ApplicationConstants.ROLE_PDN_SERVER::equals)
                    .collect(Collectors.toList());
            retVal = !intersect.isEmpty();
        }
        return retVal;
    }

    @JsonIgnore
    public Boolean getIsPanasonicUser() {
        boolean retVal = false;
        if (roles != null) {
            List<String> intersect = roles.stream().map(Role::getName)
                    .filter(ApplicationConstants.SET_PANASONIC_ROLES::contains)
                    .collect(Collectors.toList());
            retVal = !intersect.isEmpty();
        }
        return retVal;
    }

    @JsonProperty("userType")
    public String getUserType() {
        String ret = null;
        if (this.getIsPansonicAdmin()) {
            ret = UserTypeEnum.SUPER.name();
        } else if (this.getIsPanasonicCustRep()) {
            ret = UserTypeEnum.CUST_REP_ADMIN.name();
        } else if (this.getIsCustomerAdmin()) {
            ret = UserTypeEnum.CUSTOMER_ADMIN.name();
        }
        return ret;
    }

    @JsonProperty(value = JSON_KEY_STATUS, access = JsonProperty.Access.READ_ONLY)
    @Transient
    public Integer getUserStatusValue() {
        return status != null ? status.getValue() : PASSWORD_NOT_SET;
    }
}
