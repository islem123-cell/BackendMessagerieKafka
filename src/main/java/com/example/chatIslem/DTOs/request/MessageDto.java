package com.example.chatIslem.DTOs.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class MessageDto {
	
	
	
	private String Content;
	private String recipient_id ;
}
