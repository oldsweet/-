package com.usc.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.usc.entity.RegisterCode;
import com.usc.entity.User;
import com.usc.mapper.UserMapper;
import com.usc.service.RegisterCodeService;
import com.usc.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.usc.utils.MailUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Random;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Baisong Li
 * @since 2023-04-17
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    RegisterCodeService registerCodeService;

    @Override
    public SaResult login(String email, String password) {
        User user = null;
        try {
            QueryWrapper<User> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("email", email);
            user = this.getOne(queryWrapper);
        } catch (Exception e) {
            e.printStackTrace();
            return SaResult.error("对不起,该账号还没有注册");
        }
        if (user == null) {
            return SaResult.error("对不起,该账号还没有注册");
        } else {
            if (user.getPassword().equals(password)) {
                StpUtil.login(user.getId());
                return SaResult.ok("登录成功").set("token", StpUtil.getTokenValue());
            } else {
                return SaResult.error("密码错误");
            }
        }
    }

    @Override
    public SaResult userInfo() {
        int loginIdAsInt = StpUtil.getLoginIdAsInt();
        User user = this.getById(loginIdAsInt);
        if (user == null) return SaResult.error("用户不存在");
        return SaResult.ok("信息获取成功").setData(user.setPassword(null));
    }

    /**
     * 验证注册码
     * @param email
     * @param password
     * @param code
     * @return
     */
    @Override
    public SaResult verifyRegister(String email, String password, String code) {
        try {
            QueryWrapper<RegisterCode> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("email", email);

            RegisterCode registerCode = registerCodeService.getOne(queryWrapper);
            if (registerCode == null) {
                return SaResult.error("该邮箱还没有使用");
            }
            if (registerCode.getCode().equals(code)) {
                User user = new User();
                user.setUsername(email);
                user.setEmail(email);
                user.setPassword(password);
                this.save(user);
                return SaResult.ok("注册成功");
            } else {
                return SaResult.error("验证码错误");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return SaResult.error("验证失败");
        }
    }

    /**
     * 验证邮箱，发送邮件
     * @param email
     * @return
     */
    @Override
    public SaResult register(String email) {

        try {
            QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
            userQueryWrapper.eq("email",email);
            User user = this.getOne(userQueryWrapper);
            if (user != null){
                return SaResult.error("对不起，该邮箱已经注册");
            }
            // 生成验证码
            int i = new Random().nextInt(1000000);
            String code = String.format("%06d", i);
            RegisterCode codeService = registerCodeService.getOne(new QueryWrapper<RegisterCode>().eq("email", email));
            if (codeService == null){
                RegisterCode registerCode = new RegisterCode();
                registerCode.setCode(code);
                registerCode.setEmail(email);
                registerCode.setCreateTime(new Date(System.currentTimeMillis()));
                registerCodeService.save(registerCode);
            }
            else {
                codeService.setCode(code);
                codeService.setEmail(email);
                codeService.setCreateTime( new Date(System.currentTimeMillis()));
                registerCodeService.updateById(codeService);
            }
            MailUtils.sendMail(email,code); // 发送邮件
            return SaResult.ok("邮件发送成功");
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return SaResult.error("邮件发生失败");
        }
    }
}
