package com.example.chatIslem.controllers;
/*
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.chatIslem.kafka.NotificationKafka;
import com.example.chatIslem.notifications.NotificationProducerService;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

    @Autowired
    private NotificationProducerService notificationProducerService;

    @PostMapping("/sendnotif")
    public ResponseEntity<String> sendNotification(@RequestBody NotificationKafka notification) {
        try {
            notificationProducerService.sendNotification(notification);
            return ResponseEntity.ok("Notification sent successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to send notification");
        }
    }
}
*/