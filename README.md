<p align="center">
<img src=".\logo.png" style="zoom:33%;" />
</p>
<h3 align="center">南瓜云盘</h3>
<p align="center">一个轻量的在线存储云盘</p>

# 平台介绍
南瓜云盘是一个基于`vue+springboot +MyBatis-plus`的轻量级的云盘系统。通过该系统，用户将文件存储到指定的云存储平台。

# 后端配置启动

1. 在安装完maven的配置依赖后，修改好`application.yml`中的配置项，就完成了项目的基本配置。

2. 项目提供了代码生成器，在配置好`CodeGenerator`中的配置项后，运行该文件，就可以根据数据库表项为您生成`java`项目模板文件。

3. **文件上传功能**，请按照文档配置好`utils`中`COSUtils`（腾讯云）和`OSSUtils`（阿里云）中的配置项，当然你也可以拓展其它的文件存储平台，并在`service\impl\FileServiceImpl`中拓展改写。

   - 腾讯云对象存储`java`文档https://cloud.tencent.com/document/product/436/10199

   - 阿里云对象存储`java`文档 https://help.aliyun.com/document_detail/32007.html?spm=a2c4g.52834.0.0.77f962a9dJwzE0

4. **邮箱注册功能**，请按照文档配置好`utils`中`MailUtils`中的配置项，如果你需要改写登录验证的逻辑，可以在`service/impl/UserServiceImpl`中改写register（验证邮箱并且发送注册邮件）和`verifyRegister`（验证注册码）的代码逻辑。

   - `java mail`文档https://javaee.github.io/javamail/

   - 后端权限验证，请参考`sa-token`https://sa-token.cc/doc.html#/use/at-check



# 前端配置启动

1. `npm install`按照好项目所需要的依赖
2. 在`src/api/config`中配置好`axios.defaults.baseURL`(后端地址：后端端口)
3. `src/api`目录下定义了与后端交互的接口，您可以拓展改写
4. `npm run dev`启动项目





