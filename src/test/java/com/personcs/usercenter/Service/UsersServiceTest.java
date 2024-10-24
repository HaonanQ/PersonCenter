package com.personcs.usercenter.Service;
import java.util.Date;

import com.personcs.usercenter.Model.domain.Users;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UsersServiceTest {
    @Autowired
    private UsersService usersService;

    @Test
    void get(){
        Users user = new Users();
        user.setId(0L);
        user.setUsername("aaa");
        user.setUserAccount("sdaf");
        user.setAvatarUrl("sdfsdfsdf");
        user.setGender(0);
        user.setUserPassword("sdafsdfsdfsdf");
        user.setPhone("asdf");
        user.setEmail("sdfsdfsdf");
        user.setUserStatus(0);
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        user.setIsDelete(0);
        System.out.println(user.getId());

    }
}