package com.auth.Authenticate.controller;

import com.auth.Authenticate.Message;
import com.auth.Authenticate.entity.MessageEntity;
import com.auth.Authenticate.entity.UserEntity;
import com.auth.Authenticate.service.MessageService;
import com.auth.Authenticate.service.UserService;
import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

    @Autowired
    private UserService userService;

    @Autowired
    private MessageService messageService;

    @Autowired
    SimpMessagingTemplate simpMessagTemp;

    @MessageMapping("/broadcast")
    @SendTo("/b")
    public String broadcast(Message message) {
        return "Success hitting broadcast";
    }

    @MessageMapping("/chat.send")
    @SendTo("/topic/chatbroker")
    public String sendMessage(@Payload String message){
        String[] splitMessage = message.split(":");
        UserEntity sender = userService.getByEmail(splitMessage[0]);
        UserEntity receiver = userService.getByEmail(splitMessage[1]);
        long ut2 = System.currentTimeMillis() / 1000L;
        MessageEntity messageEnt = new MessageEntity(sender, receiver, splitMessage[2], (int)ut2);
        messageService.save(messageEnt);
        return message;
    }

    @MessageMapping("/chat")
    @SendTo("/topic/messages")
    public Message send(@Payload Message message) throws Exception {
        return message;
    }

    @MessageMapping("/chat/{to}")
    public void sendMessage(@DestinationVariable String to, Message message){
        System.out.println("handling send message " + message.getMessage() + " to :" + to);
        simpMessagTemp.convertAndSend("/topic/messages" + to, message);

    }
}
