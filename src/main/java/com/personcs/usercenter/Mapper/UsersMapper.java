package com.personcs.usercenter.Mapper;

import com.personcs.usercenter.Model.domain.Users;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author Qiuhaonan
* @description 针对表【users(用户表)】的数据库操作Mapper
* @createDate 2024-10-24 20:15:58
* @Entity com.personcs.usercenter.Model.domain.Users
*/
@Mapper
public interface UsersMapper extends BaseMapper<Users> {

}




