package com.bw.dao;

import com.bw.bean.User;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

/**
 * Created by a on 2017/7/28.
 */
@Transactional
@CacheConfig(cacheNames="users")
public interface UserRepository extends JpaRepository<User,Integer> {
    @Cacheable
    @Query(" from User where name=? and pwd=?")
   User login(String name, String pwd);

    @Query("select  id,name,pwd,age.sex,hobby from User Where name=?1")
    User byname(String name);
}
