package com.example.chatIslem.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.chatIslem.chat.services.ConversationService;
import com.example.chatIslem.models.chat.Conversation;
import com.example.chatIslem.models.user.UserModel;



@RestController
@RequestMapping("/api/conversations")
public class ConversationController {

	@Autowired
	
	private final ConversationService conversationService;

	public ConversationController(ConversationService conversationService) {
		super();
		this.conversationService = conversationService;
	} 
	
	@PostMapping("/createConversation")
    public ResponseEntity<Conversation> createConversation(@RequestBody Conversation conversation) {
		Conversation createdconversation = conversationService.createConversation(conversation);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdconversation);
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
}
