package com.example.chatIslem.models.chat;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.example.chatIslem.models.user.UserModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection="conversation")


public class Conversation {

	@Id
	private String id;
	
	@DBRef
	private List<UserModel> participants;
	
	@DBRef
	private List<Messages> messages;
}
