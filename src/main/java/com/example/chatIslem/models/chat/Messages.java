package com.example.chatIslem.models.chat;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.example.chatIslem.models.user.UserModel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection="message")
public class Messages {

	@Id
	private String id;
	
	private String messageContent;
	
	private Date DateEnvoie;
	
	
	@DBRef
	
	 private UserModel sender;
	
	@DBRef
    private UserModel recipient;
}