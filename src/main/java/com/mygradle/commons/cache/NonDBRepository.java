package com.mygradle.commons.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class NonDBRepository {

    private final long sleepTime = 3000L;
    private final Logger logger = LoggerFactory.getLogger(NonDBRepository.class);
    private CacheManager cacheManager;

    @Autowired
    public void setCacheManager(CacheManager cacheManager){
        this.cacheManager = cacheManager;
    }

    private void makeSlowService(){
        logger.info("start sleep");
        try{
            Thread.sleep(sleepTime);
        }catch(InterruptedException ie){
            throw new IllegalStateException(ie);
        }
        logger.info("end sleep");
    }

    private Car randomCarColor(Car car){
        Random random = new Random();
        random.setSeed(System.currentTimeMillis());

        String[] colors = {"red", "orange", "yellow", "green", "blue"};

        for (int i = 0; i < colors.length; i++) {
            int rand = random.nextInt(5);
            car.setColor(colors[rand]);
        }
        return car;
    }

    @Cacheable(cacheNames = "carName", key = "#name")
    public Car getByName(String name){
        makeSlowService();
        Car myCar = new Car(name);
        return randomCarColor(myCar);
    }

    public Car getByNameWithCacheManager(String name){
        Cache cache = cacheManager.getCache("carName");
        assert cache != null;
        return randomCarColor(cache.get(name, Car.class));
    }
}

