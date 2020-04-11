package com.ss.controller;

import com.github.pagehelper.PageInfo;
import com.ss.pojo.RetGoods;
import com.ss.service.RetGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("retgoods")
public class RetGoodsController {
    @Autowired
    RetGoodsService RetGoodsService;


    @RequestMapping("{id}")
    public ResponseEntity<RetGoods> getRetGoodsById(@PathVariable("id") Long id){
        RetGoods RetGoods = RetGoodsService.getRetGoodsById(id);
        if (RetGoods == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(RetGoods);
    }

    @RequestMapping("/page/{page}")
    public ResponseEntity<PageInfo<RetGoods>> getRetGoodsByPage(@PathVariable("page") Integer page){
        PageInfo<RetGoods> RetGoodss = RetGoodsService.getRetGoodsByPage(page);
        if (CollectionUtils.isEmpty(RetGoodss.getList())){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(RetGoodss);
    }

    @RequestMapping("addRetGoods")
    public ResponseEntity<Boolean> addRetGoods(@RequestBody RetGoods RetGoods){
        Boolean bool = RetGoodsService.addRetGoods(RetGoods);
        if (bool == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(bool);

    }

    @RequestMapping("delRetGoods/{id}")
    public ResponseEntity<Boolean> delRetGoods(@PathVariable("id") Long id){
        Boolean bool = RetGoodsService.delRetGoods(id);
        if (bool == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(bool);
    }

    @PutMapping("updateRetGoods")
    public ResponseEntity<Boolean> updateRetGoods(@RequestBody RetGoods RetGoods){
        System.out.println(RetGoods);
        Boolean bool = RetGoodsService.updateRetGoods(RetGoods);
        if (bool == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(bool);
    }

    @GetMapping("search")
    public ResponseEntity<PageInfo<RetGoods>> searchByPhoneOrName(@RequestParam("keyword") String keyword,
                                                                  @RequestParam("page") Integer page){
        PageInfo<RetGoods>  RetGoodss = RetGoodsService.searchByPhoneOrName( keyword,page );
        if ( CollectionUtils.isEmpty(RetGoodss.getList())){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(RetGoodss);
    }
}
