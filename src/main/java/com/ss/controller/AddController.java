package com.ss.controller;

import com.ss.mapper.*;
import com.ss.pojo.*;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;
import java.util.Random;

@ResponseBody
@Controller
public class AddController {

    @Autowired
    CustomerMapper customerMapper;
    @Autowired
    CustRetMapper custRetMapper;
    @Autowired
    EmployeeMapper employeeMapper;
    @Autowired
    GoodsMapper goodsMapper;
    @Autowired
    InvenInfoMapper invenInfoMapper;
    @Autowired
    InvenWarnMapper invenWarnMapper;
    @Autowired
    OrderMapper orderMapper;
    @Autowired
    RepleMapper repleMapper;
    @Autowired
    RetGoodsMapper retGoodsMapper;
    @Autowired
    SupplierMapper supplierMapper;

    @RequestMapping("add")
    public String addDate(){
        //addUser();
        //addCustomer();
        addSupp();
        //addGoods();
        //addOrder();
        //addCustRet();
        return "ok";
    }

    void addCustRet(){
        List<Customer> customers = customerMapper.selectAll();
        customers.forEach( customer -> {
            List<Goods> goodsList = goodsMapper.selectAll();
            goodsList.forEach( goods-> {
                CustRet custRet = new CustRet();
                custRet.setId(null);
                custRet.setCustId(customer.getId());
                custRet.setCustName(customer.getName());
                custRet.setGoodsName(goods.getName());
                custRet.setGoodsId(goods.getId());
                custRet.setRetTime(new Date());
                custRet.setNote("暂无");
                custRet.setNum(20);
                custRetMapper.insert(custRet);
            });
        });
    }

    void addOrder(){
        List<Customer> customers = customerMapper.selectAll();
        customers.forEach( customer -> {
            List<Goods> goodsList = goodsMapper.selectAll();
            goodsList.forEach( goods -> {
                Order order = new Order();
                order.setId(null);
                order.setCustId(customer.getId());
                order.setCustName(customer.getName());
                order.setGoodsId(goods.getId());
                order.setGoodsName(goods.getName());
                order.setNum(20);
                order.setNote("暂无");
                order.setTime(new Date());
                orderMapper.insert(order);
            });
        });
    }

    void jinhuo(){
        List<Supplier> suppliers = supplierMapper.selectAll();
        suppliers.forEach( supplier -> {

            List<Goods> goodsList = goodsMapper.selectAll();
            goodsList.forEach( goods -> {
                Reple reple = new Reple();
                reple.setId(null);
                reple.setGoodsId(goods.getId());
                reple.setGoodsName(goods.getName());
                reple.setEmployeeId(10000000009L);
                reple.setSuppId(supplier.getId());
                reple.setSuppName(supplier.getName());
                reple.setNote("");
                reple.setNum(20);
                reple.setOverdueTime(new Date());
                reple.setPrice(goods.getPurPrice());
                reple.setProduTime(goods.getProduction());
                reple.setPutTime(new Date());

                repleMapper.insert(reple);

            });
        });
    }

    void tuihuo(){
        //插入100条退货数据
        //首先查询到用户数据
        //用户与商品表做乘积
        List<Supplier> suppliers = supplierMapper.selectAll();
        List<Goods> goodsList = goodsMapper.selectAll();
        goodsList.forEach( goods -> {
            RetGoods retGoods = new RetGoods();
            Supplier supplier = new Supplier();
            supplier.setName(goods.getSupplierName());
            supplier = supplierMapper.select(supplier).get(0);
            retGoods.setCustId(supplier.getId());
            retGoods.setCustName(supplier.getName());
            retGoods.setNote("");
            retGoods.setNum(20);
            retGoods.setGoodsId(goods.getId());
            retGoods.setGoodsName(goods.getName());
            retGoods.setTime(new Date());

            retGoodsMapper.insert(retGoods);
        });
    }

