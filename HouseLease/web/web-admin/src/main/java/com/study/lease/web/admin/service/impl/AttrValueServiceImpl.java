package com.study.lease.web.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.study.lease.model.entity.AttrValue;
import com.study.lease.web.admin.mapper.AttrValueMapper;
import com.study.lease.web.admin.service.AttrValueService;
import org.springframework.stereotype.Service;

/**
 * @Author Ryan Yan
 * @Since 2024/10/30 17:50
 */
@Service
public class AttrValueServiceImpl extends ServiceImpl<AttrValueMapper,AttrValue> implements AttrValueService {
}
