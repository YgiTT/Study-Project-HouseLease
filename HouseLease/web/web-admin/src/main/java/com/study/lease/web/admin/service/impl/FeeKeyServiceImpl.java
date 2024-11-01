package com.study.lease.web.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.study.lease.model.entity.FeeKey;
import com.study.lease.model.entity.FeeValue;
import com.study.lease.web.admin.mapper.FeeKeyMapper;
import com.study.lease.web.admin.mapper.FeeValueMapper;
import com.study.lease.web.admin.service.FeeKeyService;
import com.study.lease.web.admin.vo.fee.FeeKeyVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author Ryan Yan
 * @Since 2024/11/1 10:56
 */
@Service
public class FeeKeyServiceImpl extends ServiceImpl<FeeKeyMapper, FeeKey> implements FeeKeyService {

    @Autowired
    private FeeKeyMapper feeKeyMapper;


    @Autowired
    private FeeValueMapper feeValueMapper;

    @Override
    public List<FeeKeyVo> listFeeInfo() {
        return feeKeyMapper.listFeeInfo();
    }

    @Override
    public void removeFeeKeyById(Long id) {
        //
        feeKeyMapper.deleteById(id);
        //
        LambdaQueryWrapper<FeeValue> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(FeeValue::getFeeKeyId,id);
        feeValueMapper.delete(queryWrapper);
    }
}
