package com.hlc.trucksignaling.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hlc.trucksignaling.domain.model.TmsMsg;
import com.hlc.trucksignaling.util.MarshallerHelper;
import ionic.Msmq.Message;
import ionic.Msmq.MessageQueueException;
import ionic.Msmq.Queue;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.util.UUID;

@RestController
@Slf4j
public class PublishController {

    @Autowired
    MarshallerHelper marshallerHelper;
    @Autowired
    Queue queue;

    @PostMapping(value = "/publish-truck-signal-status", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void publishTruckSignalingStatus(@RequestBody TmsMsg payload) throws MessageQueueException, UnsupportedEncodingException, JAXBException, MalformedURLException, SAXException, JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        log.info("Converting json to xml.....");
        log.info("Given Json: {}", mapper.writeValueAsString(payload));
        //Convert pojo to XML
        String xmlAfterMarshal = marshallerHelper.convertPoJoToXml(payload);
        log.info(" xml data after conversion ..... {}", xmlAfterMarshal);

        log.info("Sending xml .....");

        Message msg = new Message(xmlAfterMarshal, "inserted by TruckTrackPublisher", UUID.randomUUID().toString());
// Send message to queue
        queue.send(msg);
        log.info("Message sent to queue!!");
    }

    //    @ExceptionHandler
    public ResponseEntity<String> exception(Exception e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
