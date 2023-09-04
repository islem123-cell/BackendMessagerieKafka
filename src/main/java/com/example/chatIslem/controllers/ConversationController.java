package com.example.chatIslem.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.chatIslem.DTOs.request.ConversationRequest;
import com.example.chatIslem.chat.services.ConversationService;
import com.example.chatIslem.exceptions.EntityNotFoundException;
import com.example.chatIslem.models.chat.Conversation;
import com.example.chatIslem.models.chat.Messages;
import com.example.chatIslem.models.user.UserModel;
import com.example.chatIslem.services.user.UserService;
import com.example.chatIslem.utils.TokenUtils;


@CrossOrigin(origins = "http://localhost:3000")

@RestController
@RequestMapping("/api/conv")
public class ConversationController {

	@Autowired
	ConversationService conversationService;
	@Autowired
    UserService userService;
    @Autowired
    TokenUtils tokenUtils;
   /* @Autowired
    private KafkaTemplate kafkaTemplate;*/
  
    @GetMapping("/AllUser")
    public ResponseEntity<?> getAllUsers(){
        return new ResponseEntity<>(userService.getAllUser(),HttpStatus.OK);
      }

	  @PostMapping("/create")
	    public ResponseEntity<?> createConversation(@RequestBody ConversationRequest request) {
		  String idConect=tokenUtils.ExtractId();
		  System.out.println(request.getSelectedUserIds().size());
			if(request.getSelectedUserIds().size() >= 1 ) {
				if(!request.getSelectedUserIds().contains(idConect)) {
					
					Conversation conv=new Conversation();
					
					conv.setChatName(request.getChatName());
					conv.getParticipants().add(userService.getUser(idConect));
					for(String userId:request.getSelectedUserIds()) {
						UserModel user=userService.getUser(userId);
						if(user != null) {
							conv.getParticipants().add(user);
							
						/*	 // Créez une réponse JSON avec le succès et les données pertinentes
					        Map<String, Object> response = new HashMap<>();
					        response.put("success", true);
					        response.put("data",conv);    */
						}
					}
					conversationService.createNewConv(conv);
					return ResponseEntity.ok("Conversation cree avec succes ");
				}else {
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("l'utilisateur connecte ne peut pas etre inclus dans la liste id ");
				}
				
				
			}else {
				
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("La liste de participants doit contenir au moins deux utilisateur ");
				
				
			}			
			}
			
	  
			
		
		 @GetMapping("/getAllConversation")
		  public ResponseEntity<?> getAllConversations(){
				List<Conversation> Conversation = conversationService.getAllConversations();
				if(Conversation.size()>0) {
					return new ResponseEntity<List<Conversation>>(Conversation, HttpStatus.OK);}
					else {
						return new ResponseEntity<>("No Conversation available", HttpStatus.NOT_FOUND);
					}
				} 
	  
		 
		 @GetMapping("/{id}")
		    public Conversation getConversationById(@PathVariable String id) {
		        // Utilisez le service pour récupérer la conversation par son ID
		        return conversationService.getConversationById(id);
		    }
		 
		 
		 @DeleteMapping("/{id}")
		    public ResponseEntity<String> deleteConversation(@PathVariable String id) {
		        boolean conversation = conversationService.deleteConversation(id);

		        if (conversation) {
		            return new ResponseEntity<>("Conversation supprimée avec succès", HttpStatus.OK);
		        } else {
		            return new ResponseEntity<>("La conversation n'a pas été trouvée ou n'a pas pu être supprimée", HttpStatus.NOT_FOUND);
		        }
		    }
		 
		 
		 
/*	public void createConversation(@RequestBody  Messages message) {
		
		KafkaTemplate<String, Messages> kafkaTemplate;
    }

	 /*@GetMapping("/between")
	public ResponseEntity<Conversation> getConversationBetweenUsers(
			@RequestParam String userId1,
			@RequestParam String userId2) {
		UserModel user1 = userService.getUser(userId1) ;
		UserModel user2 = userService.getUser(userId2) ;
		
		if(user1 == null || user2 == null ) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		
		Conversation conversation = conversationService.getConversationBetweenUsers(user1, user2);
		
		if ( conversation == null ) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
    return ResponseEntity.ok(conversation);
}
	*/
	
	/*
	@PostMapping("/create")
    public ResponseEntity<?> createConversation(@RequestBody ConversationRequest request) {
		Conversation conversation = conversationService.createConversation(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(conversation);
    }*/
	  /*
	@PostMapping("/{conversationId}/add-message")
	public ResponseEntity<Void> addMessageToConversation(@PathVariable String conversationId, @RequestBody Messages message) {
	    
		try {
	        // Appel à la méthode de service pour ajouter le message à la conversation
	        conversationService.addMessageToConversation(conversationId, message);

	        // Retournez une réponse HTTP 201 (Created) pour indiquer que l'opération a réussi
	        return ResponseEntity.status(HttpStatus.CREATED).build();
	    } catch (EntityNotFoundException e) {
	        // Si la conversation n'est pas trouvée, retournez une réponse HTTP 404 (Not Found)
	        return ResponseEntity.notFound().build();
	    } catch (Exception e) {
	        // En cas d'autres erreurs, retournez une réponse HTTP 500 (Internal Server Error)
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	    }
	    
	}


	    @GetMapping("/{conversationId}/messages")
	    public ResponseEntity<List<Messages>> getConversationMessages(@PathVariable String conversationId) {
	        List<Messages> messages = conversationService.getConversationMessages(conversationId);
	        return ResponseEntity.ok(messages);
	    }*/
	  
/*	 @GetMapping("/getAllConversation")
	  public ResponseEntity<?> getAllConversations(){
			List<Conversation> Conversation = conversationService.getAllConversations();
			if(Conversation.size()>0) {
				return new ResponseEntity<List<Conversation>>(Conversation, HttpStatus.OK);}
				else {
					return new ResponseEntity<>("No Conversation available", HttpStatus.NOT_FOUND);
				}
			} 
	
	  @DeleteMapping("/deleteConversationBy{id}")
	    public ResponseEntity<Void> deleteConversation(@PathVariable String id) {
	        boolean deleted = conversationService.deleteConversation(id);
	        if (deleted) {
	            return ResponseEntity.noContent().build();
	        } else {
	            return ResponseEntity.notFound().build();
	        }}
	  
	  @PostMapping("/{conversationId}/addParticipant")
	    public ResponseEntity<String> addParticipantToConversation(@PathVariable String conversationId,@RequestBody UserModel participant) 
	  {
	        // Appelez la méthode addParticipantToConversation du service
	        conversationService.addParticipantToConversation(conversationId, participant);
	        
	        // Vous pouvez renvoyer une réponse personnalisée si nécessaire
	        return ResponseEntity.ok("Participant ajouté à la conversation avec succès !");
	    }
	  
	  
	  */
	  
}
