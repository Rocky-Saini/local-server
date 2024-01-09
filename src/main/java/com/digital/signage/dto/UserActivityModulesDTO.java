package com.digital.signage.dto;

import com.digital.signage.util.UserActivityEnum;

/**
 * @author -Ravi Kumar created on 1/21/2023 9:48 PM
 * @project - Digital Sign-edge
 */
public class UserActivityModulesDTO {
    private UserActivityEnum.ModulesEnum Key;
    private String name;

    public UserActivityEnum.ModulesEnum getKey() {
        return Key;
    }

    public void setKey(UserActivityEnum.ModulesEnum key) {
        Key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
