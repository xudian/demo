package com.aladen.service.kafka.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

@Service
public class ConsumerService {
    private Logger logger = LoggerFactory.getLogger(ConsumerService.class);


    @KafkaListener(topics = "kafka-demo", groupId = "group-id", containerFactory = "kafkaListenerContainerFactory")
    public void consumer(ConsumerRecord<String,String> record, Acknowledgment ack) {
        logger.info("topic-first get topic : {}, message:{}", record.topic(), record.value());
        logger.info("partition:{};offset:{}", record.partition(), record.offset());
        ack.acknowledge();
    }
}
