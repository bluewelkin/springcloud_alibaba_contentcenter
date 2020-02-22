package com.itmuch.contentcenter.feignclient;

import com.itmuch.contentcenter.domain.dto.user.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "usercenterprovider")
public interface UserCenterFeignClient {

//   构造 http://usercenterprovider//users/{id} 返回 UserDTO


    @GetMapping("/users/{id}")
    UserDTO findById(@PathVariable Integer id);


}
