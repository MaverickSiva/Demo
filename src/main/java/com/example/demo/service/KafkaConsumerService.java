package com.example.demo.service;

import com.example.demo.model.TestModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;


@Service
public class KafkaConsumerService {

    private static final Logger logger = LoggerFactory.getLogger(KafkaConsumerService.class);

    @KafkaListener(topics="test-topic", groupId="group-id", containerFactory="testKafkaListenerContainerFactory")
    public void consume(TestModel message){
        logger.info("The message has been consumed --> " + message.toString());
    }
}
