package com.bodyakin.hl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import reactor.netty.resources.LoopResources;

@SpringBootApplication
public class HlApplication {
    private final static Logger log = LoggerFactory.getLogger(HlApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(HlApplication.class, args);
        log.info("Reactor Netty max thread pool size is {}", LoopResources.DEFAULT_IO_WORKER_COUNT);
    }

}
