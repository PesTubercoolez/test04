package com.company.model;

import javax.persistence.*;

@Entity
@Table(name ="roles")
public class Role {

    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "name")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
