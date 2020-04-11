package com.ss.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ss.mapper.GoodsMapper;
import com.ss.mapper.InvenWarnMapper;
import com.ss.pojo.InvenWarn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class InvenWarnService {
    @Autowired
    InvenWarnMapper InvenWarnMapper;

    @Autowired
    GoodsMapper goodsMapper;

    public InvenWarn getInvenWarnById(Long id) {
        return InvenWarnMapper.selectByPrimaryKey(id);
    }

    public PageInfo<InvenWarn> getInvenWarnByPage(Integer page) {
        PageHelper.startPage(page,5);
        List<InvenWarn> InvenWarns = InvenWarnMapper.selectAll();
        PageInfo<InvenWarn> info = new PageInfo(InvenWarns);
        return info;
    }

    public Boolean addInvenWarn(InvenWarn InvenWarn) {
        try{
            InvenWarn.setId(null);
            InvenWarn.setGoodsName(goodsMapper.selectByPrimaryKey(InvenWarn.getGoodsId()).getName());
            InvenWarnMapper.insertSelective(InvenWarn);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    public Boolean delInvenWarn(Long id) {
        try {
            InvenWarnMapper.deleteByPrimaryKey(id);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public Boolean updateInvenWarn(InvenWarn InvenWarn) {
        try {
            InvenWarn.setGoodsName(goodsMapper.selectByPrimaryKey(InvenWarn.getGoodsId()).getName());
            InvenWarnMapper.updateByPrimaryKeySelective(InvenWarn);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public PageInfo<InvenWarn> searchByPhoneOrName( String keyword,Integer page) {
        if (keyword == "" || keyword == null){
            new PageInfo<>().setList(null);
        }

        Example example = new Example(InvenWarn.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.orLike("goodsName","%" + keyword + "%").orEqualTo("id",keyword);
        PageHelper.startPage(page,5);
        List<InvenWarn> InvenWarns = InvenWarnMapper.selectByExample(example);
        PageInfo<InvenWarn> info = new PageInfo(InvenWarns);
        return info;
    }
}
