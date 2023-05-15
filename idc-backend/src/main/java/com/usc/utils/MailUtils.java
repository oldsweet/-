package com.usc.utils;

import com.sun.mail.util.MailSSLSocketFactory;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.security.GeneralSecurityException;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

public class MailUtils {
     // java mail 的教程 https://www.runoob.com/java/java-sending-email.html 启动项目前请按照教程配置好服务器和keyWord
    static private String masterMail = "baisongli2023@163.com";
    static private String keyPassword = "ORA***************SAOADGY";
    static private String smtp = "smtp.163.com";

    public static void sendMail(String receiveMail,String code) throws GeneralSecurityException, MessagingException {
        Properties prop = new Properties();
        System.out.println(receiveMail);
        prop.setProperty("mail.host", smtp);
        prop.setProperty("mail.transport.protocol", "smtp");
        prop.setProperty("mail.smtp.auth", "true");
        MailSSLSocketFactory sf = new MailSSLSocketFactory();
        sf.setTrustAllHosts(true);
        prop.put("mail.smtp.ssl.enable", "true");
        prop.put("mail.smtp.ssl.socketFactory", sf);

        // 使用 JavaMail 发送邮件的5个步骤

        // 1. 定义整个应用程序所需要的环境信息的 Session 对象
        // 这一步是qq邮箱才有, 其他邮箱不用
        Session session = Session.getDefaultInstance(prop, new Authenticator() {
            @Override
            public PasswordAuthentication getPasswordAuthentication() {
                // 发件人邮箱 用户名和授权码
                return new PasswordAuthentication(masterMail, keyPassword);
            }
        });

        // 开启 Session debugger 模式, 可以看到邮件发送的运行状态
        session.setDebug(true);

        // 2. 通过 Session得到 transport 对象
        Transport transport = session.getTransport();

        // 3. 使用邮箱用户名和授权码连上邮件服务器 (登陆)
        transport.connect(smtp, masterMail, keyPassword);

        // 4. 创建邮件: 写邮件
        MimeMessage message = new MimeMessage(session);

        // 设置邮件的发件人
        message.setFrom(new InternetAddress(masterMail));

        // 设置邮件的收件人
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(receiveMail));

        // 邮件标题
        message.setSubject("南瓜云盘注册");


        // 准备正文数据
        MimeBodyPart text = new MimeBodyPart();
        text.setContent("<p>欢迎注册南瓜云盘账号，您的验证码为: </p>" +
                "<h1>" + code + "</h1>" +
                "<p>请妥善保管！！！ </p>" +
                "<p>"+ new Date().toLocaleString() + " </p>", "text/html; charset=UTF-8");

        // 描述数据关系
        MimeMultipart mm = new MimeMultipart();
        mm.addBodyPart(text);
        // 把编辑的邮件保存到消息中, 保存更改
        message.setContent(mm);
        message.saveChanges();
        // 5. 发送邮件
        transport.sendMessage(message, message.getAllRecipients());
        // 关闭连接
        transport.close();

    }
}
