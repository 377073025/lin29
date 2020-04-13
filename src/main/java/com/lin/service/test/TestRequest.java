package com.lin.service.test;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author lin
 * @version 1.0
 * @date 2020/4/1 21:09
 */
@Data
public class TestRequest {

    @NotBlank
    @ApiModelProperty(value = "param1")
    private String param1;

    @NotBlank
    @ApiModelProperty(value = "param2")
    private String param2;

}
