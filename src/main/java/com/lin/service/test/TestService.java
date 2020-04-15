package com.lin.service.test;

import com.lin.mapper.UserMapper;
import com.lin.mapper.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author lin
 * @version 1.0
 * @date 2020/4/15 22:18
 */
@Service
public class TestService {

    @Autowired
    private UserMapper userMapper;

    @Transactional
    //由于id是int型,concat要求变量必须为String,所以强转一下
    public User getUser(TestRequest test) {
        return getId(test.getId());
    }

    @Cacheable(value="id=", key="T(String).valueOf(#id)")
    public User getId(Integer id) {
        return userMapper.selectById(id);
    }


}
