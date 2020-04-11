package com.ss.controller;


import com.github.pagehelper.PageInfo;
import com.ss.pojo.Supplier;
import com.ss.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("supplier")
public class SupplierController {

    @Autowired
    SupplierService supplierService;


    @RequestMapping("getAllSupp")
    public ResponseEntity<List<Supplier>> getAllSupp(){
        List<Supplier> suppliers = supplierService.getAllSupp();
        if (CollectionUtils.isEmpty(suppliers)){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(suppliers);
    }

    @RequestMapping("{id}")
    public ResponseEntity<Supplier> getSupplierById(@PathVariable("id") Long id){
        Supplier supplier = supplierService.getSupplierById(id);
        if (supplier == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(supplier);
    }

    @RequestMapping("/page/{page}")
    public ResponseEntity<PageInfo<Supplier>> getSupplierByPage(@PathVariable("page") Integer page){
        PageInfo<Supplier> suppliers = supplierService.getSupplierByPage(page);
        if (CollectionUtils.isEmpty(suppliers.getList())){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(suppliers);
    }

    @RequestMapping("addSupp")
    public ResponseEntity<Boolean> addSupp(@RequestBody Supplier supplier){
        Boolean bool = supplierService.addSupp(supplier);
        if (bool == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(bool);

    }

    @RequestMapping("delSupp/{id}")
    public ResponseEntity<Boolean> delSupp(@PathVariable("id") Long id){
        Boolean bool = supplierService.delSupp(id);
        if (bool == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(bool);
    }

    @PutMapping("updateSupp")
    public ResponseEntity<Boolean> updateSupp(@RequestBody Supplier supplier){
        System.out.println(supplier);
        Boolean bool = supplierService.updateSupp(supplier);
        if (bool == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(bool);
    }

    @GetMapping("search")
    public ResponseEntity<PageInfo<Supplier>> searchByPhoneOrName(@RequestParam("keyword") String keyword,
                                                              @RequestParam("page") Integer page){
        PageInfo<Supplier>  suppliers = supplierService.searchByPhoneOrName( keyword,page );
        if ( CollectionUtils.isEmpty(suppliers.getList())){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(suppliers);
    }

}
