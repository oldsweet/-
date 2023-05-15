package com.usc.controller;


import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaIgnore;
import cn.dev33.satoken.util.SaResult;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.usc.entity.RegisterCode;
import com.usc.service.RegisterCodeService;
import com.usc.service.UserService;
import com.usc.utils.MailUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import java.security.GeneralSecurityException;
import java.sql.Date;
import java.sql.Time;
import java.util.Random;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Baisong Li
 * @since 2023-04-17
 */
@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    RegisterCodeService registerCodeService;
    @SaIgnore
    @PostMapping("/login")
    public SaResult login(String email,String password)
    {
        return userService.login(email,password);
    }

    @SaCheckLogin
    @PostMapping("/user_info")
    public SaResult userInfo()
    {
        return userService.userInfo();
    }
    @PostMapping("/verify_register")
    public SaResult verifyRegister(String email,String password,String code) {
        return userService. verifyRegister( email, password, code) ;
    }
    @PostMapping("/register")
    public SaResult register(String email) {
        return userService. register( email);

    }
}
