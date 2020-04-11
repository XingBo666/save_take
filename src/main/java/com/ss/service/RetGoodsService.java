package com.ss.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ss.mapper.CustomerMapper;
import com.ss.mapper.GoodsMapper;
import com.ss.mapper.RetGoodsMapper;
import com.ss.pojo.RetGoods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class RetGoodsService {
    @Autowired
    RetGoodsMapper RetGoodsMapper;

    @Autowired
    GoodsMapper goodsMapper;

    @Autowired
    CustomerMapper customerMapper;

    public RetGoods getRetGoodsById(Long id) {
        return RetGoodsMapper.selectByPrimaryKey(id);
    }

    public PageInfo<RetGoods> getRetGoodsByPage(Integer page) {
        PageHelper.startPage(page,5);
        List<RetGoods> RetGoodss = RetGoodsMapper.selectAll();
        PageInfo<RetGoods> info = new PageInfo(RetGoodss);
        return info;
    }

    public Boolean addRetGoods(RetGoods RetGoods) {
        try{
            RetGoods.setId(null);
            String goodsName = goodsMapper.selectByPrimaryKey(RetGoods.getGoodsId()).getName();
            String custName = customerMapper.selectByPrimaryKey(RetGoods.getCustId()).getName();
            RetGoods.setGoodsName(goodsName);
            RetGoods.setCustName(custName);
            RetGoodsMapper.insertSelective(RetGoods);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    public Boolean delRetGoods(Long id) {
        try {
            RetGoodsMapper.deleteByPrimaryKey(id);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


    public Boolean updateRetGoods(RetGoods RetGoods) {
        try {
            String goodsName = goodsMapper.selectByPrimaryKey(RetGoods.getGoodsId()).getName();
            String custName = customerMapper.selectByPrimaryKey(RetGoods.getCustId()).getName();
            RetGoods.setGoodsName(goodsName);
            RetGoods.setCustName(custName);
            RetGoodsMapper.updateByPrimaryKeySelective(RetGoods);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public PageInfo<RetGoods> searchByPhoneOrName( String keyword,Integer page) {
        if (keyword == "" || keyword == null){
            new PageInfo<>().setList(null);
        }

        Example example = new Example(RetGoods.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.orLike("goodsName","%" + keyword + "%").orEqualTo("id",keyword);
        PageHelper.startPage(page,5);
        List<RetGoods> RetGoodss = RetGoodsMapper.selectByExample(example);
        PageInfo<RetGoods> info = new PageInfo(RetGoodss);
        return info;
    }
}
