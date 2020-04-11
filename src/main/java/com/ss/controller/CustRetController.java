package com.ss.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.ss.pojo.CustRet;
import com.ss.service.CustRetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

@Controller
@RequestMapping("custret")
public class CustRetController {
    @Autowired
    CustRetService CustRetService;


    @RequestMapping("{id}")
    public ResponseEntity<CustRet> getCustRetById(@PathVariable("id") Long id){
        CustRet CustRet = CustRetService.getCustRetById(id);
        if (CustRet == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(CustRet);
    }

    @RequestMapping("/page/{page}")
    public ResponseEntity<PageInfo<CustRet>> getCustRetByPage(@PathVariable("page") Integer page){
        PageInfo<CustRet> CustRets = CustRetService.getCustRetByPage(page);
        if (CollectionUtils.isEmpty(CustRets.getList())){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(CustRets);
    }

    @RequestMapping("addCustRet")
    public ResponseEntity<Boolean> addCustRet(@RequestBody CustRet CustRet){
        Boolean bool = CustRetService.addCustRet(CustRet);
        if (bool == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(bool);

    }

    @RequestMapping("delCustRet/{id}")
    public ResponseEntity<Boolean> delCustRet(@PathVariable("id") Long id){
        Boolean bool = CustRetService.delCustRet(id);
        if (bool == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(bool);
    }

    @PutMapping("updateCustRet")
    public ResponseEntity<Boolean> updateCustRet(@RequestBody CustRet CustRet){
        System.out.println(CustRet);
        Boolean bool = CustRetService.updateCustRet(CustRet);
        if (bool == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(bool);
    }

    @GetMapping("search")
    public ResponseEntity<PageInfo<CustRet>> searchByPhoneOrName(@RequestParam("keyword") String keyword,
                                                                  @RequestParam("page") Integer page){
        PageInfo<CustRet>  CustRets = CustRetService.searchByPhoneOrName( keyword,page );
        if ( CollectionUtils.isEmpty(CustRets.getList())){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(CustRets);
    }
}
