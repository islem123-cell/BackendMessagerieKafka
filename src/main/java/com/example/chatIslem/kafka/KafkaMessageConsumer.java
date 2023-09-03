package com.example.chatIslem.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;

import com.example.chatIslem.chat.services.MessageService;
import com.example.chatIslem.models.chat.Messages;

public class KafkaMessageConsumer {
	
	
	  private static final Logger LOGGER = LoggerFactory.getLogger(KafkaMessageConsumer.class);

	    @KafkaListener(topics="Messages", groupId="myGroup")
		public void consume(Messages msgContent) {
			LOGGER.info(String.format("Json message received -> %s", msgContent.toString()));
	        MessageService.saveMessage(msgContent);

		}
	

}

