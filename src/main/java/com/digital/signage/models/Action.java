package com.digital.signage.models;

import javax.persistence.*;

/**
 * @author -Ravi Kumar created on 1/21/2023 10:11 PM
 * @project - Digital Sign-edge
 */
@Entity(name = "action")
public class Action {
    public static final String VIEW = "VIEW";
    public static final String ADD = "ADD";
    public static final String EDIT = "EDIT";
    public static final String DELETE = "DELETE";

    public static final String APPROVE = "APPROVE";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "action_id")
    private Long id;

    @Column(name = "action_name")
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

