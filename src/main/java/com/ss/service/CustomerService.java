package com.ss.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ss.mapper.CustomerMapper;
import com.ss.pojo.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class CustomerService {
    @Autowired
    CustomerMapper customerMapper;

    public Customer getCustomerById(Long id) {
        return customerMapper.selectByPrimaryKey(id);
    }

    public PageInfo<Customer> getcustomerByPage(Integer page) {
        PageHelper.startPage(page,5);
        List<Customer> customers = customerMapper.selectAll();
        PageInfo<Customer> info = new PageInfo(customers);
        return info;
    }

    public Boolean addCustomer(Customer customer) {
        try{
            customer.setId(null);
            customerMapper.insertSelective(customer);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    public Boolean delCustomer(Long id) {
        try {
            customerMapper.deleteByPrimaryKey(id);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public Boolean updateCustomer(Customer customer) {
        try {
            customerMapper.updateByPrimaryKeySelective(customer);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public PageInfo<Customer> searchByPhoneOrName( String keyword,Integer page) {
        if (keyword == "" || keyword == null){
            new PageInfo<>().setList(null);
        }

        Example example = new Example(Customer.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.orEqualTo("id",keyword).orEqualTo("employeeId",keyword);
        PageHelper.startPage(page,5);
        List<Customer> customers = customerMapper.selectByExample(example);
        PageInfo<Customer> info = new PageInfo(customers);
        return info;
    }
}
