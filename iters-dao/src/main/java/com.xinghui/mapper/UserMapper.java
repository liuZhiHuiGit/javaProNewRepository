package com.xinghui.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xinghui.entity.User;
import com.xinghui.security.CustUserDetails;
import org.apache.ibatis.annotations.Param;

public interface UserMapper extends BaseMapper<User> {

    CustUserDetails queryUserPass(@Param("username") String username);

}
