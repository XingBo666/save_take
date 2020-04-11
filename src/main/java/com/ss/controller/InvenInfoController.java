package com.ss.controller;

import com.github.pagehelper.PageInfo;
import com.ss.pojo.InvenInfo;
import com.ss.service.InvenInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("inveninfo")
public class InvenInfoController {

    @Autowired
    InvenInfoService InvenInfoService;


    @RequestMapping("{id}")
    public ResponseEntity<InvenInfo> getInvenInfoById(@PathVariable("id") Long id){
        InvenInfo InvenInfo = InvenInfoService.getInvenInfoById(id);
        if (InvenInfo == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(InvenInfo);
    }

    @RequestMapping("/page/{page}")
    public ResponseEntity<PageInfo<InvenInfo>> getInvenInfoByPage(@PathVariable("page") Integer page){
        PageInfo<InvenInfo> InvenInfos = InvenInfoService.getInvenInfoByPage(page);
        if (CollectionUtils.isEmpty(InvenInfos.getList())){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(InvenInfos);
    }

    @RequestMapping("addInvenInfo")
    public ResponseEntity<Boolean> addInvenInfo(@RequestBody InvenInfo InvenInfo){
        Boolean bool = InvenInfoService.addInvenInfo(InvenInfo);
        if (bool == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(bool);

    }

    @RequestMapping("delInvenInfo/{id}")
    public ResponseEntity<Boolean> delInvenInfo(@PathVariable("id") Long id){
        Boolean bool = InvenInfoService.delInvenInfo(id);
        if (bool == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(bool);
    }

    @PutMapping("updateInvenInfo")
    public ResponseEntity<Boolean> updateInvenInfo(@RequestBody InvenInfo InvenInfo){
        System.out.println(InvenInfo);
        Boolean bool = InvenInfoService.updateInvenInfo(InvenInfo);
        if (bool == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(bool);
    }

    @GetMapping("search")
    public ResponseEntity<PageInfo<InvenInfo>> searchByPhoneOrName(@RequestParam("keyword") String keyword,
                                                                  @RequestParam("page") Integer page){
        PageInfo<InvenInfo>  InvenInfos = InvenInfoService.searchByPhoneOrName( keyword,page );
        if ( CollectionUtils.isEmpty(InvenInfos.getList())){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(InvenInfos);
    }
}
