package com.study.lease.web.admin.config.converter;

import com.study.lease.model.enums.BaseEnum;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;
import org.springframework.stereotype.Component;

/**
 * springmvc->enum 类型转换工厂
 *  API参数类型转换
 *  前端-》后端
 * @Author Ryan Yan
 * @Since 2024/10/28 17:50
 */
@Component
public class StringToBaseEnumConverterFactory implements ConverterFactory<String, BaseEnum> {

    @Override
    public <T extends BaseEnum> Converter<String, T> getConverter(Class<T> targetType) {
        return new Converter<String,T>(){
            @Override
            public T convert(String source) {
                T[] enumConstants = targetType.getEnumConstants();
                for (T enumConstant :enumConstants){
                    if(enumConstant.getCode().equals(Integer.valueOf(source))){
                        return enumConstant;
                    }
                }
                throw new IllegalArgumentException("code:"+source+"非法");
            }
        };

    }
}
