package com.itmuch.contentcenter.service.content;

import com.alibaba.nacos.client.naming.NacosNamingService;
import com.itmuch.contentcenter.dao.user.ShareMapper;
import com.itmuch.contentcenter.domain.dto.content.ShareDTO;
import com.itmuch.contentcenter.domain.dto.user.UserDTO;
import com.itmuch.contentcenter.domain.entity.content.Share;
import com.itmuch.contentcenter.feignclient.UserCenterFeignClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class ShareService {
    private final RestTemplate restTemplate;
    private final ShareMapper shareMapper;
    private final UserCenterFeignClient userCenterFeignClient;

    @Autowired
    DiscoveryClient discoveryClient;

   // http://127.0.0.1:8010/shares/1 即可访问，然后跨服务去拿到昵称。


    public ShareDTO findById(Integer id){
        Share share = this.shareMapper.selectByPrimaryKey(id);
        Integer userId=share.getUserId();
 //      UserDTO userDTO = this.userCenterFeignClient.findById(userId);
//        String targetUrl = "Http://usercenterprovider/users/{id}";
//
//        UserDTO userDTO= this.restTemplate.getForObject(
//                targetUrl,
//                UserDTO.class,userId
//        );

        /**
         * Robbin 代替下面
          */
//        List<ServiceInstance> instances = this.discoveryClient.getInstances("usercenterprovider");
//  //      System.out.println(instances.size());
//        List<String> targetUrlS= instances.stream().map(instance -> instance.getUri().toString() + "/users/{id}")
//                .collect(Collectors.toList());
//
//        int i = ThreadLocalRandom.current().nextInt(targetUrlS.size());
//        String targetUrl = targetUrlS.get(i);
//        log.info("请求的负载均衡的一个实例是：{}",targetUrl);
//                UserDTO userDTO= this.restTemplate.getForObject(
//                        targetUrl,
//                UserDTO.class,userId
//        );


                UserDTO userDTO= this.restTemplate.getForObject(
                        "http://usercenterprovider/users/{id}",
                UserDTO.class,userId
        );




        ShareDTO shaeDTO =new ShareDTO();
        BeanUtils.copyProperties(share,shaeDTO);
        shaeDTO.setWxNickName(userDTO.getWxNickname());




        return shaeDTO;


}

//    public static void main(String[] args) {
//        RestTemplate restTemplate = new RestTemplate();
//        String forObject = restTemplate.getForObject(
//                "http://localhost:8082/users/1",
//                String.class
//
//        );
//        System.out.println(forObject);
//    }


}
