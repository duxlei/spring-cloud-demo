/*
 * Copyright 2020 tuhu.cn All right reserved. This software is the
 * confidential and proprietary information of tuhu.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Tuhu.cn
 */
package duxlei.demo.service;

import duxlei.demo.facade.resp.ShopInfoResp;
import duxlei.demo.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @author Administrator
 * @date 2020/7/25 17:43
 */
@Service
public class ShopInfoService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    private final String SHOP_INFO_KEY = "key:shop-info:";

    /**
     * 保存门店信息
     * @param shopInfo
     */
    public void setShopInfo(ShopInfoResp shopInfo) {
        redisTemplate.opsForValue().set(SHOP_INFO_KEY, JsonUtil.toStr(shopInfo));
    }

    /**
     * 获取门店信息
     */
    public ShopInfoResp getShopInfo() {
        return JsonUtil.toObj(redisTemplate.opsForValue().get(SHOP_INFO_KEY), ShopInfoResp.class);
    }

    /**
     * 删除门店信息
     */
    public void delShopInfo() {
        redisTemplate.delete(SHOP_INFO_KEY);
    }
}
