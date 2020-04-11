package com.ss.pojo;

import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "inven_info")
public class InvenInfo {
    @Id
    private Long id;
    private Long goodsId;
    private String goodsName;
    private Integer total;
    private String note;


    public InvenInfo() {
    }

    public InvenInfo(Long id, Long goodsId, String goodsName, Integer total, String note) {
        this.id = id;
        this.goodsId = goodsId;
        this.goodsName = goodsName;
        this.total = total;
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

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
