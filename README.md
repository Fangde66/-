# 基于spring boot的后台员工部门数据管理平台
## 项目介绍
- 一个初学Java的人，为了更好的理解Java项目及其业务功能，前后端之间数据是怎样传输，页面是怎么被渲染展示而开发的第一个项目
- 涉及的技术：基于SpringBoot开发的后台接口服务，maven，阿里云OSS文件上传、MySQL数据库、redis（仅涉及初步使用）、spring AOP，以及基于vue工程化和Element plus组件开发的前端项目打包后的文件

## 环境依赖
- JDK 17
- Maven 3.6+
- MySQL 8.0
- Redis 8.8.0

## 项目结构
### 1.后端部分（分模块开发）
- tlias-parents：父工程，不写代码，仅进行依赖管理
- tlias-common1：存放常量类
- tlias-pojo：存放实体类
- tlias-utils：存放工具类，如生成token，阿里云OSS文件上传
- tlias-web-management：业务逻辑主体模块，包含控制层、服务层、持久层，AOP切面编程，登录校验（拦截器/过滤器），全局异常处理类
### 2.sql建表语句
- tlias.sql：包括测试数据
- 注意：通过登录进入的人员，会将密码进行MD5加密处理后再与数据库中的密码进行匹配，而测试数据中的密码并非加密处理后的密码
（这里的想法是做一个注册功能，将注册时输入的密码进行MD5加密后存储到数据库，增加数据的安全性，但注册功能还没做）
- 所以想要正常运行可以注释tlias-web-management/src/main/java/com/itfd/service/impl/EmpServiceImpl.java中加密部分的代码
### 3.nginx反向代理配置模板
- nginx.conf：nginx配置文件（后端8080端口）
- html：前端项目打包文件
## 本地启动步骤 
1. 修改tlias-web-management/src/main/resources/application.yml中mysql连接配置（换成自己的用户名和密码）
2. 修改tlias-web-management/src/main/resources/application-dev.yml中redis的连接配置（换成自己的）
3. 修改tlias-web-management/src/main/resources/logback.xml中存放日志的地方，换成自己想存放的地方，默认是D盘创建一个tlias_log文件夹，按照日期存储
4. 配置系统环境变量 ALIBABA_CLOUD_ACCESS_KEY_ID、ALIBABA_CLOUD_ACCESS_KEY_SECRET ，配置自己的阿里云OSS
5. 将nignx中html文件夹中的文件全部替换为nginx/html中的文件，修改conf中的nginx.conf文件
6. 启动nginx.exe展示前端页面，启动后端，运行TliasWebManagementApplication.java
## 结果展示
### 登录界面
<img width="2557" height="1121" alt="image" src="https://github.com/user-attachments/assets/d8c615e0-2234-4a44-9a57-b68200b9ef1e" />

### 员工管理页面
<img width="2549" height="1473" alt="image" src="https://github.com/user-attachments/assets/0095cfa5-b0f2-4a6d-baef-790ff7dd3375" />

## 结语
应该没人会真的去部署吧，这个项目本身的意义就如我刚开始所说，
是为了让我自己更好的理解Java项目及其业务功能，前后端之间数据是怎样传输，页面是怎么被渲染展示而开发的一个项目，
当我再次维护这个项目的时候只有两种可能，其一是我成为了一名优秀的程序员，想要完善曾经自己做的第一个项目；其二是我决定放弃做程序员，想要走之前完善一下自己第一个项目
