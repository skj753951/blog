package com.kfc.config;

import org.apache.commons.lang3.StringUtils;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource("classpath:config/redis.properties")
public class RedisConfig {

    private static final String REDIS_MODE = "redis.mode";
    private static final String REDIS_SINGLE = "single";
    private static final String REDIS_CLUSTER = "cluster";


    @Autowired
    private Environment env;

    public RedissonClient redissonClient() {
        Config config = new Config();
        String mode = env.getProperty(REDIS_MODE, REDIS_SINGLE);
        if (StringUtils.equalsIgnoreCase(mode, REDIS_SINGLE)) {
            initSingleServerConfig(config);
        } else if (StringUtils.equalsIgnoreCase(mode, REDIS_CLUSTER)) {
            initClusterServerConfig(config);
        }
        config.setThreads(16).setNettyThreads(32);

        return Redisson.create(config);
    }

    private void initSingleServerConfig(Config config) {
        SingleServerConfig serverConfig = config.useSingleServer()
                .setAddress("redis://" + env.getProperty("redis.host") + ":" + env.getProperty("redis.port"))
                .setPassword(env.getProperty("redis.password"))
                .setDatabase(Integer.parseInt(env.getProperty("redis.database", "0")))
                .setConnectionPoolSize(Integer.parseInt(env.getProperty("redis.pool.maxSize", "64")))
                .setConnectionMinimumIdleSize(Integer.parseInt(env.getProperty("redis.pool.minIdle", "10")))
                .setIdleConnectionTimeout(Integer.parseInt(env.getProperty("redis.pool.idleTimeout", "10000")))
                .setConnectTimeout(Integer.parseInt(env.getProperty("redis.pool.connectTimeout", "3000")))
                .setTimeout(Integer.parseInt(env.getProperty("redis.pool.commandTimeout", "3000")));
    }

    private void initClusterServerConfig(Config config) {
        String[] nodes = env.getProperty("redis.cluster.nodes").split(",");
        config.useClusterServers().addNodeAddress(nodes)
                .setScanInterval(Integer.parseInt(env.getProperty("redis.cluster.scanInterval", "2000")));
    }

}
