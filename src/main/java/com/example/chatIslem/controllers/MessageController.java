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

import com.example.chatIslem.DTOs.request.MessageDto;
import com.example.chatIslem.chat.services.MessageService;
import com.example.chatIslem.kafka.KafkaMessageProducer;
import com.example.chatIslem.models.chat.Messages;
import com.example.chatIslem.models.user.UserModel;
import com.example.chatIslem.services.user.UserService;
import com.example.chatIslem.utils.TokenUtils;



@RestController
@CrossOrigin
@RequestMapping("/api/messages")
public class MessageController {

	@Autowired 
	private TokenUtils tokenUtils;
	@Autowired
	private KafkaMessageProducer  KafkaMessageProducer;
	@Autowired
	private final MessageService messageService;
	
	@Autowired
	private final UserService userService;
	
	
	
	public MessageController(MessageService messageService, UserService userService, KafkaMessageProducer KafkaMessageProducer) {
		super();
		this.messageService = messageService;
		this.KafkaMessageProducer = KafkaMessageProducer;
		this.userService = userService;
	}
	
	 @PostMapping("send-messages")
	    public ResponseEntity<Messages> createMessage(@RequestBody Messages messageContent, UserModel user) {
	       // Messages createdMessage = messageService.createMessage(messageContent);
	      //  messageContent.setDateEnvoie(new Date (System.currentTimeMillis()));
	        //return ResponseEntity.status(HttpStatus.CREATED).body(createdMessage);
		 //Envoyer le msg vers kafka topic 
		 try {
			// Messages createdMessage = messageService.createMessage(messageContent); //Enregistrer le msg ds votre 
			// KafkaMessageProducer.sendMessage(createdMessage);
		  // return ResponseEntity.status(HttpStatus.CREATED).body(createdMessage);
	    
			 


		//  MessageService.saveMessage(messageContent);

		      
			 
			 
			 
			 
			 
			 String senderUsername= user.getUsername() ;
			 UserModel senderUser = userService.getUserByUsername(senderUsername) ;
			 messageContent.setSender(senderUser);
			 messageContent.setDateEnvoie(new Date(System.currentTimeMillis()));
		      Messages createdMessage = messageService.createMessage(messageContent);
		     KafkaMessageProducer.sendMessage(createdMessage);

		     return ResponseEntity.status(HttpStatus.CREATED).body(createdMessage);

			 
		     } catch (Exception e) {
		    	 return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		     }
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

