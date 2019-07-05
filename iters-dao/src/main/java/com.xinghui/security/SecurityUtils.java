package com.xinghui.security;

import com.xinghui.enums.HttpCodeEnum;
import com.xinghui.exception.CustException;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.context.SecurityContextHolder;


/**
 * @Author chenkuanxing
 * @Date 2019/6/21
 * security工具类
 */

@Log4j2
public class SecurityUtils {

    private static CustUserDetails userDetails;

    /**
     * 获取登录的用户Id
     *
     * @return
     */
    public static Long getCurrentUserId() {
        Object object = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (object != "anonymousUser") {
            userDetails = (CustUserDetails) object;
            return userDetails.getId();
        } else {
            throw new CustException(HttpCodeEnum.deny.getValue(), HttpCodeEnum.deny.getDesc());
        }
    }

    /**
     * 获取登录用户
     *
     * @return
     */
    public static CustUserDetails getCurrentUser() {
        Object object = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (object != "anonymousUser") {
            userDetails = (CustUserDetails) object;
            return userDetails;
        } else {
            throw new CustException(HttpCodeEnum.deny.getValue(), HttpCodeEnum.deny.getDesc());
        }
    }

}


