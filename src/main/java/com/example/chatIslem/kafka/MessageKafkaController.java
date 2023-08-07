package com.example.chatIslem.kafka;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.chatIslem.models.chat.Messages;



@RestController
@RequestMapping("/messages")
public class MessageKafkaController {

	//private final MessageKafkaService messageKafkaService;
	

  /*  public MessageKafkaController(MessageKafkaService messageService) {
        this.messageKafkaService = messageService;
    }
*/
	private KafkaMessageProducer kafkaMessageProducer;
	
    public MessageKafkaController(KafkaMessageProducer kafkaMessageProducer) {
		super();
		this.kafkaMessageProducer = kafkaMessageProducer;
	}
    
 @PostMapping("/publish")
	public ResponseEntity<String> publish(@RequestBody Messages msgContent){
	
    	kafkaMessageProducer.sendMessage(msgContent);
		return ResponseEntity.ok("sent message to the kafka topic");
	}
	 

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






