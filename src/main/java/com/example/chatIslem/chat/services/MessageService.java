package com.example.chatIslem.chat.services;

import java.util.List;

import com.example.chatIslem.models.chat.Messages;


public interface MessageService {

	Messages createMessage(Messages message);

    Messages getMessageById(String id);

    List<Messages> getAllMessages();

  //  List<Message> getMessagesBySenderId(int senderId);

  //  List<Message> getMessagesByRecipientId(int recipientId);
    
    List<Messages> consulterMessage(int Userid);
    
}