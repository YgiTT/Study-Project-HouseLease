package com.study.lease.web.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.study.lease.model.entity.FeeValue;
import com.study.lease.web.admin.mapper.FeeKeyMapper;
import com.study.lease.web.admin.mapper.FeeValueMapper;
import com.study.lease.web.admin.service.FeeValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author Ryan Yan
 * @Since 2024/11/1 10:54
 */
@Service
public class FeeValueServiceImpl extends ServiceImpl<FeeValueMapper,FeeValue> implements FeeValueService {


    @Autowired
    private FeeKeyMapper feeKeyMapper;


}
