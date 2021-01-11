利用CONSUL做为服务注册中心，搭建Provider与Consumer项目，以及作为配置中心
1、consul的安装与使用

2、搭建服务提供者与消费用

3、高可用的搭建

4、配置中心用法

#consul windows下的安装与使用

1、添加环境变量：CONSUL_HOME 指定consul所在路径

2、更改path变量，添加%CONSUL_HOME%
   命令行窗口运行：consul 出现信息，则配置成功
   
3、服务中心启动方式：

	命令行执行：consul agent -dev，
    默认节点名称为主机名，当主机名包含.时会有问题，可以加节点 -node test1 指定节点名 test1
    访问http://127.0.0.1:8500 出现界面表示成功
    使用 consul members 命令查看 当前节点状态，IP等信息
    
4、服务中心停止：

     consul leave 
     出现 Graceful leave complete 字样 ，说明停止成功。
     
# spring cloud consul 搭建服务提供者与消费用
    这里用一个项目实现，提供者与消费者，分别启动

   1、创建Spring boot 项目 ,pom 参考consul-server 例子中的pom 文件
   
   2、分别定义提供者与消息者了配置文件，
   
   3、提供者配置文件 application.yml 与 bootstrap.yml 内容参考本项目中的文件
             
   4、消费者配置文件 application-consumer.yml参见项目中的文件 
   
   5、分别创建提供者与消费者控制类如 ConsumerController与ConsumerController
   
   6、分别创建ConsulProviderApplication,ConsulProviderApplication SpringBoot启动类，
      分别启动，消费者启动时增加启动参数 --spring.profiles.active=consumer
      
      
   到此访问相应restful接口，应该能得到相应的结果。
   
   
   
#实现高可用的配置 

  1、更改提供者application.yml配置文件，修改为如下内容
     项目中的文件已实现此配置。
     
      ---
      spring:
        application:
          name: consul-provider1
      
      server:
        port: 8201
      
      ---
      spring:
        application:
          name: consul-provider2
      
      server:
        port: 8202
  
   2、启动两个提供者，分别增加启动参数
      这里注意IDEA中的启动参数的方式，也可以写到VM参数中，写法也不一样，
        以下写法是vm运行参数
                    
      -Dspring.profiles.active=consul-provider1
      -Dspring.profiles.active=consul-provider2
      
   3、访问消费者的 RESTful 接口地址，可以在服务提供者后台看到每次请求调用的服务实例。   
   
#consul 作为配置中心的用法
      
   1、maven项目增加依赖包,如果引用spring-cloud-starter-consul-all包，则不用再添加，
      项目中就是此方式
     
     <dependency>
         <groupId>org.springframework.cloud</groupId>
         <artifactId>spring-cloud-starter-consul-config</artifactId>
     </dependency>
     <dependency>
         <groupId>org.springframework.boot</groupId>
         <artifactId>spring-boot-starter-actuator</artifactId>
     </dependency>
     
   2、bootstrap.xml文件中增加以下配置项，注意必须 在此文件中添加
   
       consul:
         config: #以下应用consul-config的配置
           enabled: true    # 启用配置中心
           format: YAML
           data-key: data  # 也就是 consul 中 key/value 中的 key
           prefix: config         # 可以理解为配置文件所在的最外层目录
           defaultContext: consul-provider1  # 可以理解为 mysql_config 的上级目录 
           
   3、这里要注意JDK的版本，要高版本，低版本有BUG，这里用的jdk1.8_271，否者format参数会有问题
      为此问题高度2个多小时，
   
   4、编写配置文件实体类，如MySqlComplexConfig，添加@ConfigurationProperties(prefix = "mysql")
     （代码见项目）
     
   5、添加控制类，如ConfigController，调取CONSUL参数，访问页面，即可得到结果。
      修改consul中配置参数内容，刷新页面即可，不用重启或与cloud-bus结合使用。