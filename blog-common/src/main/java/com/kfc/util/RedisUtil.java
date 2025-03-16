package com.kfc.util;

import org.redisson.api.RBatch;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;
import java.util.concurrent.TimeUnit;

public class RedisUtil {

    private final RedissonClient redisson;

    @Autowired // 通过构造器注入
    public RedisUtil(RedissonClient redisson) {
        this.redisson = redisson;
    }

    // 新增管道批量操作示例
    public void batchSet(Map<String, Object> dataMap) {
        RBatch batch = redisson.createBatch();
        dataMap.forEach((k, v) -> {
            batch.getBucket(k).setAsync(v);
        });
        batch.execute();
    }

    // 改进的分布式锁方法（支持自动续期）
    public boolean tryLockWithWatchdog(String lockKey, long waitTime, long leaseTime) {
        RLock lock = redisson.getLock(lockKey);
        try {
            return lock.tryLock(waitTime, leaseTime, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return false;
        }
    }
}
