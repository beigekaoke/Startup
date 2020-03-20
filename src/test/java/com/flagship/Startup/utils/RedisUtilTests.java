package com.flagship.Startup.utils;

import static org.junit.Assert.*;

import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.flagship.startup.utils.RedisUtil;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RedisUtilTests {
	
	@Autowired
	private RedisUtil redisUtil;
	
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;
	
	public static final String prefix = "src_*";

	@Test
	public void test() {
		Set<Object> keys = redisTemplate.opsForHash().keys(prefix);
		for(Object key : keys){
			System.out.println(key);
		}
	}

}
