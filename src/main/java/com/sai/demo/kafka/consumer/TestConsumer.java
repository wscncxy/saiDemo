package com.sai.demo.kafka.consumer;

import com.sai.demo.kafka.tool.SaiKafkaConsumer;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class TestConsumer extends SaiKafkaConsumer<String, Object> {

    public TestConsumer(){
        super("sai.testKakfa.consumer");
    }

    @Override
    public void processMessage(ConsumerRecord<String, Object> record) {
        log.info("kafka msg partation {}, value:{}",record.partition(),record.value());
    }
}
