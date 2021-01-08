package com.example.messagingstompwebsocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ScheduledUpdatesOnTopic {

    @Autowired
    private SimpMessagingTemplate template;

    // this will send a message to an endpoint on which a client can subscribe
    @Scheduled(fixedRate = 10000)
    public void trigger() {
        this.template.convertAndSend("/topic/greetings", new Greeting("hello from scheduler"));
    }

}