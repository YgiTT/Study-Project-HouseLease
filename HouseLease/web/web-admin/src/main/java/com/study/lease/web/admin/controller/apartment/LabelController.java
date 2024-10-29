package com.study.lease.web.admin.controller.apartment;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.study.lease.common.result.Result;
import com.study.lease.model.entity.LabelInfo;
import com.study.lease.model.enums.ItemType;
import com.study.lease.web.admin.service.LabelInfoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author Ryan Yan
 * @Since 2024/10/22 17:35
 */
@Tag(name = "标签")
@RestController
@RequestMapping("/admin/label")
public class LabelController {

    @Autowired
    private LabelInfoService labelInfoServer;


    @Operation(summary = "(根据类型)查询标签")
    @GetMapping("list")
    public Result<List<LabelInfo>> labelList(@RequestParam(required = false) ItemType type) {
        LambdaQueryWrapper<LabelInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(type != null, LabelInfo::getType, type);
        List<LabelInfo> list = labelInfoServer.list(queryWrapper);
        return Result.ok(list);
    }


    @Operation(summary = "新增或修改标签信息")
    @PostMapping("saveOrUpdate")
    public Result saveOrUpdateLabel(@RequestBody LabelInfo labelInfo) {
        labelInfoServer.saveOrUpdate(labelInfo);
        return Result.ok();
    }


    @Operation(summary = "根据id删除标签信息")
    @DeleteMapping("deleteById")
    public Result deleteLabelById(@RequestParam Long id) {
        labelInfoServer.removeById(id);
        return Result.ok();
    }





}
