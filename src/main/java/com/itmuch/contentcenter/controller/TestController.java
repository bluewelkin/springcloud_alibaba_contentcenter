package com.itmuch.contentcenter.controller;

import com.itmuch.contentcenter.dao.user.ShareMapper;
import com.itmuch.contentcenter.domain.entity.content.Share;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
public class TestController {


//    http://127.0.0.1:8010/test 先插入了一条数据，然后查出了所有的。
    @Autowired
    private ShareMapper shareMapper;

    @GetMapping("/test")
    public List<Share> tesetInsert() {
        Share share = new Share();
        share.setCreateTime(new Date());
        share.setUpdateTime(new Date());
        share.setTitle("biaoti");
        share.setCover("cove");
        share.setAuthor("damu");
        this.shareMapper.insertSelective(share);
        List<Share> shares = this.shareMapper.selectAll();



        return shares;

    }

}
