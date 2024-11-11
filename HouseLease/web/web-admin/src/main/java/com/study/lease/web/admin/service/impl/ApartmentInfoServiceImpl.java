package com.study.lease.web.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.study.lease.common.exception.LeaseException;
import com.study.lease.common.result.ResultCodeEnum;
import com.study.lease.model.entity.*;
import com.study.lease.model.enums.ItemType;
import com.study.lease.web.admin.mapper.*;
import com.study.lease.web.admin.service.*;
import com.study.lease.web.admin.vo.apartment.ApartmentDetailVo;
import com.study.lease.web.admin.vo.apartment.ApartmentItemVo;
import com.study.lease.web.admin.vo.apartment.ApartmentQueryVo;
import com.study.lease.web.admin.vo.apartment.ApartmentSubmitVo;
import com.study.lease.web.admin.vo.fee.FeeValueVo;
import com.study.lease.web.admin.vo.graph.GraphVo;
import io.swagger.v3.oas.annotations.callbacks.Callback;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Ryan Yan
 * @Since 2024/10/15 17:36
 */
@Service
public class ApartmentInfoServiceImpl extends ServiceImpl<ApartmentInfoMapper, ApartmentInfo>
        implements ApartmentInfoService {

    @Autowired
    private GraphInfoService graphInfoService;

    @Autowired
    private GraphInfoMapper graphInfoMapper;

    @Autowired
    private LabelInfoMapper labelInfoMapper;

    @Autowired
    private FacilityInfoMapper facilityInfoMapper;

    @Autowired
    private FeeValueMapper feeValueMapper;

    @Autowired
    private RoomInfoMapper roomInfoMapper;

    @Autowired
    private ApartmentFacilityService apartmentFacilityService;

    @Autowired
    private ApartmentLabelService apartmentLabelService;

    @Autowired
    private ApartmentFeeValueService apartmentFeeValueService;

    @Autowired
    private ApartmentInfoMapper apartmentInfoMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveOrUpdateApartment(ApartmentSubmitVo apartmentSubmitVo) {
        boolean isUpdate = apartmentSubmitVo.getId() != null;
        //插入或更新apartmentInfo
        this.saveOrUpdate(apartmentSubmitVo);
        //获取插入或更新后的id
        Long id = apartmentSubmitVo.getId();
        //若是新增则：先根据id删除关联的信息并重新插入/更新
        if (isUpdate) {
            this.removeApartmentRelevance(id);
        }
        //插入
        //1.图片信息
        List<GraphVo> graphVoList = apartmentSubmitVo.getGraphVoList();
        if (!CollectionUtils.isEmpty(graphVoList)) {
            ArrayList<GraphInfo> graphInfoList = new ArrayList<>();
            for (GraphVo graphVo : graphVoList) {
                GraphInfo graphInfo = new GraphInfo();
                graphInfo.setItemType(ItemType.APARTMENT);
                graphInfo.setItemId(id);
                graphInfo.setName(graphVo.getName());
                graphInfo.setUrl(graphVo.getUrl());
                graphInfoList.add(graphInfo);
            }
            graphInfoService.saveBatch(graphInfoList);
        }
        //2.配套列表
        List<Long> facilityInfoIds = apartmentSubmitVo.getFacilityInfoIds();
        if (!CollectionUtils.isEmpty(facilityInfoIds)) {
            ArrayList<ApartmentFacility> apartmentFacilityList = new ArrayList<>();
            for (Long facilityId : facilityInfoIds) {
                ApartmentFacility apartmentFacility = ApartmentFacility.builder()
                        .apartmentId(id)
                        .facilityId(facilityId)
                        .build();
                apartmentFacilityList.add(apartmentFacility);
            }
            apartmentFacilityService.saveBatch(apartmentFacilityList);
        }
        //3.标签列表
        List<Long> labelIds = apartmentSubmitVo.getLabelIds();
        if (!CollectionUtils.isEmpty(labelIds)) {
            ArrayList<ApartmentLabel> apartmentLabelList = new ArrayList<>();
            for (Long labelId : labelIds) {
                ApartmentLabel apartmentLabel = ApartmentLabel.builder()
                        .apartmentId(id)
                        .labelId(labelId)
                        .build();
                apartmentLabelList.add(apartmentLabel);
            }
            apartmentLabelService.saveBatch(apartmentLabelList);
        }
        //4、杂费值列表
        List<Long> feeValueIds = apartmentSubmitVo.getFeeValueIds();
        if (!CollectionUtils.isEmpty(feeValueIds)) {
            ArrayList<ApartmentFeeValue> apartmentFeeValueList = new ArrayList<>();
            for (Long feeValueId : feeValueIds) {
                ApartmentFeeValue apartmentFeeValue = ApartmentFeeValue.builder()
                        .apartmentId(id)
                        .feeValueId(feeValueId)
                        .build();
                apartmentFeeValueList.add(apartmentFeeValue);
            }
            apartmentFeeValueService.saveBatch(apartmentFeeValueList);
        }
    }

    @Override
    public IPage<ApartmentItemVo> pageItem(Page<ApartmentItemVo> page, ApartmentQueryVo queryVo) {
        IPage<ApartmentItemVo> result =  apartmentInfoMapper.pageItem(page,queryVo);
        return result;
    }

    @Override
    public ApartmentDetailVo getDetailById(Long id) {
        //1.查询公寓信息
        ApartmentInfo apartmentInfo = this.getById(id);
        //2、查询图片列表
        List<GraphVo> graphVoList = graphInfoMapper.selectListByItemTypeAndId(ItemType.APARTMENT,id);
        //3、查询标签列表
        List<LabelInfo> labelInfoList = labelInfoMapper.selectListByApartmentId(id);
        //4、查询配套列表
        List<FacilityInfo> facilityInfoList = facilityInfoMapper.selectListByApartmentId(id);
        //5、查询杂费列表
        List<FeeValueVo> feeValueVoList = feeValueMapper.selectListByApartmentId(id);
        //6、
        ApartmentDetailVo apartmentDetailVo = new ApartmentDetailVo();
        BeanUtils.copyProperties(apartmentInfo,apartmentDetailVo);
        apartmentDetailVo.setGraphVoList(graphVoList);
        apartmentDetailVo.setLabelInfoList(labelInfoList);
        apartmentDetailVo.setFacilityInfoList(facilityInfoList);
        apartmentDetailVo.setFeeValueVoList(feeValueVoList);

        return apartmentDetailVo;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void removeApartmentById(Long id) {
        //获取公寓的房间数
        LambdaQueryWrapper<RoomInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(RoomInfo::getApartmentId,id);
        Long roomCount = roomInfoMapper.selectCount(queryWrapper);
        //若存在房间则不可删除
        if(roomCount > 0){
            throw new LeaseException(ResultCodeEnum.ADMIN_ACCESS_DELETE_ERROR);
        }
        //删除公寓信息
        super.removeById(id);
        //删除公寓相关信息
        this.removeApartmentRelevance(id);

    }



    /**
     * 删除公寓的关联信息
     * @param id
     */
    private void removeApartmentRelevance(Long id) {
        //1.删除图片信息
        LambdaQueryWrapper<GraphInfo> graphQueryWrapper = new LambdaQueryWrapper<>();
        graphQueryWrapper.eq(GraphInfo::getItemType, ItemType.APARTMENT);
        graphQueryWrapper.eq(GraphInfo::getItemType, id);
        graphInfoService.remove(graphQueryWrapper);
        //2.删除配套列表
        LambdaQueryWrapper<ApartmentFacility> facilityLambdaQueryWrapper = new LambdaQueryWrapper<>();
        facilityLambdaQueryWrapper.eq(ApartmentFacility::getApartmentId, id);
        apartmentFacilityService.remove(facilityLambdaQueryWrapper);
        //3.删除标签列表
        LambdaQueryWrapper<ApartmentLabel> labelQueryWrapper = new LambdaQueryWrapper<>();
        labelQueryWrapper.eq(ApartmentLabel::getApartmentId, id);
        apartmentLabelService.remove(labelQueryWrapper);
        //4、删除杂费值列表
        LambdaQueryWrapper<ApartmentFeeValue> feeValueLambdaQueryWrapper = new LambdaQueryWrapper<>();
        feeValueLambdaQueryWrapper.eq(ApartmentFeeValue::getApartmentId, id);
        apartmentFeeValueService.remove(feeValueLambdaQueryWrapper);
    }


}
