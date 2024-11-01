package com.study.lease.web.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.study.lease.model.entity.AttrKey;
import com.study.lease.web.admin.vo.attr.AttrKeyVo;
import org.springframework.stereotype.Service;

import java.util.List;


public interface AttrKeyService extends IService<AttrKey> {

    List<AttrKeyVo> listAttrInfo();

    void removeAtrrKeyById(Long id);
}
