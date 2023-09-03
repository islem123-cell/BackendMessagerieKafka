package com.example.chatIslem.kafka;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.chatIslem.DTOs.request.MessageDto;
import com.example.chatIslem.chat.services.ConversationService;
import com.example.chatIslem.chat.services.MessageService;
import com.example.chatIslem.models.chat.Messages;
import com.example.chatIslem.services.user.UserService;
import com.example.chatIslem.utils.TokenUtils;



@RestController
@RequestMapping("/messages")
public class MessageKafkaController {

	//private final MessageKafkaService messageKafkaService;
	@Autowired
	TokenUtils tokenUtils;
	@Autowired
	UserService userService;

  /*  public MessageKafkaController(MessageKafkaService messageService) {
        this.messageKafkaService = messageService;
    }
*/
	private KafkaMessageProducer kafkaMessageProducer;
	
    public MessageKafkaController(KafkaMessageProducer kafkaMessageProducer) {
		super();
		this.kafkaMessageProducer = kafkaMessageProducer;
	}
    
    @PreAuthorize("hasRole('ROLE_USER')")   
    
    
    @PostMapping("/publish")
	public ResponseEntity<?> publish(@RequestBody MessageDto msg){
	String id_Connect=tokenUtils.ExtractId();
	Messages msgContent= Messages.builder()
			
			.messageContent(msg.getContent())
			.DateEnvoie(new Date())
			.sender(userService.getUser(id_Connect))
			.recipient(userService.getUser(msg.getRecipient_id()))
			.build();
	if(!msgContent.getSender().getId().equals(msgContent.getRecipient().getId())) {
		
				
			 kafkaMessageProducer.sendMessage(msgContent);
			 
				return ResponseEntity.ok("sent message to the kafka topic  sender is : " +msgContent.getSender().getUsername()+" And reciver is : "+ msgContent.getRecipient().getUsername());
	}else {
		return new ResponseEntity("Failed to send message ! ",HttpStatus.BAD_REQUEST);
	}
	
	
	}
	 

 /*   @GetMapping("/api/messages/{senderId}/{recipientId}")
    public ResponseEntity<?> findChatMessages ( @PathVariable String senderId,
                                                @PathVariable String recipientId) {
        return ResponseEntity
                .ok(MessageService.findChatMessages(senderId, recipientId));
    }*/

    

   /* @MessageMapping("/sendMessage")
    @SendTo("{receiver}/topic")
    public Message broadcastGroupMessage(@Payload Message message) {
        return message;
    }
*/
	/*@PostMapping("/publish")
    public ResponseEntity<String> sendMessage(@RequestBody Messages messageContent) {
        try {
        	messageKafkaService.sendMessage(messageContent.getSender(), messageContent.getRecipient(), messageContent.getMessageContent());
            return ResponseEntity.ok("Message sent successfully.");
        
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to send message: " + e.getMessage());
        }
    }
    
   */
    }






