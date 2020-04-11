package com.ss.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ss.mapper.EmployeeMapper;
import com.ss.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    EmployeeMapper EmployeeMapper;

    public Employee getEmployeeById(Long id) {
        return EmployeeMapper.selectByPrimaryKey(id);
    }

    public PageInfo<Employee> getEmployeeByPage(Integer page) {
        PageHelper.startPage(page,5);
        List<Employee> Employees = EmployeeMapper.selectAll();
        PageInfo<Employee> info = new PageInfo(Employees);
        return info;
    }

    public Boolean addEmployee(Employee Employee) {
        try{
            Employee.setId(null);
            EmployeeMapper.insertSelective(Employee);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    public Boolean delEmployee(Long id) {
        try {
            EmployeeMapper.deleteByPrimaryKey(id);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public Boolean updateEmployee(Employee Employee) {
        try {
            EmployeeMapper.updateByPrimaryKeySelective(Employee);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public PageInfo<Employee> searchByPhoneOrName( String keyword,Integer page) {
        if (keyword == "" || keyword == null){
            new PageInfo<>().setList(null);
        }

        Example example = new Example(Employee.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.orLike("name","%" + keyword + "%").orEqualTo("id",keyword);
        PageHelper.startPage(page,5);
        List<Employee> Employees = EmployeeMapper.selectByExample(example);
        PageInfo<Employee> info = new PageInfo(Employees);
        return info;
    }

    public List<Employee> getAllEmployee() {
        return EmployeeMapper.selectAll();
    }
}
