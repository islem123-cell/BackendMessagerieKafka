package com.example.chatIslem.controllers;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.chatIslem.chat.services.MessageService;
import com.example.chatIslem.models.chat.Messages;
import com.example.chatIslem.utils.TokenUtils;



@RestController
@CrossOrigin
@RequestMapping("/api/messages")
public class MessageController {

	@Autowired 
	private TokenUtils tokenUtils;
	@Autowired
	
	private final MessageService messageService;
	public MessageController(MessageService messageService) {
		super();
		this.messageService = messageService;
	}
	
	 @PostMapping
	    public ResponseEntity<Messages> createMessage(@RequestBody Messages messageContent) {
	        Messages createdMessage = messageService.createMessage(messageContent);
	        messageContent.setDateEnvoie(new Date (System.currentTimeMillis()));
	        return ResponseEntity.status(HttpStatus.CREATED).body(createdMessage);
	 }
	 
	 @GetMapping("/getAllMessages")
	  public ResponseEntity<?> getAllMessages(){
			List<Messages> message = messageService.getAllMessages();
			if(message.size()>0) {
				return new ResponseEntity<List<Messages>>(message, HttpStatus.OK);}
				else {
					return new ResponseEntity<>("No message available", HttpStatus.NOT_FOUND);
				}
			}
	
	
	
}

