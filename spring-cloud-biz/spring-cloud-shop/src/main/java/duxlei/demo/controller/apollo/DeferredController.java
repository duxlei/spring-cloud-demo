/*
 * Copyright 2020 tuhu.cn All right reserved. This software is the
 * confidential and proprietary information of tuhu.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Tuhu.cn
 */
package duxlei.demo.controller.apollo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @author duhonggang
 * @date 2020/8/12 16:01
 */
@Api(tags = "Spring deferred 异步请求")
@RestController
@RequestMapping("/deferred")
public class DeferredController {

    private Executor executor = Executors.newSingleThreadExecutor();

    @GetMapping
    @ApiOperation("异步接口")
    public DeferredResult<String> deferred() {
        DeferredResult<String> result = new DeferredResult<>();
        executor.execute(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            result.setResult("这个消息延迟返回了");
        });
        return result;
    }

}
