package com.example.chatIslem.chat.services;

import java.util.List;
import java.util.Optional;

import com.example.chatIslem.models.chat.Messages;


public interface MessageService {

	Messages createMessage(Messages message);

    Messages getMessageById(String id);

    List<Messages> getAllMessages();

  List<Messages> getMessagesBySenderId(int senderId);

   List<Messages> getMessagesByRecipientId(int recipientId);
 
    List<Messages> consulterMessage(int Userid);

	static void saveMessage(Messages msgContent) {
        MessageService messageService;
		MessageService.saveMessage(msgContent); // Enregistrement dans MongoDB
		
	}
	// public static    List<Messages> findChatMessages(String senderId, String recipientId) {
    //    Optional<String> chatId = ConversationService.getChatId(senderId, recipientId, false);

      //  List<Messages> messages =
              //  chatId.map(cId -> repository.findByChatId(cId)).orElse(new ArrayList<>());
      //  }

    

}