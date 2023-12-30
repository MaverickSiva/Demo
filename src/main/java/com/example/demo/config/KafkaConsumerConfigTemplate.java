package com.example.demo.config;

import com.example.demo.model.TestModel;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.converter.StringJsonMessageConverter;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.*;

@Configuration

public class KafkaConsumerConfigTemplate {

    @Value("${spring.kafka.producer.bootstrap-servers}")
    private String bootstrapServers;

    @Value(value = "${user.topic.group.id}")
    private String userGroupId;

    public ConsumerFactory<String, TestModel> testModelConsumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, userGroupId);
        props.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
        return new DefaultKafkaConsumerFactory<>(
                props,
                new StringDeserializer(),  // Key deserializer
                new JsonDeserializer<>(TestModel.class)  // Value deserializer
        );
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, TestModel> testKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, TestModel> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(testModelConsumerFactory());
        return factory;
    }


}
