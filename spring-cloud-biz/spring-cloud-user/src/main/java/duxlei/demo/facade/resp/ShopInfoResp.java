/*
 * Copyright 2020 tuhu.cn All right reserved. This software is the
 * confidential and proprietary information of tuhu.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Tuhu.cn
 */
package duxlei.demo.facade.resp;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Administrator
 * @date 2020/7/18 20:23
 */
@Getter
@Setter
@ToString
public class ShopInfoResp {

    private String shortName;
    private String fullName;
    private String address;
    private String tel;

}
