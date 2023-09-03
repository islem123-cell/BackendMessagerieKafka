package com.example.chatIslem.Implement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.chatIslem.chat.services.MessageService;
import com.example.chatIslem.models.chat.Messages;
import com.example.chatIslem.repositoies.MessageRepository;




@Service
public class MessageServiceImpl implements MessageService {
	@Autowired
	private  MessageRepository messageRepository;

  
    
	@Override
	public Messages createMessage(Messages message) {
		return messageRepository.save(message);
	}


	@Override
	public List<Messages> getAllMessages() {
		// TODO Auto-generated method stub
		return messageRepository.findAll();
	}



	@Override
	public Messages getMessageById(String id) {
		// TODO Auto-generated method stub
		return messageRepository.findById(id).orElse(null);
	}
/*
	@Override
	public List<Message> getMessagesBySenderId(int senderId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Message> getMessagesByRecipientId(int recipientId) {
		// TODO Auto-generated method stub
		return null;
	}
*/
	@Override
	public List<Messages> consulterMessage(int Userid) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<Messages> getMessagesBySenderId(int senderId) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<Messages> getMessagesByRecipientId(int recipientId) {
		// TODO Auto-generated method stub
		return null;
	}




}
