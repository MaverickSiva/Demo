package com.example.demo.controller;

import com.example.demo.model.TestModel;
import com.example.demo.service.KafkaProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/kafka")
public class DemoController {

    @Autowired
    KafkaProducerService kafkaSender;

    @PostMapping(value = "/producer")
    public String producer(@RequestBody TestModel message) {
        kafkaSender.send(message);

        return "Message sent to the Kafka Topic java_in_use_topic Successfully";
    }

}
