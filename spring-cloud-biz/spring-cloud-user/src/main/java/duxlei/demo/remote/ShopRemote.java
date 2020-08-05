/*
 * Copyright 2020 tuhu.cn All right reserved. This software is the
 * confidential and proprietary information of tuhu.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Tuhu.cn
 */
package duxlei.demo.remote;

import duxlei.demo.facade.resp.ShopInfoResp;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Administrator
 * @date 2020/7/5 11:37
 */
@FeignClient(name = "spring-cloud-shop-biz", fallbackFactory = ShopRemoteFallBackFactory.class)
public interface ShopRemote {

    @GetMapping("/shop/info")
    ShopInfoResp shopInfo();
}
