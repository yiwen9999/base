package com.hex.base;


import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * User: hexuan
 * Date: 2018/4/9
 * Time: 下午2:50
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j // 用lombok便捷启用log
public class LoggerTest {

//    private final Logger logger = LoggerFactory.getLogger(LoggerTest.class);

    @Test
    public void test1(){
//        logger.debug("debug...");
//        logger.info("info...");
//        logger.error("error...");

        String name = "hex";
        String password = "batterman";
        log.debug("debug...");
        log.info("info...");
        log.error("error...");
        log.info("name {}, password {}",name,password);
    }
}
