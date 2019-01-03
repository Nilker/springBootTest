package com.example.test.service;

import com.example.test.entity.Employee;
import com.example.test.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
    @Autowired
    EmployeeMapper employeeMapper;

    @Cacheable(cacheNames = "emp",keyGenerator = "myKeyGenerator")
    public Employee GetEmpId(Integer id){
        System.out.println("查询Id："+id);
        Employee employee = employeeMapper.GetModel(id);
        return employee;
    }
}
