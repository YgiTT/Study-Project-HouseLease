package com.study.lease.web.admin.controller.apartment;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.study.lease.common.result.Result;
import com.study.lease.model.entity.PaymentType;
import com.study.lease.web.admin.service.PaymentTypeSerivce;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author Ryan Yan
 * @Since 2024/10/18 14:31
 */
@Tag(name="支付方式管理")
@RequestMapping("/admin/payment")
@RestController
public class PaymentTypeController {


    @Autowired
    private PaymentTypeSerivce paymentTypeService;

    @Operation(summary = "查询全部支付方式列表")
    @GetMapping("list")
    public Result<List<PaymentType>> listPaymentType(){
//        LambdaQueryWrapper<PaymentType> queryWrapper = new LambdaQueryWrapper<>();
//        queryWrapper.eq(PaymentType::getIsDeleted,0);
        List<PaymentType> list = paymentTypeService.list();
        return Result.ok(list);
    }

    @Operation(summary = "保存或更新支付方式")
    @PostMapping("saveOrUpdate")
    public Result saveOrUpdatePaymentType(@RequestBody PaymentType paymentType){
        paymentTypeService.saveOrUpdate(paymentType);
        return Result.ok();
    }


    @Operation(summary = "根据id删除支付方式")
    @DeleteMapping("deleteById")
    public Result deletePaymentById(@RequestParam Long id){
        paymentTypeService.removeById(id);
        return Result.ok();
    }





}
