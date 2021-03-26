package com.example.messagingstompwebsocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class TaskService {
    @Autowired
    private SimpMessagingTemplate template;

    @Async
    public void asyncprocessFile(String topic, MultipartFile file) throws  Exception{
        System.out.println("Execute method asynchronously. "
                + Thread.currentThread().getName());
        //do the actual file processing here.
        for(int i=0;i<100 ;i++) {
            //This is show that some processing happening and response is send via web socket.
            this.template.convertAndSend(topic, new Greeting("processed" + i ));
        }
    }
}
