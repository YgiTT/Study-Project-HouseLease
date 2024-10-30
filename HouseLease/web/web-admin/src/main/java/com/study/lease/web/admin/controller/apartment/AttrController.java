package com.study.lease.web.admin.controller.apartment;

import com.study.lease.common.result.Result;
import com.study.lease.model.entity.AttrKey;
import com.study.lease.model.entity.AttrValue;
import com.study.lease.web.admin.service.AttrKeyService;
import com.study.lease.web.admin.service.AttrValueService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author Ryan Yan
 * @Since 2024/10/30 17:45
 */
@Tag(name="房间属性管理")
@RestController
@RequestMapping("/admin/attr")
public class AttrController {


    @Autowired
    private AttrKeyService attrKeyService;


    @Autowired
    private AttrValueService attrValueService;


    @Operation(summary = "新增或更新属性名称")
    @PostMapping("key/saveOrUpdate")
    public Result saveOrUpdateAttrKey(@RequestBody AttrKey attrKey){
        attrKeyService.saveOrUpdate(attrKey);
        return Result.ok();
    }

    @Operation(summary = "新增或更新属性值")
    @PostMapping("value/saveOrUpdate")
    public Result saveOrUpdateAttrValue(@RequestBody AttrValue attrValue){
        attrValueService.saveOrUpdate(attrValue);
        return Result.ok();
    }

    @Operation(summary = "根据id删除属性名称")
    @PostMapping("key/deleteById")
    public Result removeAttrKey(@RequestParam Long id){
        attrKeyService.removeById(id);
        return Result.ok();
    }

    @Operation(summary = "根据id删除属性值")
    @PostMapping("value/deleteById")
    public Result removeAttrValue(@RequestParam Long id){
        attrValueService.removeById(id);
        return Result.ok();
    }




}