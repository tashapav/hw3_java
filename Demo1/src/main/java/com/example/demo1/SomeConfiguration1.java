package com.example.demo1;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SomeConfiguration1 {
    public static final String NAME1 = "tasks";
    public static final String NAME2 = "responses";

    @Bean
    public Queue queue1() {
        return new Queue(NAME1, true);
    }

    @Bean
    public Queue queue2() {
        return new Queue(NAME2, true);
    }
}