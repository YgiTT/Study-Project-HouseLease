package com.study.lease.web.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.study.lease.model.entity.ApartmentFeeValue;
import com.study.lease.web.admin.mapper.ApartmentFeeValueMapper;
import com.study.lease.web.admin.service.ApartmentFeeValueService;
import org.springframework.stereotype.Service;

/**
 * @Author Ryan Yan
 * @description 针对表【apartment_facility(公寓&配套关联表)】的数据库操作Service实现
 * @Since 2024/10/15 16:16
 */
@Service
public class ApartmentFeeValueServiceImpl extends ServiceImpl<ApartmentFeeValueMapper, ApartmentFeeValue>
        implements ApartmentFeeValueService {

}
