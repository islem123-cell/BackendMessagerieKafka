package com.example.chatIslem.chat.services;

import java.util.List;

import com.example.chatIslem.models.chat.Conversation;
import com.example.chatIslem.models.user.UserModel;


public interface ConversationService {
	
	Conversation createConversation(Conversation conversation);

    boolean deleteConversation(String id);

    void addParticipantToConversation(String conversationId, UserModel participantId);
    
    List<Conversation> getAllConversations();
    


}
