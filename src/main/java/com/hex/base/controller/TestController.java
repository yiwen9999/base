package com.hex.base.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * User: hexuan
 * Date: 2018/4/9
 * Time: 上午11:29
 */
@RestController
@Slf4j
public class TestController {
    @GetMapping("/test1")
    public Object test1() {
        log.info("日志测试哟~");
        return "ok,you are the best one~";
    }
}
