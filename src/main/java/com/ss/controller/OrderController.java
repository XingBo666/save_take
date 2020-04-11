package com.ss.controller;

import com.github.pagehelper.PageInfo;
import com.ss.pojo.Order;
import com.ss.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("order")
public class OrderController {
    @Autowired
    OrderService OrderService;


    @RequestMapping("{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable("id") Long id){
        Order Order = OrderService.getOrderById(id);
        if (Order == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(Order);
    }

    @RequestMapping("/page/{page}")
    public ResponseEntity<PageInfo<Order>> getOrderByPage(@PathVariable("page") Integer page){
        PageInfo<Order> Orders = OrderService.getOrderByPage(page);
        if (CollectionUtils.isEmpty(Orders.getList())){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(Orders);
    }

    @RequestMapping("addOrder")
    public ResponseEntity<Boolean> addOrder(@RequestBody Order Order){
        Boolean bool = OrderService.addOrder(Order);
        if (bool == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(bool);

    }

    @RequestMapping("delOrder/{id}")
    public ResponseEntity<Boolean> delOrder(@PathVariable("id") Long id){
        Boolean bool = OrderService.delOrder(id);
        if (bool == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(bool);
    }

    @PutMapping("updateOrder")
    public ResponseEntity<Boolean> updateOrder(@RequestBody Order Order){
        System.out.println(Order);
        Boolean bool = OrderService.updateOrder(Order);
        if (bool == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(bool);
    }

    @GetMapping("search")
    public ResponseEntity<PageInfo<Order>> searchByPhoneOrName(@RequestParam("keyword") String keyword,
                                                                  @RequestParam("page") Integer page){
        PageInfo<Order>  Orders = OrderService.searchByPhoneOrName( keyword,page );
        if ( CollectionUtils.isEmpty(Orders.getList())){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(Orders);
    }
}
