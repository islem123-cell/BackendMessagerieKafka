package com.example.chatIslem.repositoies;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.chatIslem.models.chat.Conversation;


public interface ConversationRepository extends MongoRepository<Conversation, String> {

}
