package com.study.lease.web.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.study.lease.model.entity.FeeValue;
import com.study.lease.web.admin.vo.fee.FeeValueVo;

import java.util.List;

public interface FeeValueMapper extends BaseMapper<FeeValue> {

    List<FeeValueVo> selectListByApartmentId(Long id);
}
