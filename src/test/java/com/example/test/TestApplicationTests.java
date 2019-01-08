package com.example.test;

import com.example.test.config.RedisUtil;
import com.example.test.entity.Employee;
import com.example.test.mapper.EmployeeMapper;
import io.searchbox.client.JestClient;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestApplicationTests {

    @Autowired
    EmployeeMapper employeeMapper;

    @Autowired
    StringRedisTemplate stringRedisTemplate;


    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    RedisUtil redisUtil;

    @Test
    public void contextLoads() {
        Employee employee = employeeMapper.GetModel(1);
        System.out.println(employee);

        boolean set = redisUtil.set("1", employee);
        //Long add = redisTemplate.opsForSet().add("1", employee);
    }


    @Autowired
    private AmqpTemplate amqpTemplate;

    @Autowired
    private AmqpAdmin amqpAdmin;

    @Autowired
    private JestClient jestClient;

    @Test
    public void  mqTest() throws IOException {

//        //存储的对象
//        Employee emp = new Employee("张三", 22);
//        emp.setId(111);
//        //构建一个索引功能
//        Index index = new Index.Builder(emp).index("test").type("emp").id("222").build();
//
//        //执行
//        jestClient.execute(index);


        //查询表达式
        String json="{\n" +
                "    \"query\" : {\n" +
                "        \"match\" : {\n" +
                "            \"dId\" : 444\n" +
                "        }\n" +
                "    }\n" +
                "}";
        //构建搜索功能
        Search search = new Search.Builder(json).addIndex("test").addType("emp").build();
        //执行
        SearchResult result = jestClient.execute(search);

        System.out.println(result.getJsonString());

        //amqpTemplate.send();

        //amqpTemplate.convertAndSend("amq.direct",);


//        //创建exchange
//        amqpAdmin.declareExchange(new DirectExchange("my-dir"));
//
//        //创建 queues
//        amqpAdmin.declareQueue(new Queue("myemp"));
//
//        //创建绑定关系
//        amqpAdmin.declareBinding(new Binding("myemp" , Binding.DestinationType.QUEUE,"my-dir","my-rout",null));

//        amqpTemplate.convertAndSend("myemp",new Employee("zhansn",12));
    }
}

