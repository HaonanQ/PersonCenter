package com.personcs.usercenter.Service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.personcs.usercenter.Model.domain.Users;
import com.personcs.usercenter.Service.UsersService;
import com.personcs.usercenter.Mapper.UsersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.personcs.usercenter.Constant.UserConstant.USER_LOGIN_STATE;

/**
* @author Qiuhaonan
* @description 针对表【users(用户表)】的数据库操作Service实现
* @createDate 2024-10-24 20:15:58
*/
@Service
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users>
    implements UsersService{

    @Autowired
    private UsersMapper usersMapper;
    private final String salt = "csuser";

    @Override
    public Long registeruser(String account, String password, String checkpassword, String planetcode) {
        if(StringUtils.isEmpty(account)|| StringUtils.isEmpty(password)|| StringUtils.isEmpty(checkpassword)|| StringUtils.isEmpty(planetcode)){
            return -1l;
        }
        if(account.length()<4){
            return -1l;
        }
        if(password.length()<8){
            return -1l;
        }
        if(!password.equals(checkpassword)){
            return -1l;
        }
        //验证账号是否合法
        String RegExp = "^[a-zA-Z0-9]+$";
        Matcher matcher = Pattern.compile(RegExp).matcher(account);
        if(!matcher.find()){
            return -1l;
        }
        //验证星球码是否合法
        if(planetcode.length()<1){
            return -1l;
        }
        //账户不能重复
        QueryWrapper<Users> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userAccount",account);
        long count =  this.count(queryWrapper);
        if(count>0){
            return -1l;
        }
        //星球码不能重复
        QueryWrapper<Users> queryWrapper2 = new QueryWrapper<>();
        queryWrapper2.eq("planetCode",planetcode);
        long count2 =  this.count(queryWrapper2);
        if(count2>0){
            return -1l;
        }
        Users user = new Users();
        String md5password = DigestUtils.md5DigestAsHex((password+salt).getBytes());
        user.setUserAccount(account);
        user.setUserPassword(md5password);
        user.setUsername(account);
        user.setPlanetCode(planetcode);
        if(!this.save(user)){ //保存失败
            return -1l;
        };
        return user.getId();
    }
    public Users login(String account, String password , HttpServletRequest response) {
        //验证账号密码是否正确
        if(StringUtils.isEmpty(account)|| StringUtils.isEmpty(password)){
            return null;
        }
        if(account.length()<4){
            return null;
        }
        if (password.length() < 8){
            return null;
        }
        String md5password = DigestUtils.md5DigestAsHex((password+salt).getBytes());
        QueryWrapper<Users> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userAccount",account);
        queryWrapper.eq("userPassword",md5password);
        Users user = usersMapper.selectOne(queryWrapper);
        if(user==null){
            return null;
        }
        Users safeuser = getSafeUser(user);
        //设置cookie
        response.getSession().setAttribute(USER_LOGIN_STATE,safeuser);
        return safeuser;
    }

    @Override
    public Users getSafeUser(Users user) {
        if(user==null){
            return null;
        }
        //用户脱敏
        Users safeuser = new Users();
        safeuser.setId(user.getId());
        safeuser.setUserAccount(user.getUserAccount());
        safeuser.setUsername(user.getUsername());
        safeuser.setUserPassword(null);
        safeuser.setEmail(user.getEmail());
        safeuser.setAvatarUrl(user.getAvatarUrl());
        safeuser.setUserStatus(user.getUserStatus());
        safeuser.setCreateTime(user.getCreateTime());
        safeuser.setUserrole(user.getUserrole());
        return safeuser;
    }

    @Override
    public Integer userlogout(HttpServletRequest request) {
        request.getSession().removeAttribute(USER_LOGIN_STATE);
        return 1;
    }
}




