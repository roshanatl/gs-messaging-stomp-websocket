package com.example.messagingstompwebsocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;


@RestController
public class GreetingController {

	private static final Logger logger = LoggerFactory.getLogger(GreetingController.class);
	@Autowired
	private SimpMessagingTemplate template;

	@Autowired
	private TaskService taskService;


	@PostMapping(value = "/upload-file")
	public Greeting uploadVinList(HttpServletRequest httpServletRequest, @RequestParam(name = "file") MultipartFile file,
											  @RequestParam(name = "topic") String topic)
			throws ServletException, Exception {
		logger.info("upload-excel invoked.");
		if (file.getSize() == 0) {
			throw new RuntimeException("Please select file to upload");
		}
		taskService.asyncprocessFile(topic,file);
		return new Greeting("upload-excel complete" );

	}

	@GetMapping (value = "/socket/topic")
	public String getSocket()
			throws ServletException, Exception {
		logger.info("upload-excel invoked.");
		String topic= "/topic/uploadProgress/"+System.currentTimeMillis();
		return topic;
	}







}
