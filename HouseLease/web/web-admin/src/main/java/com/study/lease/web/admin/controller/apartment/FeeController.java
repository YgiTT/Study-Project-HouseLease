package com.study.lease.web.admin.controller.apartment;

import com.study.lease.common.result.Result;
import com.study.lease.model.entity.AttrKey;
import com.study.lease.model.entity.AttrValue;
import com.study.lease.model.entity.FeeKey;
import com.study.lease.model.entity.FeeValue;
import com.study.lease.web.admin.service.FeeKeyService;
import com.study.lease.web.admin.service.FeeValueService;
import com.study.lease.web.admin.vo.attr.AttrKeyVo;
import com.study.lease.web.admin.vo.fee.FeeKeyVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author Ryan Yan
 * @Since 2024/10/31 18:05
 */
@Tag(name="房间杂费管理")
@RestController
@RequestMapping("/admin/fee")
public class FeeController {

    @Autowired
    private FeeKeyService feeKeyService;

    @Autowired
    private FeeValueService feeValueService;

    @Operation(summary = "查询全部杂费名称和杂费值列表")
    @GetMapping("list")
    public Result<List<FeeKeyVo>> feeInfoList(){
        List<FeeKeyVo> feeKeyVos = feeKeyService.listFeeInfo();
        return Result.ok(feeKeyVos);
    }


    @Operation(summary = "新增或更新杂费名称")
    @PostMapping("key/saveOrUpdate")
    public Result saveOrUpdateFeeKey(@RequestBody FeeKey feeKey){
        feeKeyService.saveOrUpdate(feeKey);
        return Result.ok();
    }

    @Operation(summary = "新增或更新杂费值")
    @PostMapping("value/saveOrUpdate")
    public Result saveOrUpdateFeeValue(@RequestBody FeeValue feeValue){
        feeValueService.saveOrUpdate(feeValue);
        return Result.ok();
    }

    @Operation(summary = "根据id删除杂费名称和所属杂费值")
    @PostMapping("key/deleteById")
    public Result removeFeeKey(@RequestParam Long id){
        feeKeyService.removeFeeKeyById(id);
        return Result.ok();
    }

    @Operation(summary = "根据id删除杂费值")
    @PostMapping("value/deleteById")
    public Result removeFeeValue(@RequestParam Long id){
        feeValueService.removeById(id);
        return Result.ok();
    }


}
