package com.example.chatIslem.Implement;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.chatIslem.chat.services.ConversationService;
import com.example.chatIslem.models.chat.Conversation;
import com.example.chatIslem.models.user.UserModel;
import com.example.chatIslem.repositoies.ConversationRepository;



@Service
public class ConversationServiceImpl implements ConversationService {
	 @Autowired
	private  ConversationRepository conversationRepository;

   
    

	@Override
	public Conversation createConversation(Conversation conversation) {  
		return conversationRepository.save(conversation) ;
	}

	@Override
	public boolean deleteConversation(String id) {
		conversationRepository.deleteById(id);
		return !conversationRepository.existsById(id);

	}

	@Override
	public void addParticipantToConversation(String conversationId, UserModel participantId) {
	    // Retrieve the conversation object using the conversationId
	    Optional<Conversation> optionalConversation = conversationRepository.findById(conversationId);

	    if (optionalConversation.isPresent()) {
	        Conversation conversation = optionalConversation.get();

	        // Check if the participant is already in the conversation
	        if (!conversation.getParticipants().contains(participantId)) {
	            conversation.getParticipants().add(participantId);

	            // Save the modified conversation back to the repository
	            conversationRepository.save(conversation);
	            System.out.println("Participant with ID " + participantId + " added to the conversation.");
	        } else {
	            System.out.println("Participant with ID " + participantId + " is already in the conversation.");
	        }
	    } else {
	        System.out.println("Conversation with ID " + conversationId + " not found.");
	    }
	
	}


	@Override
	public List<Conversation> getAllConversations() {
		// TODO Auto-generated method stub
		return null;
	}
}

