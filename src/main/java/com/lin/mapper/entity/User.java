package com.lin.mapper.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author lin
 * @version 1.0
 * @date 2020/4/15 22:12
 */
@Data
@TableName(value = "tbl_user")
public class User implements Serializable {

    private static final long serialVersionUID = 8626503226301751745L;

    private Integer id;

    private String name;

}
