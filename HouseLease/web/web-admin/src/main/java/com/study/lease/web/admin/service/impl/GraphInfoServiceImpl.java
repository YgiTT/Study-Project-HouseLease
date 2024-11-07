package com.study.lease.web.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.study.lease.model.entity.GraphInfo;
import com.study.lease.web.admin.mapper.GraphInfoMapper;
import com.study.lease.web.admin.service.GraphInfoService;
import org.springframework.stereotype.Service;

/**
 * @Author Ryan Yan
 * @Since 2024/11/5 18:02
 */
@Service
public class GraphInfoServiceImpl extends ServiceImpl<GraphInfoMapper, GraphInfo> implements GraphInfoService {
}
