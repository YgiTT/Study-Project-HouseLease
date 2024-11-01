package com.study.lease.web.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.study.lease.model.entity.FeeKey;
import com.study.lease.web.admin.vo.fee.FeeKeyVo;

import java.util.List;

public interface FeeKeyMapper extends BaseMapper<FeeKey> {

    List<FeeKeyVo> listFeeInfo();
}
