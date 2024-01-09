package com.digital.signage.models;

import com.digital.signage.util.Status;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.*;

/**
 * @author -Ravi Kumar created on 1/21/2023 10:08 PM
 * @project - Digital Sign-edge
 */
@Entity
@Table(name = "role")
public class Role implements Cloneable {
    public static final String CUSTOMER_ADMIN = "CUSTOMER_ADMIN";
    public static final String PANASONIC_ADMIN = "PANASONIC_ADMIN";
    public static final String APPROVER = "APPROVER";
    public static final String PANASONIC_CUST_REP = "PANASONIC_CUST_REP";
    public static final String MAKER = "MAKER";
    public static final String VIEW_ONLY = "VIEW_ONLY";
    public static final String PDN_SERVER = "PDN_SERVER";

    public static Role customerAdminWithoutRoleId() {
        return new Role(null, CUSTOMER_ADMIN);
    }

    public static Role customerRepWithoutRoleId() {
        return new Role(null, PANASONIC_CUST_REP);
    }

    public static Role panAdminWithoutRoleId() {
        return new Role(null, PANASONIC_ADMIN);
    }

    public static Role approverWithoutRoleId() {
        return new Role(null, APPROVER);
    }

    public static Role viewOnlyWithoutRoleId() {
        return new Role(null, VIEW_ONLY);
    }

    public static Role makerWithoutRoleId() {
        return new Role(null, MAKER);
    }

    public static final List<String> NON_LOCATION_RESTRICTED_ROLES = new ArrayList<>(3);

    static {
        NON_LOCATION_RESTRICTED_ROLES.add(CUSTOMER_ADMIN);
        NON_LOCATION_RESTRICTED_ROLES.add(PANASONIC_ADMIN);
        NON_LOCATION_RESTRICTED_ROLES.add(PANASONIC_CUST_REP);
        NON_LOCATION_RESTRICTED_ROLES.add(VIEW_ONLY);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "role_id")
    @JsonProperty("roleId")
    private Long id;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "role_privilege_mapping", joinColumns = @JoinColumn(name = "role_id", referencedColumnName = "role_id"), inverseJoinColumns = @JoinColumn(name = "privilege_id", referencedColumnName = "privilege_id"))
    private Collection<Privilege> privileges;
    @JsonProperty("roleName")
    @Column(name = "role_name")
    private String name;
    @Column(name = "customer_id")
    private Long customerId;
    @Column(name = "created_by")
    @JsonIgnore
    private Long createdBy;
    @Column(name = "created_on")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date createdOn;
    @Column(name = "modified_on")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date modifiedOn;
    @Column(name = "role_description")
    @JsonProperty("roleDescription")
    private String roleDescription;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Column(name = "status")
    private Status status;

    public static boolean isUserLocationRestricted(
            String roleName,
            boolean isApproverRole
    ) {
        return !(NON_LOCATION_RESTRICTED_ROLES.contains(roleName)
                || isApproverRole); /** Approver roles can't be location restricted */
    }

    @JsonProperty("isLocationRestricted")
    public boolean isLocationRestricted() {
        return isUserLocationRestricted(getName(), isApproverRole(this));
    }

    @Override
    protected Role clone() {
        try {
            return (Role) super.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    public Role() {
    }

    public Role(Long roleId) {
        this.id = roleId;
    }

    public Role(final String name) {
        this.name = name;
    }

    public Role(
            Long roleId,
            final String name
    ) {
        this.name = name;
    }

    public static boolean isApproverRole(Role role) {
        return role.getPrivileges()
                .parallelStream()
                .anyMatch(it -> it.getName().split("_")[0].contains(Action.APPROVE));
    }

    public String getRoleDescription() {
        return roleDescription;
    }

    public void setRoleDescription(String roleDescription) {
        this.roleDescription = roleDescription;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
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

    //

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Collection<Privilege> getPrivileges() {
        return privileges;
    }

    public void setPrivileges(final Collection<Privilege> privileges) {
        this.privileges = privileges;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Role role = (Role) obj;
        if (!this.name.equals(role.name)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Role [name=").append(name).append("]").append("[id=").append(id).append("]");
        return builder.toString();
    }

    @JsonIgnore
    @ManyToMany(mappedBy = "roles")
    private Set<User> users;

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}