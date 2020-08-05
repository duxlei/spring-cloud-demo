/*
 * Copyright 2020 tuhu.cn All right reserved. This software is the
 * confidential and proprietary information of tuhu.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Tuhu.cn
 */
package duxlei.demo.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import duxlei.demo.facade.resp.ShopInfoResp;
import duxlei.demo.remote.ShopRemote;
import duxlei.demo.util.JsonUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Administrator
 * @date 2020/7/5 11:25
 */
@Api(tags = "用户信息接口")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private ShopRemote shopRemote;

    @ApiOperation("查询用户")
    @GetMapping("/info")
    public Object info() throws JsonProcessingException {
        ShopInfoResp shopInfo = shopRemote.shopInfo();
        return "user: duxlei" + ", shop: " + JsonUtil.toStr(shopInfo);
    }

}
