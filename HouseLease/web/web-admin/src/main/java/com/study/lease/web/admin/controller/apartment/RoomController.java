package com.study.lease.web.admin.controller.apartment;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.study.lease.common.result.Result;
import com.study.lease.model.entity.RoomInfo;
import com.study.lease.model.enums.ReleaseStatus;
import com.study.lease.web.admin.service.RoomInfoService;
import com.study.lease.web.admin.vo.room.RoomDetailVo;
import com.study.lease.web.admin.vo.room.RoomItemVo;
import com.study.lease.web.admin.vo.room.RoomQueryVo;
import com.study.lease.web.admin.vo.room.RoomSubmitVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author Ryan Yan
 * @Since 2024/11/12 15:18
 */
@Tag(name = "房间管理")
@RestController
@RequestMapping("/admin/room")
public class RoomController {

    @Autowired
    private RoomInfoService roomInfoService;


    @Operation(summary = "保存或更新房间信息")
    @PostMapping("saveOrUpdate")
    public Result saveOrUpdate(@RequestBody RoomSubmitVo roomSubmitVo){
        roomInfoService.saveOrUpdateRoom(roomSubmitVo);
        return Result.ok();
    }


    @Operation(summary = "根据条件分页查询房间列表")
    @GetMapping("pageItem")
    public Result<IPage<RoomItemVo>> pageItem(@RequestParam long current, @RequestParam long size, RoomQueryVo queryVo){
        Page<RoomItemVo> page = new Page<>(current, size);
        IPage<RoomItemVo> roomItemVoIPage = roomInfoService.pageRoomItemByQuery(page,queryVo);
        return Result.ok(roomItemVoIPage);
    }

    @Operation(summary = "根据id获取房间详细信息")
    @GetMapping("getDetailById")
    public Result<RoomDetailVo> getDetailById(@RequestParam Long id){
        RoomDetailVo roomDetailVo = roomInfoService.getRoomDetailById(id);
        return Result.ok(roomDetailVo);

    }

    @Operation(summary = "根据Id删除房间信息")
    @DeleteMapping("removeId")
    public Result removeById(@RequestParam Long id){
        roomInfoService.removeRoomById(id);
        return Result.ok();
    }

    @Operation(summary = "根据id修改房间发布状态")
    @PostMapping("updateReleaseStatusById")
    public Result updateReleaseStatusById(Long id, ReleaseStatus status){
        LambdaUpdateWrapper<RoomInfo> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(RoomInfo::getId,id);
        updateWrapper.set(RoomInfo::getIsRelease,status);
        roomInfoService.update(updateWrapper);
        return Result.ok();
    }

    @Operation(summary = "根据公寓id查询房间列表")
    @GetMapping("listBasicByApartmentId")
    public Result<List<RoomInfo>> listBasicByApartmentId(Long apartmentId){
        LambdaQueryWrapper<RoomInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(RoomInfo::getApartmentId,apartmentId);
        queryWrapper.eq(RoomInfo::getIsRelease,ReleaseStatus.RELEASED);
        List<RoomInfo> roomInfoList = roomInfoService.list(queryWrapper);
        return Result.ok(roomInfoList);
    }



}
