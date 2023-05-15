package com.usc.service;

import cn.dev33.satoken.util.SaResult;
import com.usc.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author Baisong Li
 * @since 2023-04-17
 */
public interface UserService extends IService<User> {

    public SaResult login(String email, String password);

    public SaResult userInfo();

    public SaResult verifyRegister(String email, String password, String code);

    public SaResult register(String email);
}
