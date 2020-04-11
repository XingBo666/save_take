package com.ss.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ss.mapper.InvenInfoMapper;
import com.ss.pojo.InvenInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class InvenInfoService {
    @Autowired
    InvenInfoMapper InvenInfoMapper;

    public InvenInfo getInvenInfoById(Long id) {
        return InvenInfoMapper.selectByPrimaryKey(id);
    }

    public PageInfo<InvenInfo> getInvenInfoByPage(Integer page) {
        PageHelper.startPage(page,5);
        List<InvenInfo> InvenInfos = InvenInfoMapper.selectAll();
        PageInfo<InvenInfo> info = new PageInfo(InvenInfos);
        return info;
    }

    public Boolean addInvenInfo(InvenInfo InvenInfo) {
        try{
            InvenInfo.setId(null);
            InvenInfoMapper.insertSelective(InvenInfo);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    public Boolean delInvenInfo(Long id) {
        try {
            InvenInfoMapper.deleteByPrimaryKey(id);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public Boolean updateInvenInfo(InvenInfo InvenInfo) {
        try {
            InvenInfoMapper.updateByPrimaryKeySelective(InvenInfo);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public PageInfo<InvenInfo> searchByPhoneOrName( String keyword,Integer page) {
        if (keyword == "" || keyword == null){
            new PageInfo<>().setList(null);
        }

        Example example = new Example(InvenInfo.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.orLike("goodsName","%" + keyword + "%").orEqualTo("id",keyword);
        PageHelper.startPage(page,5);
        List<InvenInfo> InvenInfos = InvenInfoMapper.selectByExample(example);
        PageInfo<InvenInfo> info = new PageInfo(InvenInfos);
        return info;
    }
}
