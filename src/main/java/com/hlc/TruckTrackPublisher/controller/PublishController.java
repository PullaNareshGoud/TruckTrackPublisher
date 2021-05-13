package com.hlc.TruckTrackPublisher.controller;

import ionic.Msmq.Message;
import ionic.Msmq.MessageQueueException;
import ionic.Msmq.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.util.UUID;

@RestController

public class PublishController {

    @Autowired
    Queue queue;
    @PostMapping("/publish")
    public void publish(@RequestBody String payload) throws MessageQueueException, UnsupportedEncodingException {
        System.out.println("Sending.....");

        Message msg= new Message(payload, "inserted by TruckTrackPublisher", UUID.randomUUID().toString());

        queue.send(msg);
        System.out.println("Message sent to queue!!");
    }
}
