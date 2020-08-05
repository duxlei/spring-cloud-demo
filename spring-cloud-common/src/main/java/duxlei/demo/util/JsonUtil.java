/*
 * Copyright 2020 tuhu.cn All right reserved. This software is the
 * confidential and proprietary information of tuhu.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Tuhu.cn
 */
package duxlei.demo.util;

import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

import java.nio.file.Paths;

/**
 * @author Administrator
 * @date 2020/7/18 21:07
 */
public class JsonUtil {

    /**
     * 对象Json转字符串
     * @param o
     * @return
     */
    public static String toStr(Object o) {
        return toStr(o, null);
    }

    /**
     * 对象Json转字符串
     * @param o
     * @param filter 过滤器
     * @return
     */
    public static String toStr(Object o, FilterProvider filter) {
        if (o == null) {
            return null;
        }
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonFilter annotation = o.getClass().getAnnotation(JsonFilter.class);
            if (annotation == null) {
                // 如果被序列化的对象没有@JsonFilter，直接序列化
                return mapper.writeValueAsString(o);
            } else {
                if (filter == null) {
                    // 如果filter为空怎序列化所有字段
                    filter = new SimpleFilterProvider().addFilter(annotation.value(), SimpleBeanPropertyFilter.serializeAll());
                }
                return mapper.writer(filter).writeValueAsString(o);
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static <T> T toObj(String jsonStr, Class<T> clazz) {
        if (StrUtil.isBlank(jsonStr)) {
            return null;
        }
        try {
//            ObjectMapper mapper = new ObjectMapper();
//            JsonFilter annotation = clazz.getAnnotation(JsonFilter.class);
//            if (annotation == null) {
//                return new ObjectMapper().readValue(jsonStr, clazz);
//            } else {
//                FilterProvider filter = new SimpleFilterProvider().addFilter(annotation.value(), SimpleBeanPropertyFilter.serializeAll());
//                return new ObjectMapper().reader(filter).readValue(jsonStr, clazz);
//            }
            return new ObjectMapper().readValue(jsonStr, clazz);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

}
