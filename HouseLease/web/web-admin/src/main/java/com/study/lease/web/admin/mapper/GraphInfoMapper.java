package com.study.lease.web.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.study.lease.model.entity.GraphInfo;
import com.study.lease.model.enums.ItemType;
import com.study.lease.web.admin.vo.graph.GraphVo;

import java.util.List;

public interface GraphInfoMapper extends BaseMapper<GraphInfo> {

    List<GraphVo> selectListByItemTypeAndItemId(ItemType itemType, Long itemId);
}
