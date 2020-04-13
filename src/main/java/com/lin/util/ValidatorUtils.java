package com.lin.util;

import com.lin.base.AjaxResult;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.HibernateValidator;
import org.springframework.util.CollectionUtils;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author lin
 * @version 1.0
 * @date 2020/4/1 22:27
 */
@Slf4j
public class ValidatorUtils {

    private ValidatorUtils(){}

    private static Validator validate = (Validation.byProvider(HibernateValidator.class).configure())
            .failFast(false).buildValidatorFactory().getValidator();

    public static AjaxResult validate(Object request){
        log.info("validatorUtils validate");
        Set<ConstraintViolation<Object>> result = validate.validate(request, new Class[0]);
        if(!CollectionUtils.isEmpty(result)){
            List<String> msglist = result.stream().map((e) -> "validate mssage:"
                    + e.getPropertyPath() + " "  + e.getMessage())
                    .collect(Collectors.toList());
            StringBuilder sb = new StringBuilder();
            msglist.forEach(str -> sb.append(str).append(","));
            String errmsg = sb.toString();
            log.error("-------------parameter error: {}", errmsg);
            return AjaxResult.error(errmsg);
        }
        return null;
    }

}
