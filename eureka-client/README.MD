# 微服务中心provider

1、本模块将为eureka-service模块的服务中心创建服务提供者，创建springBoot项目，加入maven依赖

	<dependency>
	  <groupId>org.springframework.cloud</groupId>
	  <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
	</dependency>
	<dependency>
    	<groupId>org.springframework.boot</groupId>
    	<artifactId>spring-boot-starter-web</artifactId>
    </dependency>
2、在springBoot启动类，增加注解@EnableDiscoveryClient	

3、修改application.yml文件与bootstrap.yml文件（如果没有则创建一个）,application.yml中主要修改端口,bootstrap.yml文件中
   添加以下内容，指明要注册的服务中心，此外指向在eureka-service中设定的服务中心地址。
   
       eureka:
         client:
           service-url:
             defaultZone:  http://server1:8101/eureka
             
4、编写控制类，提供数据服务。此模块中ClientRestController类中提供取得当前 service实例名及具体服务信息的接口，
   引例子中访问地址http://localhost:8080/service返回["eureka2","eureka1","eureka-client"]。、
   访问http://localhost:8080/service/eureka1返回具体服务信息。
   UserController类提供了返回用户信息

# Spring-cloud-config的使用配置与使用
  普通项目应该用不到这个功能，只有在模块繁多，需要频繁更改同样的配置文件时，这个还是有作用的。
  
  1、在maven项目文件中引入依赖
  
      <dependency>
      	<groupId>org.springframework.cloud</groupId>
      	<artifactId>spring-cloud-starter-config</artifactId>
      </dependency>
  
  2、修改bootstrap.yml文件，增加以下部分内容
      
      spring:
        profiles: prod
        application:
          name: eureka-client
        cloud:
          config:
            label: master
            profile: prod
            discovery:
              enabled: true
              service-id: eureka1   
   这里service-id为在eureka-service模块中serviceId,即应用名。
   此模块中应用名为eureka-client,active为dev ，注意cloudConfig中的配置文件名称，必须为eureka-client-dev.yml
   
  3、创建GitAutoRefreshConfig，添加注解@ConfigurationProperties(prefix = "data")，并添加相应属性
     创建ConfigController类
     
  4、访问localhost:8080/autoShow ,应该能得到想要的结果 。  