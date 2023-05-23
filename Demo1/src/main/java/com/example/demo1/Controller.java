package com.example.demo1;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;

import java.lang.Math;

@RestController
@Slf4j
public class Controller {
    private final RabbitTemplate rabbitTemplate;
    private final Counter requests;

    @Autowired
    Controller(RabbitTemplate rabbitTemplate, MeterRegistry meterRegistry) {
        this.rabbitTemplate = rabbitTemplate;
        requests = meterRegistry.counter("counter");
    }

    @PostMapping("/randomTimesMakeSleep")
    void randomTimesMakeSleep(Long times) {
        log.info("1");
        for (int i = 0; i < times; ++i) {
            rabbitTemplate.convertAndSend(SomeConfiguration1.NAME1, (int) (Math.random() * 7));
            requests.increment();
        }
    }
}