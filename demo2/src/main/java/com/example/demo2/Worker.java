package com.example.demo2;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;


@Service
@Slf4j
public class Worker {
    private final RabbitTemplate rabbitTemplate;
    private final Counter goodResponse;
    private final Counter error;

    Worker(RabbitTemplate rabbitTemplate, MeterRegistry meterRegistry)  {
        this.rabbitTemplate = rabbitTemplate;
        goodResponse = meterRegistry.counter("goodresponse");
        error = meterRegistry.counter("error");
    }
    @RabbitListener(queues = {SomeConfiguration2.NAME1})
    public void workerdo(Integer sleepCount, MessageHeaders headers, @Header(AmqpHeaders.CONSUMER_QUEUE) String queueName) throws InterruptedException {
        try {
            log.info("2");
            Thread.sleep(sleepCount);
            rabbitTemplate.convertAndSend(SomeConfiguration2.NAME2, sleepCount);
            goodResponse.increment();
        } catch (Exception e) {
            error.increment();
            log.info("Exception in worker");
        }
    }
}