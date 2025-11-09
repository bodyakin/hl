package com.bodyakin.hl.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;

@RestController
@RequestMapping("/api")
public class HelloWorldController {
    private static volatile double sink;

    @GetMapping("/hello")
    public HelloDto hello() {
        return new HelloDto("hello");
    }

    @GetMapping("/payload")
    public PayloadDto payload(@RequestParam(defaultValue = "100") long cpuMsec) {
        final long now = System.nanoTime();
        final int cpuCycles = busyWork(cpuMsec);
        final long end = System.nanoTime();
        Duration duration = Duration.ofMillis((end - now) / 1_000_000);
        return new PayloadDto(duration, cpuCycles);
    }

    private int busyWork(long timeMs) {
        long end = System.nanoTime() + timeMs * 1_000_000;
        double x = 0;
        int cycesCount = 0;
        while (System.nanoTime() < end) {
            x += Math.sqrt(Math.random());
            cycesCount++;
        }
        sink = x;
        return cycesCount;
    }

    public record HelloDto(String value) {
    }

    public record PayloadDto(Duration wallTimeSec, int totalCycles) {
    }

}
