package com.example.mall.product.service.impl;

import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.common.utils.PageUtils;
import com.example.common.utils.Query;

import com.example.mall.product.dao.CategoryDao;
import com.example.mall.product.entity.CategoryEntity;
import com.example.mall.product.service.CategoryService;


@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, CategoryEntity> implements CategoryService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CategoryEntity> page = this.page(
                new Query<CategoryEntity>().getPage(params),
                new QueryWrapper<CategoryEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<CategoryEntity> listWithTree() {
        // 1.查出所有分类
        List<CategoryEntity> entities = baseMapper.selectList(null);
        // 2.组装成父子的树形结构
        return entities.stream()
                .filter(categoryEntity -> categoryEntity.getParentCid() == 0)
                .peek(categoryEntity -> categoryEntity.setChildren(getChildren(categoryEntity, entities)))
                .sorted(Comparator.comparingInt(CategoryEntity::getSort))
                .collect(Collectors.toList());
    }

    @Override
    public void removeMenuByIds(List<Long> asList) {
        // TODO 1.检查当前删除的菜单，是否被别的地方引用
        // 逻辑删除
        baseMapper.deleteBatchIds(asList);
    }

    private List<CategoryEntity> getChildren(CategoryEntity root, List<CategoryEntity> all) {
        return all.stream()
                .filter(categoryEntity -> categoryEntity.getParentCid().equals(root.getCatId()))
                .peek(categoryEntity -> {
                    // 1.找到子菜单
                    categoryEntity.setChildren(getChildren(categoryEntity, all));
                })
                .sorted(Comparator.comparingInt(CategoryEntity::getSort))
                .collect(Collectors.toList());
    }
}