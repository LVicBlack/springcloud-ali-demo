package com.vic.microserviceprovideruser;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

@Component
public class Cache {

    private LoadingCache<String, String> cache;

    @PostConstruct
    public void init() {
        cache = CacheBuilder.newBuilder().maximumSize(100)
                .expireAfterWrite(60 * 60, TimeUnit.SECONDS)
                .build(new CacheLoader<String, String>() {
                    @Override
                    public String load(String key) throws Exception {
                        return key;
                    }
                });
    }

    public String getCacheByKey(String key) {
        AtomicBoolean atomicBoolean = new AtomicBoolean();
        try {
            return cache.get(key);
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }
}
