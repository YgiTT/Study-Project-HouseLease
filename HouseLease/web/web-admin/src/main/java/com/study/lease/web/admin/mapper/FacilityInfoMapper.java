package com.study.lease.web.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.study.lease.model.entity.FacilityInfo;

import java.util.List;

public interface FacilityInfoMapper extends BaseMapper<FacilityInfo> {


    List<FacilityInfo> selectListByApartmentId(Long apartmentId);
}
