package com.example.demo.controller;

import com.example.demo.model.TestModel;
import com.example.demo.service.KafkaProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/kafka")
public class DemoController {

    @Autowired
    KafkaProducerService kafkaSender;

    @PostMapping(value = "/producer")
    public ResponseEntity<String> producer(@RequestBody TestModel message) {
        boolean result = kafkaSender.send(message);

        if (result) {
            return ResponseEntity.ok("Message sent successfully");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to send message");
        }    }

}
