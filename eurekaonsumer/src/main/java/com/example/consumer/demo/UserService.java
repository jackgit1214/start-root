package com.example.consumer.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class UserService {
    //    ribbon负载均衡器
    private final LoadBalancerClient loadBalancerClient;

    public UserService(LoadBalancerClient loadBalancerClient) {
        this.loadBalancerClient = loadBalancerClient;
    }

    public List<UserPojo> getUser() {
//        选择需要获取哪一个
//        比如我们想要和eureka_client进行连接
        ServiceInstance instance = this.loadBalancerClient.choose("eureka-client");
        StringBuffer sb = new StringBuffer();
//        拼接出来请求地址
        sb.append("http://").append(instance.getHost()).append(":").append(instance.getPort()).append("/getUser");
        RestTemplate template = new RestTemplate();
//        这里是内部类，我们只需要自有的方法就足够了，不需要要再写其他的了
//        ParameterizedTypeReference<List<UserPojo>> typeReference = new ParameterizedTypeReference<List<UserPojo>>() {};
//        ParameterizedTypeReference<Object> typeReference = new ParameterizedTypeReference<Object>() {
//        };
        MultiValueMap<String, Object> paramMap = new LinkedMultiValueMap<String, Object>();
        paramMap.add("userName", "tom");
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity<MultiValueMap<String, Object>>(paramMap, headers);
//      第一个参数，我们拼接的url，第二个参数，请求参数，第三个返回值类型（本应该为
//      List<UserPojo>的，技术有点菜，不知道怎么写...）
//        ResponseEntity<Object> response;
//        UserPojo[] users = template.postForEntity(sb.toString(),
//                httpEntity,
//                UserPojo.class);
        //UserPojo[] users = template.postForObject(sb.toString(),httpEntity,UserPojo[].class);
        // List<Object> userList = Arrays.asList(users);
        ResponseEntity<List> response=  template.postForEntity(sb.toString(),httpEntity,List.class);
        //Object o = response.getBody();

        List<UserPojo> list = (List<UserPojo>) response.getBody();

        return list;

    }
}

