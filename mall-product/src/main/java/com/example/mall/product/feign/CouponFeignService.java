package com.example.mall.product.feign;

import com.example.common.to.SkuReductionTo;
import com.example.common.to.SpuBoundTo;
import com.example.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("mall-coupon")
public interface CouponFeignService {

    /**
     * 1、CouponFeignService.saveSpuBounds(spuBoundTo)
     *      1）@RequestBody 将这个对象转为 json
     *      2）找到 mall-coupon 服务，给 /coupon/spubounds/save 发送请求。将上一步转的 json 放在请求体位置，发送请求
     *      3）对方服务收到请求。请求体里有 json 数据。
     *          @RequestBody SpuBoundsEntity spuBoundsEntity：将请求体 json 转为 SpuBoundsEntity
     * 只要 json 数据模型是兼容的。双方服务无需使用同一个 to
     * @param spuBoundTo
     * @return
     */
    @PostMapping("/coupon/spubounds/save")
    R saveSpuBounds(@RequestBody SpuBoundTo spuBoundTo);

    @PostMapping("/coupon/skufullreduction/saveinfo")
    R saveSkuReduction(@RequestBody SkuReductionTo skuReductionTo);
}
