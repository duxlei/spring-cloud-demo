/*
 * Copyright 2020 tuhu.cn All right reserved. This software is the
 * confidential and proprietary information of tuhu.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Tuhu.cn
 */
package duxlei.demo.controller;

import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import duxlei.demo.facade.resp.ShopInfoResp;
import duxlei.demo.util.JsonUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author Administrator
 * @date 2020/7/5 11:34
 */
@Api(tags = "门店信息：Jackson测试")
@RestController
@RequestMapping("/shop")
public class ShopController {

    @ApiOperation("测试：Index")
    @GetMapping("/index")
    public String index() {
        return JsonUtil.toStr(genShopInfo());
    }

    @ApiOperation("测试：@JsonUnwrapped")
    @GetMapping("/flat")
    public String flat() {
        String str = "{\"fullName\":\"途虎工场店（人民广场）\",\"address\":\"上海市人民广场\",\"openTime\":\"2020-07-18 02:34:35\",\"level\":12,\"briefName\":\"人民广场店\"}";
        return JsonUtil.toObj(str, ShopInfoResp.class).toString();
    }

    @ApiOperation("测试：@Filter")
    @GetMapping("/json-filter")
    public String jsonFilter() {
        SimpleFilterProvider filterProvider = new SimpleFilterProvider().addFilter("shopJsonFilter", SimpleBeanPropertyFilter.filterOutAllExcept("address", "fullName", "briefName","aged"));
        return JsonUtil.toStr(genShopInfo(), filterProvider);
    }


    @GetMapping("/info")
    public ShopInfoResp info() {
        return genShopInfo();
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
