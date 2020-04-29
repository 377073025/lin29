package com.lin.service.test;

import com.lin.config.RedisCache;
import com.lin.mapper.UserMapper;
import com.lin.mapper.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author lin
 * @version 1.0
 * @date 2020/4/15 22:18
 */
@Slf4j
@Service
public class TestService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisCache redisCache;

    //由于id是int型,concat要求变量必须为String,所以强转一下
    @Cacheable(value="user", key="#test.id")
    public User getUser(TestRequest test) {
        log.info("------数据库查询------");
        User user = userMapper.selectById(test.getId());
        return user;
    }

    public User getId(String id) {
        User user = redisCache.getCacheObject(id);
        if(user == null){
            user = userMapper.selectById(id);
            if(user != null){
                redisCache.setCacheObject(id, user);
            }
        }

        return user;
    }

}
