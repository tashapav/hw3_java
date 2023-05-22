package com.example.demo2;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class Worker {
    private final RabbitTemplate rabbitTemplate;

    Worker(RabbitTemplate rabbitTemplate)  { this.rabbitTemplate = rabbitTemplate; }
    @RabbitListener(queues = {SomeConfiguration2.NAME1})
    public void workerdo(Integer sleepCount, MessageHeaders headers, @Header(AmqpHeaders.CONSUMER_QUEUE) String queueName) throws InterruptedException {
        try {
            log.info("2");
            Thread.sleep(sleepCount);
            rabbitTemplate.convertAndSend(SomeConfiguration2.NAME2, sleepCount);
        } catch (Exception e) {
            log.info("Exception in worker");
        }
    }
}