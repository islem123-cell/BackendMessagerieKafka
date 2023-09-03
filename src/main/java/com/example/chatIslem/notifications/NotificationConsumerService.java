package com.example.chatIslem.notifications;
/*
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.example.chatIslem.kafka.NotificationKafka;



@Service
public class NotificationConsumerService {

    @KafkaListener(topics = "Notifications", groupId = "myGroup")
    public void consume(NotificationKafka notification) {
        // Traitez la notification en temps réel
        System.out.println("Notification reçue : " + notification.getMessage());
        // Vous pouvez ajouter ici des logiques pour envoyer la notification aux utilisateurs en temps réel
        // Par exemple, via WebSocket, enregistrement en base de données, etc.
    }
}


/*@Service
public class NotificationConsumerService {

    @Autowired
    private NotificationProducerService notificationProducerService;

    @KafkaListener(topics = "notification-topic", groupId = "notification-consumer-group")
    public void consumeNotification(NotificationKafka notification) {
        // Traitez la notification (par exemple, envoyez un e-mail, créez une notification dans l'application, etc.)
        sendNotificationToRecipient(notification);
    }

    private void sendNotificationToRecipient(NotificationKafka notification) {
        // Vous pouvez appeler le service de producteur de notification ici pour envoyer la notification au destinataire
        notificationProducerService.sendNotification(notification);
    }
}*/
