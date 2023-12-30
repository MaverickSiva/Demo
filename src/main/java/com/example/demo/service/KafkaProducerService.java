package com.example.demo.service;

import ch.qos.logback.classic.Logger;
import com.example.demo.model.TestModel;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {

    @Autowired
    private final KafkaTemplate<String, TestModel> createTestModelKafkaTemplate;

    String kafkaTopic = "test-topic";

    Logger logger;

    public KafkaProducerService(KafkaTemplate<String, TestModel> createTestModelKafkaTemplate) {
        this.createTestModelKafkaTemplate = createTestModelKafkaTemplate;
    }

    public boolean send(TestModel message) {
        createTestModelKafkaTemplate.send(kafkaTopic, message);
        return true;
    }

    @Bean
    public NewTopic createTopic(){
        return new NewTopic(kafkaTopic,3,(short) 1);
    }
}



