package com.ss.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ss.mapper.GoodsMapper;
import com.ss.mapper.RepleMapper;
import com.ss.mapper.SupplierMapper;
import com.ss.pojo.Goods;
import com.ss.pojo.Reple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

@Service
public class RepleService {
    @Autowired
    RepleMapper RepleMapper;

    @Autowired
    GoodsMapper goodsMapper;

    @Autowired
    SupplierMapper supplierMapper;



    public Reple getRepleById(Long id) {
        return RepleMapper.selectByPrimaryKey(id);
    }

    public PageInfo<Reple> getRepleByPage(Integer page) {
        PageHelper.startPage(page,5);
        List<Reple> Reples = RepleMapper.selectAll();
        PageInfo<Reple> info = new PageInfo(Reples);
        return info;
    }

    public Boolean addReple(Reple Reple) {
        try{
            Reple.setId(null);
            Reple.setPutTime(new Date());
            Goods goods = goodsMapper.selectByPrimaryKey(Reple.getGoodsId());
            Reple.setProduTime( goods.getProduction());
            Reple.setGoodsName(goods.getName());
            Reple.setOverdueTime( goods.getOverdue());
            Reple.setSuppName(supplierMapper.selectByPrimaryKey(Reple.getSuppId()).getName());
            RepleMapper.insertSelective(Reple);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    public Boolean delReple(Long id) {
        try {


            RepleMapper.deleteByPrimaryKey(id);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public Boolean updateReple(Reple Reple) {
        try {
            Reple.setPutTime(new Date());
            Goods goods = goodsMapper.selectByPrimaryKey(Reple.getGoodsId());
            Reple.setProduTime( goods.getProduction());
            Reple.setGoodsName(goods.getName());
            Reple.setOverdueTime( goods.getOverdue());
            Reple.setSuppName(supplierMapper.selectByPrimaryKey(Reple.getSuppId()).getName());
            RepleMapper.updateByPrimaryKeySelective(Reple);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public PageInfo<Reple> searchByPhoneOrName( String keyword,Integer page) {
        if (keyword == "" || keyword == null){
            new PageInfo<>().setList(null);
        }

        Example example = new Example(Reple.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.orLike("goodsName","%" + keyword + "%").orEqualTo("id",keyword);
        PageHelper.startPage(page,5);
        List<Reple> Reples = RepleMapper.selectByExample(example);
        PageInfo<Reple> info = new PageInfo(Reples);
        return info;
    }
}
