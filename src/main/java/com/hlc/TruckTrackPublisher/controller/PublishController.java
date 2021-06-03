package com.hlc.TruckTrackPublisher.controller;

import ionic.Msmq.Message;
import ionic.Msmq.MessageQueueException;
import ionic.Msmq.Queue;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.UUID;

@RestController
@Slf4j

public class PublishController {

    @Autowired
    Queue queue;
    @PostMapping("/publish")
    public void publish(@RequestBody String payload) throws MessageQueueException, UnsupportedEncodingException {
        log.info("Sending.....");

        Message msg= new Message(payload, "inserted by TruckTrackPublisher", UUID.randomUUID().toString());

        queue.send(msg);
        log.info("Message sent to queue!!");
    }

    @PostMapping(value = "/publish-json", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void publishJson(@RequestBody String payload) throws MessageQueueException, UnsupportedEncodingException {

        log.info("Converting json to xml.....");
        JSONObject json = new JSONObject(payload);
        String xml = XML.toString(json);
        log.info(" xml data after conversion ....."+ xml);
        log.info("Sending xml .....");

        Message msg= new Message(xml, "inserted by TruckTrackPublisher", UUID.randomUUID().toString());

        queue.send(msg);
        log.info("Message sent to queue!!");
    }

    @ExceptionHandler
    public ResponseEntity exception(Exception e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
