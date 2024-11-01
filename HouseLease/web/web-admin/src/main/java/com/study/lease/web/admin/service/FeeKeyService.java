package com.study.lease.web.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.study.lease.model.entity.FeeKey;
import com.study.lease.web.admin.vo.fee.FeeKeyVo;

import java.util.List;

public interface FeeKeyService extends IService<FeeKey> {

    List<FeeKeyVo> listFeeInfo();

    void removeFeeKeyById(Long id);
}
