package com.personcs.usercenter.Controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.personcs.usercenter.Model.domain.Users;
import com.personcs.usercenter.Model.domain.request.requestLoginUser;
import com.personcs.usercenter.Model.domain.request.requestRegisterUser;
import com.personcs.usercenter.Respons.BaseResponse;
import com.personcs.usercenter.Respons.ErrorCode;
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
    public BaseResponse<Long> UserRegister(@RequestBody requestRegisterUser requestRegisterUser) {
        if(requestRegisterUser == null){
            return new BaseResponse<>(ErrorCode.PARAMS_ERROR,"参数为空");
        }
        String useraccount = requestRegisterUser.getUserAccount();
        String password = requestRegisterUser.getUserPassword();
        String checkpassword = requestRegisterUser.getCheckPassword();
//        String planetcode = requestRegisterUser.getPlanetCode();
        if(useraccount == null || password == null || checkpassword == null ){
            return new BaseResponse<>(ErrorCode.LESSPRAM,ErrorCode.LESSPRAM.getMessage());
        }
        BaseResponse<Long> userId = usersService.registeruser(useraccount, password, checkpassword);
        return new BaseResponse<>(ErrorCode.SUCCESSREGISTER.getCode(),userId.getData(),ErrorCode.SUCCESSREGISTER.getMessage());
    }

    @PostMapping("/login")
    public BaseResponse<Users> UserLogin(@RequestBody requestLoginUser requestLoginUser, HttpServletRequest request) {
        if(requestLoginUser == null){
            return new BaseResponse<>(ErrorCode.PARAMS_ERROR,"参数为空");
        }
        String useraccount = requestLoginUser.getUserAccount();
        String password = requestLoginUser.getUserPassword();
        if(useraccount == null || password == null ){
            return new BaseResponse<>(ErrorCode.LESSPRAM,ErrorCode.LESSPRAM.getMessage());
        }
        BaseResponse<Users> user = usersService.login(useraccount, password, request);
        return new BaseResponse<>(user);
    }

    /**
     * 用户注销
     * @param request
     * @return
     */
    @PostMapping("/logout")
    public BaseResponse<Integer> UserLogout(HttpServletRequest request){
        if(request == null){
            return new BaseResponse<>(ErrorCode.PARAMS_ERROR,null,"参数为空");
        }
        usersService.userlogout(request);
        return new BaseResponse<>(ErrorCode.SUCCESSLogout.getCode(),null,ErrorCode.SUCCESSLogout.getMessage());
    }
    @GetMapping("/search")
    public BaseResponse<List<Users>> UserSearch(String username, HttpServletRequest request){
        if(!isAdmin(request)){
            return new BaseResponse<>(ErrorCode.NO_AUTH.getCode(),null,ErrorCode.NO_AUTH.getMessage());
        }
        QueryWrapper<Users> queryWrapper = new QueryWrapper<>();
        if(username!=null){
            queryWrapper.like("username", username);
        }
        List<Users> userlist = usersService.list(queryWrapper);
        List<Users> safeuserlist = userlist.stream().map(user -> usersService.getSafeUser(user)).collect(Collectors.toList());
        return new BaseResponse<>(ErrorCode.SUCCESS_UPDATE_INFO.getCode(),safeuserlist,ErrorCode.SUCCESS_UPDATE_INFO.getMessage());
    }
    @PostMapping("/delete")
    public BaseResponse<Integer> UserDelete(@RequestBody Long id,HttpServletRequest request){
        if(!isAdmin(request)){
            return new BaseResponse<>(ErrorCode.NO_AUTH.getCode(),null,ErrorCode.NO_AUTH.getMessage());
        }
        if (id == null ||id <= 0){
            return new BaseResponse<>(ErrorCode.FAIR_DELETE_USER.getCode(),null,ErrorCode.FAIR_DELETE_USER.getMessage());
        }
        boolean result = usersService.removeById(id);
        Integer ncode = result ? 1:0;
        return new BaseResponse<>(ErrorCode.SUCESS_DELETE_USER.getCode(),ncode,ErrorCode.SUCESS_DELETE_USER.getMessage());
    }
    @GetMapping("/current")
    public BaseResponse<Users> UserCurrent(HttpServletRequest request){
        Object object = request.getSession().getAttribute(USER_LOGIN_STATE);
        Users user = (Users) object;
        if(user == null){
            return new BaseResponse<>(ErrorCode.PARAMS_ERROR,null,"参数为空");
        }
        //todo : 完成用户是否封号
        Long userId = user.getId();
        Users newuser = usersService.getById(userId);
        Users safeuser = usersService.getSafeUser(newuser);
        return new BaseResponse<>(ErrorCode.SUCCESS_UPDATE_INFO.getCode(),safeuser,ErrorCode.SUCCESS_UPDATE_INFO.getMessage());
    }

    private boolean isAdmin(HttpServletRequest request){
        Object object = request.getSession().getAttribute(USER_LOGIN_STATE);
        Users user = (Users) object;
        return user != null && user.getUserRole() == Admin;
    }
}
