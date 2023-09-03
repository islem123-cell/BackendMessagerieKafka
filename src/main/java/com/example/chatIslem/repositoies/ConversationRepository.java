package com.example.chatIslem.repositoies;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.chatIslem.DTOs.request.ConversationRequest;
import com.example.chatIslem.models.chat.Conversation;
import com.example.chatIslem.models.user.UserModel;


public interface ConversationRepository extends MongoRepository<Conversation, String> {
	Conversation findByParticipantsIn(String conversationId) ; 
	Optional<Conversation> findById(String conversationId) ; 

	/* static Conversation findBySenderAndRecipient(UserModel sender,UserModel recipient) {
		// TODO Auto-generated method stub
		return null;
	}*/
	
 //   List<Conversation> findBySender(String sender);

	
			
}
