package com.example.demo1;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class Sender {
    @RabbitListener(queues = {SomeConfiguration1.NAME2})
    public void result(Integer sleepCount, MessageHeaders headers, @Header(AmqpHeaders.CONSUMER_QUEUE) String queueName) {
        log.info("Was sleeping for" + sleepCount);
    }
}