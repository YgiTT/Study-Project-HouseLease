package com.study.lease.web.admin.controller.apartment;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.study.lease.common.result.Result;
import com.study.lease.web.admin.vo.apartment.ApartmentItemVo;
import com.study.lease.web.admin.vo.apartment.ApartmentQueryVo;
import com.study.lease.web.admin.vo.apartment.ApartmentSubmitVo;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

/**
 * @Author Ryan Yan
 * @Since 2024/10/16 16:25
 */
@Tag(name="公寓信息管理")
@RestController
@RequestMapping("/admin/apartment")
public class ApartmentController {

    @PostMapping("saveOrUpdate")
    public Result saveOrUpdate(@RequestBody ApartmentSubmitVo apartmentSubmitVo){
        return Result.ok();
    }


    @GetMapping("pageItem")
    public Result<IPage<ApartmentItemVo>> pageItem(@RequestParam long current, @RequestParam long size, ApartmentQueryVo queryVo){
        return Result.ok();
    }


}
