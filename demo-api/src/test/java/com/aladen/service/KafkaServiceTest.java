package com.aladen.service;

import com.aladen.DemoApplicationTests;
import com.aladen.service.kafka.producer.ProducerService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class KafkaServiceTest extends DemoApplicationTests {

    @Autowired
    private ProducerService producerService;

    @Test
    public void send() {
        producerService.send("topic-first","this is myFirst topic" + System.currentTimeMillis());
    }

}
