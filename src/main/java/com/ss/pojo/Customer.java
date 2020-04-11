package com.ss.pojo;

import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "customer")
public class Customer {
    @Id
    private Long id;
    private String name;
    private String phone;
    private String location;
    private Long employeeId;
    private String note;

    public Customer() {
    }

    public Customer(Long id, String name, String phone, String location, Long employeeId, String note) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.location = location;
        this.employeeId = employeeId;
        this.note = note;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
