package com.example.chatIslem.models.chat;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
@Document(collection="conversation")
public class Conversation {

	@Id
	private String id;
	
	private String chatName;
/*	@DBRef
	private UserModel recipient;
	
	@DBRef
	private UserModel sender;
	*/
	
	@DBRef
	private List<UserModel> participants=new ArrayList<>();

	public List<Messages> message=new ArrayList<>();
		
//	@DBRef
	//private List<Messages> messages ;
  /*  private String chatId;

    public String getChatId() {
        return chatId; }*/





	

	
}
