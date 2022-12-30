package com.example.common.validator.constraint;

import com.example.common.validator.annotation.ListValue;
import org.springframework.util.CollectionUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.concurrent.CopyOnWriteArraySet;

public class ListValueConstraintValidator implements ConstraintValidator<ListValue, Integer> {

    private final CopyOnWriteArraySet<Integer> set = new CopyOnWriteArraySet<>();

    // 初始化方法
    @Override
    public void initialize(ListValue constraintAnnotation) {
        int[] vals = constraintAnnotation.vals();
        for (int val : vals) {
            set.add(val);
        }
    }

    /**
     * 判断是否校验成功
     * @param integer 需要校验的值
     * @param constraintValidatorContext 整个校验的上下文环境信息
     * @return true：校验通过  false：校验失败
     */
    @Override
    public boolean isValid(Integer integer, ConstraintValidatorContext constraintValidatorContext) {
        if (CollectionUtils.isEmpty(set)) {
            return true;
        }
        return set.contains(integer);
    }
}
