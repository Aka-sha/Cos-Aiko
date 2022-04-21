package com.auth.Authenticate.controller;

import com.auth.Authenticate.Message;
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
    SimpMessagingTemplate simpMessagTemp;

    @MessageMapping("/broadcast")
    @SendTo("/b")
    public String broadcast(Message message) {
        return "Success hitting broadcast";
    }

    @MessageMapping("/chat.send")
    @SendTo("/chatbroker/public")
    public Message sendMessage(@Payload Message message){
        return message;
    }

    @MessageMapping("/chat")
    @SendTo("/topic/messages")
    public String send(Message message) throws Exception {
        return "working";
    }

    @MessageMapping("/chat/{to}")
    public void sendMessage(@DestinationVariable String to, Message message){
        System.out.println("handling send message " + message.getMessage() + " to :" + to);
        simpMessagTemp.convertAndSend("/topic/messages" + to, message);

    }
}
