package com.study.lease.web.admin.config;

/**
 * @Author Ryan Yan
 * @Since 2024/10/28 16:20
 */

import com.study.lease.web.admin.config.converter.StringToBaseEnumConverterFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

//    @Autowired
//    private StringToItemTypeConverter stringToItemTypeConverter;

    @Autowired
    private StringToBaseEnumConverterFactory stringToBaseEnumConverterFactory;


    @Override
    public void addFormatters(FormatterRegistry registry) {
//        registry.addConverter(this.stringToItemTypeConverter);
        registry.addConverterFactory(this.stringToBaseEnumConverterFactory);
        WebMvcConfigurer.super.addFormatters(registry);
    }


}
