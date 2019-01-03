package com.example.test.mapper;

import com.example.test.entity.Employee;
import org.apache.ibatis.annotations.*;

@Mapper
public interface EmployeeMapper {

    @Select("select * from employee where id=#{id}")
    public Employee GetModel(Integer id);

    @Update("update employee set lastName=#{lastName} ,email=#{email} ,gender=#{gender} d_id=#{dId}  where id=#{id}")
    public void Upate(Employee employee);

    @Delete("delete from employee where id=#{id}")
    public void Del(Integer id);

    @Insert("Insert into employee (lastName,email,gender,d_id) values (#lastName,#email,#gender,#did)")
    public void  Add(Employee model);
}
