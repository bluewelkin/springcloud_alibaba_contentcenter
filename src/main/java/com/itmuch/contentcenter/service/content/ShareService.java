package com.itmuch.contentcenter.service.content;

import com.itmuch.contentcenter.dao.user.ShareMapper;
import com.itmuch.contentcenter.domain.dto.content.ShareDTO;
import com.itmuch.contentcenter.domain.dto.user.UserDTO;
import com.itmuch.contentcenter.domain.entity.content.Share;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ShareService {
    private final ShareMapper shareMapper;
    private final RestTemplate restTemplate;

    public ShareDTO findById(Integer id){
        Share share = this.shareMapper.selectByPrimaryKey(id);
        Integer userId=share.getUserId();

        UserDTO userDTO= this.restTemplate.getForObject(
                "Http://localhost:8080/users/{id}",
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
//                "http://localhost:8080/users/1",
//                String.class
//
//        );
//        System.out.println(forObject);
//    }


}
