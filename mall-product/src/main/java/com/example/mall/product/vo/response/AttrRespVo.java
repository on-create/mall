package com.example.mall.product.vo.response;

import com.example.mall.product.vo.AttrVo;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class AttrRespVo extends AttrVo   {

    /**
     * 所属分类的名字
     */
    private String catelogName;

    /**
     * 所属分组的名字
     */
    private String groupName;

    private Long[] catelogPath;
}
