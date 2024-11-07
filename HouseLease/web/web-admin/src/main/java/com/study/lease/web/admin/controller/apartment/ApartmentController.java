package com.study.lease.web.admin.controller.apartment;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.study.lease.common.result.Result;
import com.study.lease.web.admin.service.ApartmentInfoService;
import com.study.lease.web.admin.vo.apartment.ApartmentItemVo;
import com.study.lease.web.admin.vo.apartment.ApartmentQueryVo;
import com.study.lease.web.admin.vo.apartment.ApartmentSubmitVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author Ryan Yan
 * @Since 2024/10/16 16:25
 */
@Tag(name="公寓信息管理")
@RestController
@RequestMapping("/admin/apartment")
public class ApartmentController {

    @Autowired
    private ApartmentInfoService apartmentInfoService;

    @Operation(summary = "保存或更新公寓信息")
    @PostMapping("saveOrUpdate")
    public Result saveOrUpdate(@RequestBody ApartmentSubmitVo apartmentSubmitVo){
        apartmentInfoService.saveOrUpdateApartment(apartmentSubmitVo);

        return Result.ok();
    }


    @Operation(summary = "根据条件分页查询公寓列表")
    @GetMapping("pageItem")
    public Result<IPage<ApartmentItemVo>> pageItem(@RequestParam long current, @RequestParam long size, ApartmentQueryVo queryVo){
        Page<ApartmentItemVo> page = new Page<>(current, size);
        IPage<ApartmentItemVo> result = apartmentInfoService.pageItem(page, queryVo);
        return Result.ok(result);
    }


}
