//package com.ndz.tirana.mongodb;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.data.mongodb.core.MongoTemplate;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.Date;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class PersonServiceTest {
//
//
//
//    @Autowired
//    private MongoTemplate mongoTemplate;
//
//    /**
//     * 插入文档
//     * @throws Exception
//     */
//    @Test
//    public void insert() throws Exception {
//        Person person =new Person();
//        person.setId(1l);
//        person.setUserName("张三");
//        person.setPassWord("123456");
//        person.setCreateTime(new Date());
//        mongoTemplate.insert(person);
//    }
//
//
//    /**
//     * 自定义集合，插入文档
//     * @throws Exception
//     */
//    @Test
//    public void insertCustomCollection() throws Exception {
//        Person person =new Person();
//        person.setId(1l);
//        person.setUserName("张三");
//        person.setPassWord("123456");
//        person.setCreateTime(new Date());
//        mongoTemplate.insert(person, "custom_person");
//    }
//
//    /**
//     * 根据索引名称移除索引
//     */
//    @Test
//    public void removeIndex() {
//        // 设置字段名称
//        String field = "userName";
//        // 删除索引
//        mongoTemplate.getCollection("persons").dropIndex(field);
//    }
//}
