package com.example.chatIslem.kafka;

import org.springframework.stereotype.Service;

@Service
public class MessageKafkaService {
/*	
	private final KafkaMessageProducer kafkaMessageProducer1;
 private final MessageRepository messageRepository1;
    
public MessageKafkaService(KafkaMessageProducer kafkaMessageProducer, MessageRepository messageRepository) {
		super();
		this.kafkaMessageProducer1 = kafkaMessageProducer;
		this.messageRepository1 = messageRepository;
	} 
	
public void sendMessage(User sender, User recipient, String messageContent) {
        // Envoi du message au topic "Messages"
        kafkaMessageProducer1.sendMessage("Messages");

        // Enregistrement du message dans la base de données
        Messages message = new Messages();
        message.setSender(sender);
        message.setRecipient(recipient);
        message.setMessageContent(messageContent);
        messageRepository1.save(message);
    }

	
/*	private final KafkaMessageProducer kafkaMessageProducer;
	 private final UserRepository userRepository;
	 private final MessageRepository messageRepository;

	 public MessageKafkaService(KafkaMessageProducer kafkaMessageProducer, UserRepository userRepository,MessageRepository messageRepository) {
	        this.kafkaMessageProducer1 = kafkaMessageProducer;
	        this.userRepository = userRepository;
	        this.messageRepository1 =messageRepository;
	    }


    public void sendMessageToUser(String senderId, String recipientId, String messageContent) {        
    	 User sender = userRepository.findById(senderId).orElse(null);
         User recipient = userRepository.findById(recipientId).orElse(null);
         
         // Vérifier si les utilisateurs existent avant d'envoyer le message
         if (sender == null || recipient == null) {
             throw new IllegalArgumentException("L'expéditeur ou le destinataire n'existe pas.");
         }
         
         // Créer un message
         Message message = new Message();
         message.setSender(sender);
         message.setRecipient(recipient);
         message.setContenuMessage(messageContent);
         
      // Envoyer le message sur le sujet "messages_envoyes"
         kafkaMessageProducer1.sendMessage("MessagesEnvoyes", message.toString());
         
      // Enregistrer le message dans la base de données
         messageRepository1.save(message);
         
    } */
    
 /*   public void saveReceivedMessage(Messages messageContent) {
        // Logique pour extraire l'expéditeur, le destinataire et le contenu du message reçu
        // (suppose que le message est déjà formaté correctement)

        // Vérifier si l'expéditeur et le destinataire existent avant d'enregistrer le message
        if (messageContent.getSender() == null || messageContent.getRecipient() == null) {
            throw new IllegalArgumentException("L'expéditeur ou le destinataire du message reçu n'existe pas.");
        }

        // Enregistrer le message reçu dans la base de données
        messageRepository1.save(messageContent);
    }
    
*/
} 
