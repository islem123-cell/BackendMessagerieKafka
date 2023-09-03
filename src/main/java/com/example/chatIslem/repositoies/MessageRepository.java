package com.example.chatIslem.repositoies;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.chatIslem.models.chat.Messages;

@Repository
public interface MessageRepository extends MongoRepository<Messages,String> {

	
 //   long countBySenderAndRecipientAndStatus(
         //   String sender, String recipient);

   // List<Messages> findByChatId(String chatId);
}
