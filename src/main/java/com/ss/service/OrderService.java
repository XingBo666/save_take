package com.ss.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ss.mapper.CustomerMapper;
import com.ss.mapper.GoodsMapper;
import com.ss.mapper.OrderMapper;
import com.ss.pojo.Customer;
import com.ss.pojo.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    OrderMapper OrderMapper;


    @Autowired
    GoodsMapper goodsMapper;

    @Autowired
    CustomerMapper customerMapper;

    //新增订单时应该

    public Order getOrderById(Long id) {
        return OrderMapper.selectByPrimaryKey(id);
    }

    public PageInfo<Order> getOrderByPage(Integer page) {
        PageHelper.startPage(page,5);
        List<Order> Orders = OrderMapper.selectAll();
        PageInfo<Order> info = new PageInfo(Orders);
        return info;
    }

    public Boolean addOrder(Order Order) {
        try{
            Order.setId(null);
            Order.setGoodsName(goodsMapper.selectByPrimaryKey(Order.getGoodsId()).getName());
            Order.setCustName(customerMapper.selectByPrimaryKey(Order.getCustId()).getName());
            Order.setTime(new Date());
            OrderMapper.insertSelective(Order);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    public Boolean delOrder(Long id) {
        try {

            OrderMapper.deleteByPrimaryKey(id);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public Boolean updateOrder(Order Order) {
        try {
            Order.setGoodsName(goodsMapper.selectByPrimaryKey(Order.getGoodsId()).getName());
            Order.setCustName(customerMapper.selectByPrimaryKey(Order.getCustId()).getName());
            Order.setTime(new Date());
            OrderMapper.updateByPrimaryKeySelective(Order);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public PageInfo<Order> searchByPhoneOrName( String keyword,Integer page) {
        if (keyword == "" || keyword == null){
            new PageInfo<>().setList(null);
        }

        Example example = new Example(Order.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.orLike("goodsName","%" + keyword + "%").orEqualTo("id",keyword);
        PageHelper.startPage(page,5);
        List<Order> Orders = OrderMapper.selectByExample(example);
        PageInfo<Order> info = new PageInfo(Orders);
        return info;
    }
}
