package com.aladen.service.kafka.consumer;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

@Service
public class ConsumerService {
    private Logger logger = LoggerFactory.getLogger(ConsumerService.class);


    /**
     *  使用 Acknowledgment 或者使用 consumer 效果是一样的，都需要手动去提交信息
     * @param record
     * @param consumer
     */
    @KafkaListener(topics = "kafka-demo", groupId = "group-id")
    public void consumer(ConsumerRecord<String,String> record, Consumer<String,String> consumer) {
        logger.info("topic-first get topic : {}, message:{}", record.topic(), record.value());
        logger.info("partition:{};offset:{}", record.partition(), record.offset());
        consumer.commitSync();
    }

    @KafkaListener(topics = {"topic-demo"}, groupId = "topic-id", containerFactory = "kafkaListenerContainerFactory")
    public void consumer(ConsumerRecord<String,String> record, Acknowledgment ack) {
        logger.info("topic:{}", record.topic());
        logger.info("partition:{}", record.partition());
        logger.info("offset:{}", record.offset());
        logger.info("value:{}", record.value());
        ack.acknowledge();
    }

    @KafkaListener(topics = {"batch-demo"}, groupId = "batch-id", containerFactory = "kafkaListenerContainerFactory")
    public void batchConsumer(ConsumerRecords<String,String> records, Acknowledgment ack) {
        if (!records.isEmpty()) {
            for (ConsumerRecord record : records) {
                logger.info("offset:{};value:{}", record.offset(), record.value());
                if (record.offset() % 2 == 0) {
                    ack.acknowledge();
                }
            }
        }
    }
}
