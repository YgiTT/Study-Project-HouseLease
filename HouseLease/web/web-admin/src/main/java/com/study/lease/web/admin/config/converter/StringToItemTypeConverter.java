package com.study.lease.web.admin.config.converter;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.study.lease.model.enums.ItemType;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * API参数类型转换
 * 前端-》后端
 * @Author Ryan Yan
 * @Since 2024/10/23 18:07
 */
//@Component
public class StringToItemTypeConverter implements Converter<String, ItemType> {

    @Override
    public ItemType convert(String code) {
        ItemType[] itemTypes = ItemType.values();
        for (ItemType itemType:itemTypes){
            if(itemType.getCode().equals(Integer.valueOf(code))){
                return itemType;
            }
        }
        throw new IllegalArgumentException("code:"+code+"非法");
    }

}
