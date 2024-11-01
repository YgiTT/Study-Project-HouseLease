package com.study.lease.web.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.study.lease.model.entity.AttrKey;
import com.study.lease.model.entity.AttrValue;
import com.study.lease.web.admin.mapper.AttrKeyMapper;
import com.study.lease.web.admin.mapper.AttrValueMapper;
import com.study.lease.web.admin.service.AttrKeyService;
import com.study.lease.web.admin.vo.attr.AttrKeyVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author Ryan Yan
 * @Since 2024/10/30 17:49
 */
@Service
public class AttrKeyServiceImpl extends ServiceImpl<AttrKeyMapper, AttrKey> implements AttrKeyService {

    @Autowired
    private AttrKeyMapper attrKeyMapper;

    @Autowired
    private AttrValueMapper attrValueMapper;

    @Override
    public List<AttrKeyVo> listAttrInfo() {
        return attrKeyMapper.listAttrInfo();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void removeAtrrKeyById(Long id) {
        //
        attrKeyMapper.deleteById(id);
        //
        LambdaQueryWrapper<AttrValue> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(AttrValue::getAttrKeyId,id);
        attrValueMapper.delete(wrapper);
    }
}
