package com.aladen.common.interceptor;

import org.apache.kafka.clients.producer.ProducerInterceptor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class KafkaProducerInterceptor implements ProducerInterceptor<String,String> {
    private Logger logger = LoggerFactory.getLogger(KafkaProducerInterceptor.class);
    private AtomicInteger successNum = new AtomicInteger(0);
    private AtomicInteger failNum = new AtomicInteger(0);

    @Override
    public ProducerRecord<String, String> onSend(ProducerRecord<String, String> producerRecord) {
        String value = "pre1-" + producerRecord.value();
        return new ProducerRecord(producerRecord.topic(),producerRecord.partition(), producerRecord.key(),value, producerRecord.headers());
    }

    @Override
    public void onAcknowledgement(RecordMetadata recordMetadata, Exception e) {
        if (e != null) {
            logger.error("消息发送失败;topic:{}, partition:{}" ,recordMetadata.topic() , recordMetadata.partition());
            failNum.incrementAndGet();
        } else {
            logger.info("消息发送成功;topic:{}, partition:{}" ,recordMetadata.topic() , recordMetadata.partition());
            successNum.incrementAndGet();
        }
    }

    @Override
    public void close() {

    }

    @Override
    public void configure(Map<String, ?> map) {

    }
}
