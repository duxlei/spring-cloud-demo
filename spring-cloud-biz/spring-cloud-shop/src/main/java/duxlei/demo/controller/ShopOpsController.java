/*
 * Copyright 2020 tuhu.cn All right reserved. This software is the
 * confidential and proprietary information of tuhu.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Tuhu.cn
 */
package duxlei.demo.controller;

import duxlei.demo.facade.resp.ShopInfoResp;
import duxlei.demo.service.ShopInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * @author Administrator
 * @date 2020/7/25 17:49
 */
@Api(tags = "门店信息操作API")
@RestController
@RequestMapping("/shop-ops")
public class ShopOpsController {

    @Autowired
    private ShopInfoService shopInfoService;


    @ApiOperation("从Redis中查询门店信息")
    @GetMapping("/shop-info-from-redis")
    public ShopInfoResp getShopInfoFromRedis() {
        return shopInfoService.getShopInfo();
    }


    @ApiOperation("保存门店信息Redis")
    @PostMapping("/shop-info-from-redis")
    public Boolean saveShopInfoFromRedis() {
        shopInfoService.setShopInfo(genShopInfo());
        return true;
    }

    @ApiOperation("删除门店信息Redis")
    @DeleteMapping("/shop-info-from-redis")
    public Boolean delShopInfoFromRedis() {
        shopInfoService.delShopInfo();
        return true;
    }



    private ShopInfoResp genShopInfo() {
        ShopInfoResp shopInfoResp = new ShopInfoResp();
        shopInfoResp.setFullName("途虎工场店（人民广场）");
        shopInfoResp.setShortName("人民广场店");
        shopInfoResp.setAddress("上海市人民广场");
        shopInfoResp.setTel("123-123456");
        shopInfoResp.setExtInfo(new ShopInfoResp.ExtInfo(new Date(), 12));
        return shopInfoResp;
    }

}
