package com.example.demo.services;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitService {

    @RabbitListener(queues = "pact-demo")
    public void listen(String in){
        System.out.println("Message received " + in);
    }
}
