
package com.example.chatIslem.kafka;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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

import com.example.chatIslem.models.chat.Conversation;
import com.example.chatIslem.models.chat.Messages;
import com.example.chatIslem.models.user.UserModel;
import com.example.chatIslem.services.user.UserService;
import com.example.chatIslem.utils.TokenUtils;

import jakarta.validation.Valid;



@RestController
@RequestMapping("/messages")
public class MessageKafkaController {

	//private final MessageKafkaService messageKafkaService;
	@Autowired
	TokenUtils tokenUtils;
	@Autowired
	UserService userService;
	@Autowired
	ConversationService conversationService;
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
    public ResponseEntity<?> publish(@RequestBody  MessageDto msg) {
    	  System.out.println(conversationService.getConversationById("64f532dca6d4b53dae1bc8e9"));
       // String idConnecte = tokenUtils.ExtractId();
        String id_Connect=tokenUtils.ExtractId();
        // Récupérez la conversation en fonction de l'ID de la conversation fourni
        if (msg.getConv_id() != null) {
        Conversation conversation = conversationService.getConversationById(msg.getConv_id());
      System.out.println(msg.getContent());
        if (conversation !=null) {
            // Vérifiez si l'utilisateur connecté fait partie des participants de la conversation
            if (conversation.getParticipants().stream().anyMatch(user -> user.getId().equals(id_Connect))) {
                // Créez un nouvel objet Messages (message)
                Messages msgContent = new Messages();
                msgContent.setMessageContent(msg.getContent());
                msgContent.setDateEnvoie(new Date());
                msgContent.setConvId(msg.getConv_id());
                msgContent.setSender(userService.getUser(id_Connect));

                // Obtenez tous les participants de la conversation, sauf l'utilisateur connecté
                List<UserModel> participants = conversation.getParticipants().stream()
                        .filter(user -> !user.getId().equals(id_Connect))
                        .collect(Collectors.toList());

                // Assurez-vous qu'il y a au moins un destinataire
                if (!participants.isEmpty()) {
                    msgContent.setRecipient(participants);
                } else {
                    // Gérez le cas où il n'y a aucun autre participant dans la conversation
                    return new ResponseEntity<>("Vous êtes le seul participant dans la conversation.", HttpStatus.BAD_REQUEST);
                }

                // Ajoutez le message à la liste de messages de la conversation
                conversation.getMessage().add(msgContent);

                // Enregistrez la conversation mise à jour
                conversationService.createNewConv(conversation);

                // Envoyez le message au topic Kafka
                kafkaMessageProducer.sendMessage(msgContent);

                return ResponseEntity.ok("Message envoyé à la conversation avec succès. Expéditeur : " + msgContent.getSender().getUsername());
            } else {
                return new ResponseEntity<>("Vous n'êtes pas autorisé à envoyer un message dans cette conversation.", HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>("La conversation n'existe pas.", HttpStatus.NOT_FOUND);
        }
        }else {
        	return new ResponseEntity<>("L'identifiant de la conversation est null.", HttpStatus.BAD_REQUEST);
        }
    }
    /*@PostMapping("/publish")
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
	
	
	}*/
	 

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






