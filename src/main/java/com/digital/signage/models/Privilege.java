package com.digital.signage.models;

import javax.persistence.*;

/**
 * @author -Ravi Kumar created on 1/21/2023 10:10 PM
 * @project - Digital Sign-edge
 */
@Entity
@Table(name = "privilege")
public class Privilege {

    public static final String ALL_USERS = "ALL_USERS";

    public static final String ALL_DEVICES = "ALL_DEVICES";

    public static final String TPA_SERVER = "TPA_SERVER";
    public static final String PDN_USER = "PDN_USER";

    public static final String ADD_USER = "ADD_USER";
    public static final String EDIT_USER = "EDIT_USER";
    public static final String DELETE_USER = "DELETE_USER";
    public static final String VIEW_USER = "VIEW_USER";

    public static final String VIEW_DEVICE = "VIEW_DEVICE";
    public static final String VIEW_DEVICE_GROUP = "VIEW_DEVICE_GROUP";
    public static final String VIEW_LOCATION = "VIEW_LOCATION";

    public static final String VIEW_ROLE = "VIEW_ROLE";

    public static final String EDIT_CUSTOMER = "EDIT_CUSTOMER";
    public static final String VIEW_CUSTOMER = "VIEW_CUSTOMER";
    public static final String ADD_CUSTOMER_SETUP = "ADD_CUSTOMER_SETUP";

    public static final String EDIT_PLANOGRAM = "EDIT_PLANOGRAM";
    public static final String VIEW_PLANOGRAM = "VIEW_PLANOGRAM";
    public static final String ADD_PLANOGRAM = "ADD_PLANOGRAM";
    public static final String DELETE_PLANOGRAM = "DELETE_PLANOGRAM";

    public static final String EDIT_LAYOUT = "EDIT_LAYOUT";
    public static final String VIEW_LAYOUT = "VIEW_LAYOUT";
    public static final String VIEW_TEMPLATE = "VIEW_TEMPLATE";
    public static final String VIEW_CONTENT = "VIEW_CONTENT";

    public static final String VIEW_LAYOUT_GROUP = "VIEW_LAYOUT_GROUP";

    public static final String VIEW_ASPECT_RATIO = "VIEW_ASPECT_RATIO";
    public static final String ADD_ASPECT_RATIO = "ADD_ASPECT_RATIO";
    public static final String EDIT_ASPECT_RATIO = "EDIT_ASPECT_RATIO";
    public static final String DELETE_ASPECT_RATIO = "DELETE_ASPECT_RATIO";

    public static final String VIEW_FA_RULE_ENGINE = "VIEW_FA_RULE_ENGINE";
    public static final String EDIT_FA_RULE_ENGINE = "EDIT_FA_RULE_ENGINE";
    public static final String VIEW_FA_REPORTS = "VIEW_FA_REPORTS";
    public static final String VIEW_DEMOGRAPHY_CAMPAIGN_REPORTS = "VIEW_DEMOGRAPHY_CAMPAIGN_REPORTS";

    public static final String VIEW_REPORTS = "VIEW_REPORTS";

    public static final String APPROVE_LAYOUT = "APPROVE_LAYOUT";
    public static final String APPROVE_LAYOUT_GROUP = "APPROVE_LAYOUT_GROUP";
    public static final String APPROVE_PLANOGRAM = "APPROVE_PLANOGRAM";

    public static final String VIEW_CMS_ADVERTISEMENT = "VIEW_CMS_ADVERTISEMENT";
    public static final String EDIT_CMS_ADVERTISEMENT = "EDIT_CMS_ADVERTISEMENT";
    public static final String ADD_CMS_ADVERTISEMENT = "ADD_CMS_ADVERTISEMENT";
    public static final String DELETE_CMS_ADVERTISEMENT = "DELETE_CMS_ADVERTISEMENT";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "privilege_id")
    private Long id;

    @Column(name = "privilege_name")
    private String name;

    @JoinColumn(name = "action_id")
    @ManyToOne
    private Action action;

    @JoinColumn(name = "resource_type_id")
    @ManyToOne
    private ResourceType resourceType;

    public Privilege() {
        super();
    }

    public Privilege(final String name) {
        super();
        this.name = name;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public ResourceType getResourceType() {
        return resourceType;
    }

    public void setResourceType(ResourceType resourceType) {
        this.resourceType = resourceType;
    }

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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Privilege other = (Privilege) obj;
        if (name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!name.equals(other.name)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Privilege [name=").append(name).append("]").append("[id=").append(id)
                .append("]");
        return builder.toString();
    }
}
