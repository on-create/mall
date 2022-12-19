package com.example.mall.order.dao;

import com.example.mall.order.entity.OrderEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单
 * 
 * @author on-create
 * @email on-create@gmail.com
 * @date 2022-12-19 19:21:48
 */
@Mapper
public interface OrderDao extends BaseMapper<OrderEntity> {
	
}