    public void addGoods(){
        //插入100条goods
        //首先获取所有的供应商
        Integer j = 0;
        List<Supplier> suppliers = supplierMapper.selectAll();
        suppliers.forEach( supplier -> {

            for ( int i = 0 ; i < 4 ; i ++){
                Goods goods = new Goods();
                goods.setId(null);
                String name1 = "果冻"  + getRandomChar();
                goods.setName(name1);
                goods.setNote("");
                goods.setNum(5000);
                goods.setProduction(new Date());
                goods.setOverdue(new Date());
                goods.setPieway("个");
                goods.setPurPrice(30);
                goods.setPrice(100);
                String name = supplierMapper.selectAll().get((int) (Math.random() * 20)).getName();
                goods.setSupplierName(name);
                goodsMapper.insert(goods);
                addInvenInfoAndInvenWarn(name1);
            }
        });
    }

    public void addInvenInfoAndInvenWarn(String name){
        Goods goods = new Goods();
        goods.setName(name);
        goods = goodsMapper.select(goods).get(0);
        Long goodsId = goods.getId();
        InvenInfo invenInfo = new InvenInfo();
        invenInfo.setGoodsId(goodsId);
        invenInfo.setGoodsName(name);
        invenInfo.setId(null);
        invenInfo.setNote("");
        invenInfo.setTotal(5000);

        InvenWarn invenWarn = new InvenWarn();
        invenWarn.setGoodsId(goodsId);
        invenWarn.setGoodsName(name);
        invenWarn.setOverdueTime(new Date());
        invenWarn.setNum(50);
        invenWarn.setNote("");
        invenWarn.setId(null);

        invenInfoMapper.insert(invenInfo);
        invenWarnMapper.insert(invenWarn);
    }



    public void addCustomer(){
        for (Long i = 10000000055L; i < 10000000075L; i ++){
            Customer customer = new Customer();
            customer.setEmployeeId( 10000000001L + (int)(Math.random() * 20) );
            customer.setId(i);
            customer.setLocation("浙江省温州市王鹤皮革厂");
            customer.setName("江南皮革厂");
            customer.setNote("暂无");
            customer.setPhone(getPhone());
            customerMapper.insert(customer);
        }
    }

    public void addSupp(){
        for (Long i = 10000000055L; i < 10000000075L; i ++){
            Supplier supplier = new Supplier();
            supplier.setId(i);
            String phone = getPhone();
            supplier.setEmployeeId(10000000001L + (int)(Math.random() * 20));
            supplier.setEmail(phone + "@qq.com");
            supplier.setLocation("江南皮革厂");
            supplier.setPhone(phone);
            supplier.setName("王鹤");
            supplierMapper.insert(supplier);

        }
    }

    //向数据库中cha'ru
   //首先向数据库中插入20条员工
    public void addUser(){
        for (Long i = 10000000001L; i <= 10000000020L;i++){
            Employee employee = new Employee();
            employee.setName(getRandomChar() + getRandomChar() + getRandomChar());
            employee.setId(i);
            employee.setPhone(getPhone());
            if (i % 5 == 0){
                employee.setPosition(1);
            }else {
                employee.setPosition(5);
            }
            employeeMapper.insert(employee);
        }

    }



    public static String getPhone(){
        String str = "138";
        for (int i = 0;i < 8;i++){
            str += (int) (Math.random() * 10);
        }
        return str;
    }

    public static String getRandomChar() {
        String str = "";
        int highCode;
        int lowCode;

        Random random = new Random();

        highCode = (176 + Math.abs(random.nextInt(39))); //B0 + 0~39(16~55) 一级汉字所占区
        lowCode = (161 + Math.abs(random.nextInt(93))); //A1 + 0~93 每区有94个汉字

        byte[] b = new byte[2];
        b[0] = (Integer.valueOf(highCode)).byteValue();
        b[1] = (Integer.valueOf(lowCode)).byteValue();

        try {
            str = new String(b, "GBK");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return str;
    }
}
