package com.study.lease.web.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.study.lease.model.entity.LeaseTerm;
import com.study.lease.web.admin.mapper.LeaseTermMapper;
import com.study.lease.web.admin.service.LeaseTermService;
import org.springframework.stereotype.Service;

/**
 * @Author Ryan Yan
 * @Since 2024/10/22 15:20
 */
@Service
public class LeaseTermServiceImpl extends ServiceImpl<LeaseTermMapper, LeaseTerm>
implements LeaseTermService {
}
