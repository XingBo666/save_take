package com.ss.pojo;

import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "employee")
public class Employee {

    @Id
    private Long id;
    private String name;
    private String phone;
    private Integer position;

    public Employee() {
    }

    public Employee(Long id, String name, String phone, Integer position) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.position = position;
    }

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }
}
