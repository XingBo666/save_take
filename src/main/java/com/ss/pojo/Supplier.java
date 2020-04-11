package com.ss.pojo;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;

@Table( name = "supplier")
public class Supplier {
    @Id
    private Long id;
    private String name;
    private String phone;
    private Long employeeId;
    private String location;
    @Email
    private String email;

    public Supplier() {
    }

    public Supplier(Long id, String name, String phone, Long employeeId, String location, @Email String email) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.employeeId = employeeId;
        this.location = location;
        this.email = email;
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

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Supplier{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", employeeId=" + employeeId +
                ", location='" + location + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
