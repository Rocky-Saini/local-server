package com.digital.signage.models;

/**
 * @author -Ravi Kumar created on 2/1/2023 3:31 AM
 * @project - Digital Sign-edge
 */
public class RoleDTO {
    private Long roleId;
    private String roleName;

    public RoleDTO(Long roleId, String roleName) {
        this.roleId = roleId;
        this.roleName = roleName;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}

