package com.study.lease.web.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.study.lease.model.entity.AttrKey;
import com.study.lease.web.admin.vo.attr.AttrKeyVo;

import java.util.List;

public interface AttrKeyMapper extends BaseMapper<AttrKey> {


    List<AttrKeyVo> listAttrInfo();

}
