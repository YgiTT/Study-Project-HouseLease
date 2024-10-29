package com.study.lease.web.admin.controller.apartment;

import com.study.lease.common.result.Result;
import com.study.lease.model.entity.LeaseTerm;
import com.study.lease.web.admin.service.LeaseTermService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author Ryan Yan
 * @Since 2024/10/22 15:17
 */
@Tag(name = "租期管理")
@RequestMapping("/admin/term")
@RestController
public class LeaseTermController {

    @Autowired
    private LeaseTermService leaseTermService;


    @Operation(summary = "查询全部租期列表")
    @GetMapping("list")
    public Result<List<LeaseTerm>> listLeaseTerm(){
        List<LeaseTerm> list = leaseTermService.list();
        return Result.ok(list);
    }


    @Operation(summary = "保存或更新租期信息")
    @PostMapping("saveOrUpdate")
    public Result saveOrUpdate(@RequestBody LeaseTerm leaseTerm){
        leaseTermService.saveOrUpdate(leaseTerm);
        return Result.ok();
    }


    @Operation(summary = "根据id删除租期")
    @DeleteMapping("deleteById")
    public Result deleteLeaseTermById(@RequestParam Long id){
        leaseTermService.removeById(id);
        return Result.ok();
    }

}
