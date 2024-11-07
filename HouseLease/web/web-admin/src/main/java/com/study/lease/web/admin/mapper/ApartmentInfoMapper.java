package com.study.lease.web.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.study.lease.model.entity.ApartmentInfo;
import com.study.lease.web.admin.vo.apartment.ApartmentItemVo;
import com.study.lease.web.admin.vo.apartment.ApartmentQueryVo;

/**
 *
 */
public interface ApartmentInfoMapper extends BaseMapper<ApartmentInfo> {

    IPage<ApartmentItemVo> pageItem(Page<ApartmentItemVo> page, ApartmentQueryVo queryVo);
}
