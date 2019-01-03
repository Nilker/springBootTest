package com.example.test;

import com.example.test.entity.Employee;
import com.example.test.mapper.EmployeeMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestApplicationTests {

    @Autowired
    EmployeeMapper employeeMapper;

    @Autowired
    StringRedisTemplate stringRedisTemplate;


    @Autowired
    RedisTemplate redisTemplate;

    @Test
    public void contextLoads() {
        Employee employee = employeeMapper.GetModel(1);
        System.out.println(employee);

        Integer append = stringRedisTemplate.opsForValue().append("1", "111");
    }

}

