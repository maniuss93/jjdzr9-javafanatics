package com.isa.jjdzr.model;

import jakarta.persistence.*;

@Entity
@Table(name = "roles")
public class Role{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roleId;

    @Column(name = "name")
    private String name;

    protected Role() {
    }

    public Role(String roleName) {
    }

    public Long getId() {
        return roleId;
    }

    public void setId(Long id) {
        this.roleId = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
