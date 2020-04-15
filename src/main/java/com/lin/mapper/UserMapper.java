package com.lin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lin.mapper.entity.User;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

/**
 * @author lin
 * @version 1.0
 * @date 2020/4/15 22:11
 */
@Mapper
@Component
public interface UserMapper extends BaseMapper<User> {

}
