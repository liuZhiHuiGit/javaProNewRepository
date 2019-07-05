package com.xinghui.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("s_user")
public class User {
    /**
     * 编号
     */
    private Long id;
    /**
     * 账号
     */
    private String userCode;
    /**
     * 密码
     */
    private String password;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 性别
     */
    private Integer sex;

    /**
     * 手机号码
     */
    private String mobileNo;
    /**
     * 邮件
     */
    private String email;
    /**
     * 状态： 1:正常 -1:删除
     */
    private Integer status;
    /**
     * 创建时间
     */
    private Date createdTime;
    /**
     * 创建人
     */
    private Long createdUser;
    /**
     * 最后更新时间
     */
    private Date lastChanged;
    /**
     * 最后更新人
     */
    private Long lastChanged_user;
}
