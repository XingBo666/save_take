package com.ss.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ss.mapper.SupplierMapper;
import com.ss.pojo.Supplier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class SupplierService {

    @Autowired
    SupplierMapper supplierMapper;

    public Supplier getSupplierById(Long id) {
        return supplierMapper.selectByPrimaryKey(id);
    }

    public PageInfo<Supplier> getSupplierByPage(Integer page) {
        PageHelper.startPage(page,5);
        List<Supplier> suppliers = supplierMapper.selectAll();
        PageInfo<Supplier> info = new PageInfo(suppliers);
        return info;
    }

    public Boolean addSupp(Supplier supplier) {
        try{
            supplier.setId(null);
            supplierMapper.insertSelective(supplier);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    public Boolean delSupp(Long id) {
        try {
            supplierMapper.deleteByPrimaryKey(id);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public Boolean updateSupp(Supplier supplier) {
        try {
            supplierMapper.updateByPrimaryKeySelective(supplier);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public PageInfo<Supplier> searchByPhoneOrName( String keyword,Integer page) {
        if (keyword == "" || keyword == null){
            new PageInfo<>().setList(null);
        }

        Example example = new Example(Supplier.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.orLike("name","%" + keyword + "%").orEqualTo("phone",keyword);
        PageHelper.startPage(page,5);
        List<Supplier> suppliers = supplierMapper.selectByExample(example);
        PageInfo<Supplier> info = new PageInfo(suppliers);
        return info;
    }

    public List<Supplier> getAllSupp() {
        return supplierMapper.selectAll();
    }
}
