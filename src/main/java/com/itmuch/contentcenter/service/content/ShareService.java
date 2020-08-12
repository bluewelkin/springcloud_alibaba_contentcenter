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
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class ShareService {
    private final RestTemplate restTemplate;
    private final ShareMapper shareMapper;
    private final UserCenterFeignClient userCenterFeignClient;

   // http://127.0.0.1:8010/shares/1 即可访问，然后跨服务去拿到昵称。


    public ShareDTO findById(Integer id){
        Share share = this.shareMapper.selectByPrimaryKey(id);
        Integer userId=share.getUserId();
       UserDTO userDTO = this.userCenterFeignClient.findById(userId);
//        String targerUrl = "Http://usercenterprovider/users/{id}";
//
//        UserDTO userDTO= this.restTemplate.getForObject(
//                targerUrl,
//                UserDTO.class,userId
//        );


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
