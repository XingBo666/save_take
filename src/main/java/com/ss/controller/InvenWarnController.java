package com.ss.controller;

import com.github.pagehelper.PageInfo;
import com.ss.pojo.InvenWarn;
import com.ss.service.InvenWarnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("invenwarn")
public class InvenWarnController {
    @Autowired
    InvenWarnService InvenWarnService;


    @RequestMapping("{id}")
    public ResponseEntity<InvenWarn> getInvenWarnById(@PathVariable("id") Long id){
        InvenWarn InvenWarn = InvenWarnService.getInvenWarnById(id);
        if (InvenWarn == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(InvenWarn);
    }

    @RequestMapping("/page/{page}")
    public ResponseEntity<PageInfo<InvenWarn>> getInvenWarnByPage(@PathVariable("page") Integer page){
        PageInfo<InvenWarn> InvenWarns = InvenWarnService.getInvenWarnByPage(page);
        if (CollectionUtils.isEmpty(InvenWarns.getList())){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(InvenWarns);
    }

    @RequestMapping("addInvenWarn")
    public ResponseEntity<Boolean> addInvenWarn(@RequestBody InvenWarn InvenWarn){
        Boolean bool = InvenWarnService.addInvenWarn(InvenWarn);
        if (bool == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(bool);

    }

    @RequestMapping("delInvenWarn/{id}")
    public ResponseEntity<Boolean> delInvenWarn(@PathVariable("id") Long id){
        Boolean bool = InvenWarnService.delInvenWarn(id);
        if (bool == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(bool);
    }

    @PutMapping("updateInvenWarn")
    public ResponseEntity<Boolean> updateInvenWarn(@RequestBody InvenWarn InvenWarn){
        System.out.println(InvenWarn);
        Boolean bool = InvenWarnService.updateInvenWarn(InvenWarn);
        if (bool == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(bool);
    }

    @GetMapping("search")
    public ResponseEntity<PageInfo<InvenWarn>> searchByPhoneOrName(@RequestParam("keyword") String keyword,
                                                                  @RequestParam("page") Integer page){
        PageInfo<InvenWarn>  InvenWarns = InvenWarnService.searchByPhoneOrName( keyword,page );
        if ( CollectionUtils.isEmpty(InvenWarns.getList())){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(InvenWarns);
    }
}
