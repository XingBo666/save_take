package com.ss.pojo;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "reple")
public class Reple {
    @Id
    private Long id;
    private Long goodsId;
    private String goodsName;
    private Integer price;
    private Integer num;
    private Date produTime;
    private Date overdueTime;
    private String suppName;
    private Long suppId;
    private Date putTime;
    private Long employeeId;
    private String note;

    public Reple(){}

    public Reple(Long id, Long goodsId, String goodsName, Integer price, Integer num, Date produTime, Date overdueTime, String suppName, Long suppId, Date putTime, Long employeeId, String note) {
        this.id = id;
        this.goodsId = goodsId;
        this.goodsName = goodsName;
        this.price = price;
        this.num = num;
        this.produTime = produTime;
        this.overdueTime = overdueTime;
        this.suppName = suppName;
        this.suppId = suppId;
        this.putTime = putTime;
        this.employeeId = employeeId;
        this.note = note;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Date getProduTime() {
        return produTime;
    }

    public void setProduTime(Date produTime) {
        this.produTime = produTime;
    }

    public Date getOverdueTime() {
        return overdueTime;
    }

    public void setOverdueTime(Date overdueTime) {
        this.overdueTime = overdueTime;
    }

    public String getSuppName() {
        return suppName;
    }

    public void setSuppName(String suppName) {
        this.suppName = suppName;
    }

    public Long getSuppId() {
        return suppId;
    }

    public void setSuppId(Long suppId) {
        this.suppId = suppId;
    }

    public Date getPutTime() {
        return putTime;
    }

    public void setPutTime(Date putTime) {
        this.putTime = putTime;
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
