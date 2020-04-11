package com.ss.controller;

import com.github.pagehelper.PageInfo;
import com.ss.mapper.CustomerMapper;
import com.ss.pojo.Customer;
import com.ss.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;


    @RequestMapping("{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable("id") Long id){
        Customer Customer = customerService.getCustomerById(id);
        if (Customer == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(Customer);
    }

    @RequestMapping("/page/{page}")
    public ResponseEntity<PageInfo<Customer>> getCustomerByPage(@PathVariable("page") Integer page){
        PageInfo<Customer> Customers = customerService.getcustomerByPage(page);
        if (CollectionUtils.isEmpty(Customers.getList())){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(Customers);
    }

    @RequestMapping("addCust")
    public ResponseEntity<Boolean> addCust(@RequestBody Customer Customer){
        Boolean bool = customerService.addCustomer(Customer);
        if (bool == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(bool);

    }

    @RequestMapping("delCust/{id}")
    public ResponseEntity<Boolean> delCust(@PathVariable("id") Long id){
        Boolean bool = customerService.delCustomer(id);
        if (bool == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(bool);
    }

    @PutMapping("updateCust")
    public ResponseEntity<Boolean> updateCust(@RequestBody Customer Customer){
        System.out.println(Customer);
        Boolean bool = customerService.updateCustomer(Customer);
        if (bool == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(bool);
    }

    @GetMapping("search")
    public ResponseEntity<PageInfo<Customer>> searchByPhoneOrName(@RequestParam("keyword") String keyword,
                                                                  @RequestParam("page") Integer page){
        PageInfo<Customer>  Customers = customerService.searchByPhoneOrName( keyword,page );
        if ( CollectionUtils.isEmpty(Customers.getList())){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(Customers);
    }
}
