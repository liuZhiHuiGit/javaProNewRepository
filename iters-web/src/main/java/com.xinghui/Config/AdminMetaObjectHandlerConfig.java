package com.xinghui.Config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.xinghui.security.SecurityUtils;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Author chenkuanxing
 * @Date 2019/6/21
 * 自动填充配置
 */
@Component
public class AdminMetaObjectHandlerConfig implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        setFieldValByName("createdTime", new Date(), metaObject);
        setFieldValByName("createdUser", SecurityUtils.getCurrentUserId(), metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        setFieldValByName("lastChanged", new Date(), metaObject);
        setFieldValByName("lastChangedUser", SecurityUtils.getCurrentUserId(), metaObject);
    }
}
