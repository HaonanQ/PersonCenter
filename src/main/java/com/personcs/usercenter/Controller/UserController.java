package com.personcs.usercenter.Controller;

import com.personcs.usercenter.Model.domain.Users;
import com.personcs.usercenter.Model.domain.request.requestLoginUser;
import com.personcs.usercenter.Model.domain.request.requestRegisterUser;
import com.personcs.usercenter.Service.UsersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
    @Autowired
    private UsersService usersService;

    @PostMapping("/register")
    public Long UserRegister(@RequestBody requestRegisterUser requestRegisterUser) {
        if(requestRegisterUser == null){
            return null;
        }
        String useraccount = requestRegisterUser.getUserAccount();
        String password = requestRegisterUser.getUserPassword();
        String checkpassword = requestRegisterUser.getCheckPassword();
        if(useraccount == null || password == null || checkpassword == null){
            return null;
        }
        Long userId = usersService.registeruser(useraccount, password, checkpassword);
        return userId;
    }

    @PostMapping("/login")
    public Users UserLogin(@RequestBody requestLoginUser requestLoginUser, HttpServletRequest request) {
        if(requestLoginUser == null){
            return null;
        }
        String useraccount = requestLoginUser.getUserAccount();
        String password = requestLoginUser.getUserPassword();
        if(useraccount == null || password == null ){
            return null;
        }
        return usersService.login(useraccount, password, request);
    }
}
