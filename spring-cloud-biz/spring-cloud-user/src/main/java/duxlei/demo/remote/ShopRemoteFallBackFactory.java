/*
 * Copyright 2020 tuhu.cn All right reserved. This software is the
 * confidential and proprietary information of tuhu.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Tuhu.cn
 */
package duxlei.demo.remote;

import feign.hystrix.FallbackFactory;

/**
 * @author Administrator
 * @date 2020/7/18 20:12
 */
public class ShopRemoteFallBackFactory implements FallbackFactory<ShopRemote> {
    @Override
    public ShopRemote create(Throwable throwable) {
        throwable.printStackTrace();
        return null;
    }
}
