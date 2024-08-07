package com.mata;

import com.mata.utils.RedisCommonKey;
import org.junit.jupiter.api.Test;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RLock;
import org.redisson.api.RReadWriteLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.concurrent.TimeUnit;

@SpringBootTest
class ReadShareAndBuyApplicationTests {
    @Autowired
    @Qualifier("userBloom")
    private RBloomFilter<Integer> userBloom;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedissonClient redissonClient;

    @Test
    void contextLoads() {
        String s = stringRedisTemplate.opsForValue().get("test");
        System.out.println(s);
    }

    @Test
    void testWriteLock(){
        RReadWriteLock readWriteLock = redissonClient.getReadWriteLock("RWL");
        RLock rLock = readWriteLock.writeLock();
        try {
            rLock.lock(15, TimeUnit.SECONDS);
            stringRedisTemplate.opsForValue().set("test","test3");
        }finally {
             rLock.unlock();
        }
    }

    @Test
    void testReadLock(){
        RReadWriteLock readWriteLock = redissonClient.getReadWriteLock("RWL");
        RLock rLock = readWriteLock.readLock();
        try {
            rLock.lock(15, TimeUnit.SECONDS);
            String s = stringRedisTemplate.opsForValue().get("test");
            System.out.println(s);
        }finally {
             // rLock.unlock();
        }
    }

    @Test
    void testWriteLock1(){
        RReadWriteLock readWriteLock = redissonClient.getReadWriteLock("RWL");
        RLock rLock = readWriteLock.writeLock();
        try {
            rLock.lock(15, TimeUnit.SECONDS);
            stringRedisTemplate.opsForValue().set("test","test1");
            System.out.println("test1");
        }finally {
            //rLock.unlock();
        }
    }

    @Test
    void testWriteLock2(){
        RReadWriteLock readWriteLock = redissonClient.getReadWriteLock("RWL");
        RLock rLock = readWriteLock.writeLock();
        try {
            rLock.lock(15, TimeUnit.SECONDS);
            stringRedisTemplate.opsForValue().set("test","test2");
            System.out.println("test2");
        }finally {
            //rLock.unlock();
        }
    }

    @Test
    void testLock() throws InterruptedException {
        String userJson = stringRedisTemplate.opsForValue().get(RedisCommonKey.USER_ID_PRE_KEY);
        System.out.println(userJson);
    }

}
