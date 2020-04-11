package com.ss.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ss.mapper.CustRetMapper;
import com.ss.mapper.CustomerMapper;
import com.ss.mapper.GoodsMapper;
import com.ss.mapper.InvenInfoMapper;
import com.ss.pojo.CustRet;
import com.ss.pojo.InvenInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

@Service
public class CustRetService {
    @Autowired
    CustRetMapper CustRetMapper;

    @Autowired
    GoodsMapper goodsMapper;

    @Autowired
    CustomerMapper customerMapper;

    InvenInfoMapper InvenInfoMapper;

    public CustRet getCustRetById(Long id) {
        return CustRetMapper.selectByPrimaryKey(id);
    }

    public PageInfo<CustRet> getCustRetByPage(Integer page) {
        PageHelper.startPage(page,5);
        List<CustRet> CustRets = CustRetMapper.selectAll();
        PageInfo<CustRet> info = new PageInfo(CustRets);
        return info;
    }

    public Boolean addCustRet(CustRet CustRet) {
        try{
            CustRet.setRetTime( new Date());
            CustRet.setCustName(customerMapper.selectByPrimaryKey(CustRet.getCustId()).getName());
            CustRet.setGoodsName(goodsMapper.selectByPrimaryKey(CustRet.getGoodsId()).getName());


            CustRet.setId(null);
            CustRetMapper.insertSelective(CustRet);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    @Transactional
    public Boolean delCustRet(Long id) {
        try {
            CustRetMapper.deleteByPrimaryKey(id);

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public Boolean updateCustRet(CustRet CustRet) {
        try {
            CustRetMapper.updateByPrimaryKeySelective(CustRet);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public PageInfo<CustRet> searchByPhoneOrName( String keyword,Integer page) {
        if (keyword == "" || keyword == null){
            new PageInfo<>().setList(null);
        }

        Example example = new Example(CustRet.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.orEqualTo("id",keyword).orLike("goodsName","%" + keyword + "%");
        PageHelper.startPage(page,5);
        List<CustRet> CustRets = CustRetMapper.selectByExample(example);
        PageInfo<CustRet> info = new PageInfo(CustRets);
        return info;
    }
}
