package com.example.mall.product.service.impl;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.function.Consumer;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.common.utils.PageUtils;
import com.example.common.utils.Query;

import com.example.mall.product.dao.AttrGroupDao;
import com.example.mall.product.entity.AttrGroupEntity;
import com.example.mall.product.service.AttrGroupService;


@Service("attrGroupService")
public class AttrGroupServiceImpl extends ServiceImpl<AttrGroupDao, AttrGroupEntity> implements AttrGroupService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AttrGroupEntity> page = this.page(
                new Query<AttrGroupEntity>().getPage(params),
                new QueryWrapper<AttrGroupEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params, Long catelogId) {
        QueryWrapper<AttrGroupEntity> wrapper = new QueryWrapper<>();
        // 关键词检索
        String key = (String) params.get("key");
        if (StringUtils.isNotBlank(key)) {
            wrapper.nested(queryWrapper -> {
                queryWrapper.eq("attr_group_id", key).or().like("attr_group_name", key);
            });
        }

        IPage<AttrGroupEntity> page;
        if (catelogId == 0) {
            page = this.page(
                    new Query<AttrGroupEntity>().getPage(params),
                    wrapper
            );
        } else {
            page = this.page(
                    new Query<AttrGroupEntity>().getPage(params),
                    wrapper.eq("catelog_id", catelogId)
            );
        }
        return new PageUtils(page);
    }

}