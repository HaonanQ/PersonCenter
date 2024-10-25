package com.personcs.usercenter.Service;
import java.util.Date;

import com.personcs.usercenter.Model.domain.Users;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class UsersServiceTest {
    @Autowired
    private UsersService usersService;

    @Test
    void test(){
        Users users = new Users();
        users.setUsername("asd");
        users.setUserAccount("asd");
        users.setAvatarUrl("aaaa");
        users.setGender(0);
        users.setUserPassword("aaa");
        users.setPhone("aaa");
        users.setEmail("aaa");
        users.setUserStatus(0);
        users.setCreateTime(new Date());
        users.setUpdateTime(new Date());
        users.setIsDelete(0);
        usersService.save(users);
        System.out.println(users.getId());
    }
    @Test
    void adduser(){
        String username ="qhn!";
        String password ="test12";
        String checkpassword ="test123456";
        System.out.println(usersService.registeruser(username,password,checkpassword));

    }

}