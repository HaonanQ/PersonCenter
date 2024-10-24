package com.personcs.usercenter.Service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.personcs.usercenter.Model.domain.Users;
import com.personcs.usercenter.Mapper.UsersMapper;
import com.personcs.usercenter.Service.UsersService;
import org.springframework.stereotype.Service;

/**
* @author Qiuhaonan
* @description 针对表【users(用户表)】的数据库操作Service实现
* @createDate 2024-10-24 19:54:14
*/
@Service
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users>
    implements UsersService {

}




