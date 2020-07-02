package com.mygradle.commons;

import com.mygradle.commons.cache.NonDBRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.Collections;

@EnableCaching
@SpringBootApplication
public class SpringCachingApp implements CommandLineRunner {
    private static final Logger logger = LoggerFactory.getLogger(SpringCachingApp.class);

    private NonDBRepository nonDBRepository;

    @Autowired
    public void setNonDBRepository(NonDBRepository nonDBRepository) {
        this.nonDBRepository = nonDBRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringCachingApp.class, args);
    }

    @Override
    public void run(String ... args){
        logger.info("자동차 정보");
        logger.info("no1::" + nonDBRepository.getByName("Lamborghini"));
        logger.info("no2::" + nonDBRepository.getByName("Porsche"));
        logger.info("no3::" + nonDBRepository.getByName("BMW"));
        logger.info("no4::" + nonDBRepository.getByName("Benz"));
        logger.info("no5::" + nonDBRepository.getByName("Ferrari"));

        logger.info("no1::" + nonDBRepository.getByName("Lamborghini"));
        logger.info("no1::" + nonDBRepository.getByName("Lamborghini"));
        logger.info("no2::" + nonDBRepository.getByName("Porsche"));

        logger.info("cacheManager::" + nonDBRepository.getByNameWithCacheManager("Lamborghini"));
        logger.info("cacheManager::" + nonDBRepository.getByNameWithCacheManager("Lamborghini"));
        logger.info("cacheManager::" + nonDBRepository.getByNameWithCacheManager("Porsche"));
    }
/*
    @Bean
    public CacheManager cacheManager(){
        SimpleCacheManager cacheManager = new SimpleCacheManager();
        Cache cache = new ConcurrentMapCache("carName");
        cacheManager.setCaches(Collections.singletonList(cache));
        return cacheManager;
    }

 */
}
