package com.ss.pojo;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "tb_order")
public class Order {
    @Id
    private Long id;
    private Long custId;
    private String custName;
    private Long goodsId;
    private String goodsName;
    private Integer num;
    private Date time;
    private String note;

    public Order(){}

    public Order(Long id, Long custId, String custName, Long goodsId, String goodsName, Integer num, Date time, String note) {
        this.id = id;
        this.custId = custId;
        this.custName = custName;
        this.goodsId = goodsId;
        this.goodsName = goodsName;
        this.num = num;
        this.time = time;
        this.note = note;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCustId() {
        return custId;
    }

    public void setCustId(Long custId) {
        this.custId = custId;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
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

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
