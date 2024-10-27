package com.personcs.usercenter.Controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.personcs.usercenter.Model.domain.Users;
import com.personcs.usercenter.Model.domain.request.requestLoginUser;
import com.personcs.usercenter.Model.domain.request.requestRegisterUser;
import com.personcs.usercenter.Service.UsersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

import static com.personcs.usercenter.Constant.UserConstant.Admin;
import static com.personcs.usercenter.Constant.UserConstant.USER_LOGIN_STATE;

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
    @GetMapping("/search")
    public List<Object> UserSearch(String username, HttpServletRequest request){
        if(!isAdmin(request)){
            return null;
        }
        QueryWrapper<Users> queryWrapper = new QueryWrapper<>();
        if(username!=null){
            queryWrapper.like("username", username);
        }
        List<Users> userlist = usersService.list(queryWrapper);
        return userlist.stream().map(user -> usersService.getSafeUser(user)).collect(Collectors.toList());
    }
    @PostMapping("/delete")
    public boolean UserDelete(@RequestBody Long id,HttpServletRequest request){
        if(!isAdmin(request)){
            return false;
        }
        if (id == null ||id <= 0){
            return false;
        }
        return usersService.removeById(id);
    }
    @GetMapping("/current")
    public Users UserCurrent(HttpServletRequest request){
        Object object = request.getSession().getAttribute(USER_LOGIN_STATE);
        Users user = (Users) object;
        if(user == null){
            return null;
        }
        //todo : 完成用户是否封号
        Long userId = user.getId();
        Users newuser = usersService.getById(userId);
        return usersService.getSafeUser(newuser);
    }

    private boolean isAdmin(HttpServletRequest request){
        Object object = request.getSession().getAttribute(USER_LOGIN_STATE);
        Users user = (Users) object;
        return user != null && user.getUserrole() == Admin;
    }
}
