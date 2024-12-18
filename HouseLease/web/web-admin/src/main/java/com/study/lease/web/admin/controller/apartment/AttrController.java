package com.study.lease.web.admin.controller.apartment;

import com.study.lease.common.result.Result;
import com.study.lease.model.entity.AttrKey;
import com.study.lease.model.entity.AttrValue;
import com.study.lease.web.admin.service.AttrKeyService;
import com.study.lease.web.admin.service.AttrValueService;
import com.study.lease.web.admin.vo.attr.AttrKeyVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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


    @Operation(summary = "查詢全部属性名称和属性值列表")
    @GetMapping("list")
    public Result<List<AttrKeyVo>> listAttrInfo(){
        List<AttrKeyVo> attrKeyVos = attrKeyService.listAttrInfo();
        return  Result.ok(attrKeyVos);

    }

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

    @Operation(summary = "根据id删除属性名称和对应的属性值")
    @PostMapping("key/deleteById")
    public Result removeAttrKey(@RequestParam Long id){
        attrKeyService.removeAtrrKeyById(id);
        return Result.ok();
    }

    @Operation(summary = "根据id删除属性值")
    @PostMapping("value/deleteById")
    public Result removeAttrValue(@RequestParam Long id){
        attrValueService.removeById(id);
        return Result.ok();
    }





}
