package com.personcs.usercenter.Service;

import com.personcs.usercenter.Model.domain.Users;
import com.baomidou.mybatisplus.extension.service.IService;
import com.personcs.usercenter.Respons.BaseResponse;

import javax.servlet.http.HttpServletRequest;

/**
* @author Qiuhaonan
* @description 针对表【users(用户表)】的数据库操作Service
* @createDate 2024-10-24 20:15:58
*/
public interface UsersService extends IService<Users> {

    BaseResponse<Long> registeruser(String account, String password, String checkpassword);
    BaseResponse<Users> login(String account, String password , HttpServletRequest response);

    Users getSafeUser(Users user);
    Integer userlogout(HttpServletRequest request);
}
