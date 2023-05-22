package com.example.demo1;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import java.lang.Math;

@RestController
@Slf4j
public class Controller {
    private final RabbitTemplate rabbitTemplate;

    @Autowired
    Controller(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @PostMapping("/randomTimesMakeSleep")
    void randomTimesMakeSleep(Long times) {
        log.info("1");
        for (int i = 0; i < times; ++i) {
            rabbitTemplate.convertAndSend(SomeConfiguration1.NAME1, (int) (Math.random() * 7));
        }
    }
}