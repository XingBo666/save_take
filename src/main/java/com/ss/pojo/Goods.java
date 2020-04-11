package com.ss.pojo;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "goods")
public class Goods {
    @Id
    private Long id;
    private String name;
    private Integer num;
    private Integer purPrice;
    private Integer price;
    private Date production;
    private Date overdue;
    private String supplierName;
    private String pieway;
    private String note;

    public Goods() {
    }

    public Goods(Long id, String name, Integer num, Integer purPrice, Integer price, Date production, Date overdue, String supplierName, String pieway, String note) {
        this.id = id;
        this.name = name;
        this.num = num;
        this.purPrice = purPrice;
        this.price = price;
        this.production = production;
        this.overdue = overdue;
        this.supplierName = supplierName;
        this.pieway = pieway;
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

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getPurPrice() {
        return purPrice;
    }

    public void setPurPrice(Integer purPrice) {
        this.purPrice = purPrice;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Date getProduction() {
        return production;
    }

    public void setProduction(Date production) {
        this.production = production;
    }

    public Date getOverdue() {
        return overdue;
    }

    public void setOverdue(Date overdue) {
        this.overdue = overdue;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getPieway() {
        return pieway;
    }

    public void setPieway(String pieway) {
        this.pieway = pieway;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
