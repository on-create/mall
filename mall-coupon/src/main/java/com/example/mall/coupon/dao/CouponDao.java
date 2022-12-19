package com.example.mall.coupon.dao;

import com.example.mall.coupon.entity.CouponEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 优惠券信息
 * 
 * @author on-create
 * @email on-create@gmail.com
 * @date 2022-12-19 19:08:47
 */
@Mapper
public interface CouponDao extends BaseMapper<CouponEntity> {
	
}
