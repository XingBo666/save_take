package com.ss.controller;

import com.github.pagehelper.PageInfo;
import com.ss.pojo.Employee;
import com.ss.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("employee")
public class EmployeeController {
    @Autowired
    EmployeeService EmployeeService;


    @RequestMapping("getAllEmployee")
    public ResponseEntity<List<Employee>> getAllEmployee(){
        List<Employee> employees = EmployeeService.getAllEmployee();
        if (CollectionUtils.isEmpty(employees)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(employees);
    }

    @RequestMapping("{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") Long id){
        Employee Employee = EmployeeService.getEmployeeById(id);
        if (Employee == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(Employee);
    }

    @RequestMapping("/page/{page}")
    public ResponseEntity<PageInfo<Employee>> getEmployeeByPage(@PathVariable("page") Integer page){
        PageInfo<Employee> Employees = EmployeeService.getEmployeeByPage(page);
        if (CollectionUtils.isEmpty(Employees.getList())){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(Employees);
    }

    @RequestMapping("addEmployee")
    public ResponseEntity<Boolean> addEmployee(@RequestBody Employee Employee){
        Boolean bool = EmployeeService.addEmployee(Employee);
        if (bool == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(bool);

    }

    @RequestMapping("delEmployee/{id}")
    public ResponseEntity<Boolean> delEmployee(@PathVariable("id") Long id){
        Boolean bool = EmployeeService.delEmployee(id);
        if (bool == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(bool);
    }

    @PutMapping("updateEmployee")
    public ResponseEntity<Boolean> updateEmployee(@RequestBody Employee Employee){
        System.out.println(Employee);
        Boolean bool = EmployeeService.updateEmployee(Employee);
        if (bool == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(bool);
    }

    @GetMapping("search")
    public ResponseEntity<PageInfo<Employee>> searchByPhoneOrName(@RequestParam("keyword") String keyword,
                                                                  @RequestParam("page") Integer page){
        PageInfo<Employee>  Employees = EmployeeService.searchByPhoneOrName( keyword,page );
        if ( CollectionUtils.isEmpty(Employees.getList())){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(Employees);
    }
}
