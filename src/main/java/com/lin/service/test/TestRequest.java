package com.lin.service.test;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author lin
 * @version 1.0
 * @date 2020/4/1 21:09
 */
@Data
public class TestRequest {

    @NotNull
    @ApiModelProperty(value = "1", required = true)
    private Integer id;

}
