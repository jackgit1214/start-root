利用Spring Cloud Netflix框架中的Eureka搭建服务注册中心，服务中心是微服务架构的核心功能。
另外,Netflix框架还有其它一系列框架集合，如Ribbon、Zuul、Hystrix ，有时间一并研究下。

# 模块环境
  Spring cloud 版本：Hoxton.SR9
  JDK版本： 1.8_271
  SpringBoot 版本： 2.3.5.RELEASE 
  开发环境：idea 
  
# 模块说明
  模块主要以实现服务注册中心为主，实现很简单，引入maven依赖，启动springBoot项目即可，
  使用搭建过程中为配置高可用环境，在IDEA中配置了两个YML文件。
  模块中引用了spring-cloud-config,作为了简单配置
  
# 服务中心简单配置说明
## 1、POM文件中引入依赖
    <dependency>
  	  <groupId>org.springframework.cloud</groupId>
  	  <artifactId>spring-cloud-starter-netflix-eureka-server</artifactId>
    </dependency>
## 2、创建启动类与单独的配置文件
    1）、创建singleBootstrap.yml文件，配置端口为8100，
       将register-with-eureka与fetch-registry设置为false，目的是暂不作为服务提供者
       具体参见模块中文件内容

    2）、创建SingleServiceApplication类，启动时运用singleBootstrap.yml
   
    3)、访问http://localhost:8100 出现Eureka界面，则配置成功
    
    4）、更改register-with-eureka与fetch-registry为true，
       同时在启动类添加@EnableEurekaClient注解，则可将此作用服务提供者
    
# 高可用配置说明  
    1)、windows下修改主机hosts文件，增加两行内容，分别代表两个服务器
        127.0.0.1        server1
        127.0.0.1        server2 
    2）、修改项目application.yml文件，spring.profiles.active=server1，指定active默认为server1
    3）、分别创建application-server1.yml与application-server2.yml，复制singleBootstrap.yml内容，
        更改端口号与hostname属性，
    4）、配置两个server，指定programer arguments参数分别为
         --spring.profiles.active=server1与--spring.profiles.active=server2 分别启动
         
    5)、register-with-eureka与fetch-registry分别设为true,分别访问相应地址验证,可以看到两个中心是同步的
    
# spring-cloud-config基本应用与配置
    在applicaton.xml增加config的配置片段，如下
    spring:
      cloud:
        config:
          server:
            git:
              uri: https://github.com/jackgit1214/start-root #配置文件所在仓库
              username: XXXXXX
              password: XXXXXX
              default-label: master #配置文件分支
              search-paths: cloudConfig/config  #配置文件所在根目录
            prefix: /config