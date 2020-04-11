package com.ss.controller;

import com.github.pagehelper.PageInfo;
import com.ss.pojo.Reple;
import com.ss.service.RepleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("reple")
public class RepleController {

    @Autowired
    RepleService RepleService;


    @RequestMapping("{id}")
    public ResponseEntity<Reple> getRepleById(@PathVariable("id") Long id){
        Reple Reple = RepleService.getRepleById(id);
        if (Reple == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(Reple);
    }

    @RequestMapping("/page/{page}")
    public ResponseEntity<PageInfo<Reple>> getRepleByPage(@PathVariable("page") Integer page){
        PageInfo<Reple> Reples = RepleService.getRepleByPage(page);
        if (CollectionUtils.isEmpty(Reples.getList())){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(Reples);
    }

    @RequestMapping("addReple")
    public ResponseEntity<Boolean> addReple(@RequestBody Reple Reple){
        Boolean bool = RepleService.addReple(Reple);
        if (bool == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(bool);

    }

    @RequestMapping("delReple/{id}")
    public ResponseEntity<Boolean> delReple(@PathVariable("id") Long id){
        Boolean bool = RepleService.delReple(id);
        if (bool == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(bool);
    }

    @PutMapping("updateReple")
    public ResponseEntity<Boolean> updateReple(@RequestBody Reple reple){
        System.out.println("方法已经进来了");
        System.out.println(reple);
        Boolean bool = RepleService.updateReple(reple);
        if (bool == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(bool);
    }

    @GetMapping("search")
    public ResponseEntity<PageInfo<Reple>> searchByPhoneOrName(@RequestParam("keyword") String keyword,
                                                                  @RequestParam("page") Integer page){
        PageInfo<Reple>  Reples = RepleService.searchByPhoneOrName( keyword,page );
        if ( CollectionUtils.isEmpty(Reples.getList())){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(Reples);
    }
}
