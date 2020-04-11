package com.ss.controller;

import com.github.pagehelper.PageInfo;
import com.ss.pojo.Goods;
import com.ss.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("goods")
public class GoodsController {
    @Autowired
    GoodsService GoodsService;

    @GetMapping("getAllGoods")
    public ResponseEntity<List<Goods>> getAllGoods(){
        List<Goods> goodsList = GoodsService.getAllGoods();
        if (CollectionUtils.isEmpty(goodsList)){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(goodsList);
    }


    @RequestMapping("{id}")
    public ResponseEntity<Goods> getGoodsById(@PathVariable("id") Long id){
        Goods Goods = GoodsService.getGoodsById(id);
        if (Goods == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(Goods);
    }

    @RequestMapping("/page/{page}")
    public ResponseEntity<PageInfo<Goods>> getGoodsByPage(@PathVariable("page") Integer page){
        PageInfo<Goods> Goodss = GoodsService.getGoodsByPage(page);
        if (CollectionUtils.isEmpty(Goodss.getList())){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(Goodss);
    }

    @RequestMapping("addGoods")
    public ResponseEntity<Boolean> addGoods(@RequestBody Goods Goods){
        Boolean bool = GoodsService.addGoods(Goods);
        if (bool == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(bool);

    }

    @RequestMapping("delGoods/{id}")
    public ResponseEntity<Boolean> delGoods(@PathVariable("id") Long id){
        Boolean bool = GoodsService.delGoods(id);
        if (bool == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(bool);
    }

    @PutMapping("updateGoods")
    public ResponseEntity<Boolean> updateGoods(@RequestBody Goods Goods){
        System.out.println(Goods);
        Boolean bool = GoodsService.updateGoods(Goods);
        if (bool == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(bool);
    }

    @GetMapping("search")
    public ResponseEntity<PageInfo<Goods>> searchByPhoneOrName(@RequestParam("keyword") String keyword,
                                                                  @RequestParam("page") Integer page){
        PageInfo<Goods>  Goodss = GoodsService.searchByPhoneOrName( keyword,page );
        if ( CollectionUtils.isEmpty(Goodss.getList())){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(Goodss);
    }
}
