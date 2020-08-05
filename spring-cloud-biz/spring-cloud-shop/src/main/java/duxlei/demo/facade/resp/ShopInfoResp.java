/*
 * Copyright 2020 tuhu.cn All right reserved. This software is the
 * confidential and proprietary information of tuhu.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Tuhu.cn
 */
package duxlei.demo.facade.resp;

import com.fasterxml.jackson.annotation.*;
import io.swagger.annotations.ApiModel;
import lombok.*;

import java.util.Date;

/**
 * @author Administrator
 * @date 2020/7/18 20:23
 */
@Getter
@Setter
@ToString
@JsonFilter("shopJsonFilter")
@ApiModel
public class ShopInfoResp {

    @JsonProperty("briefName")
    private String shortName;

    private String fullName;

    private String address;

    @JsonIgnore
    private String tel;

    @JsonUnwrapped
    private ExtInfo extInfo;

    @Getter
    @Setter
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    @ApiModel
    public static class ExtInfo {

        @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
        private Date openTime;
        private int level;

    }
}
