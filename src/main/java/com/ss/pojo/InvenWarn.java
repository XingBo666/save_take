package com.ss.pojo;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "inven_warn")
public class InvenWarn {

    @Id
    private Long id;
    private Long goodsId;
    private String goodsName;
    private Date overdueTime;
    private Integer num;
    private String note;

    public InvenWarn() {
    }

    public InvenWarn(Long id, Long goodsId, String goodsName, Date overdueTime, Integer num, String note) {
        this.id = id;
        this.goodsId = goodsId;
        this.goodsName = goodsName;
        this.overdueTime = overdueTime;
        this.num = num;
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

    public Date getOverdueTime() {
        return overdueTime;
    }

    public void setOverdueTime(Date overdueTime) {
        this.overdueTime = overdueTime;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
