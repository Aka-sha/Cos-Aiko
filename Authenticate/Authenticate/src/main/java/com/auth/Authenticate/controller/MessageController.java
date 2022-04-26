package com.auth.Authenticate.controller;

import com.auth.Authenticate.data.MessageDto;
import com.auth.Authenticate.data.UserProfileDto;
import com.auth.Authenticate.entity.EventEntity;
import com.auth.Authenticate.entity.MessageEntity;
import com.auth.Authenticate.entity.UserEntity;
import com.auth.Authenticate.service.EventService;
import com.auth.Authenticate.service.MessageService;
import com.auth.Authenticate.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping
public class MessageController {
    @Autowired
    private MessageService service;

    @Autowired
    private UserService userService;

    /**
     * This function returns a list of all existing messages
     *
     * @return list of all events
     */
    @GetMapping("/messages")
    public ResponseEntity<List<MessageDto>> list() {
        List<MessageEntity> messageEntities = service.findAll();
        List<MessageDto> messageDtos = new ArrayList<>();
        for (MessageEntity ent : messageEntities){
            MessageDto dto = new MessageDto(ent.getMid(), ent.getSenderId().getId(), ent.getReceiverId().getId(),ent.getTime(), ent.getMessage());
            messageDtos.add(dto);
        }
        ResponseEntity<List<MessageDto>> responseEntity = new ResponseEntity<List<MessageDto>>(messageDtos, HttpStatus.OK);
        return responseEntity;
    }
    /**
     * This function returns a list of all existing messages
     *
     * @return list of all events
     */
    @GetMapping("/messages/betweentwo/{sender}/{receiver}")
    public ResponseEntity<List<MessageDto>> betweenTwo(@PathVariable String sender, @PathVariable String receiver) {
        UserEntity senderEnt = userService.getByEmail(sender);
        UserEntity receiverEnt = userService.getByEmail(receiver);
        List<MessageDto> returnList = mapToListDta(service.findByReceiverAndSender(senderEnt, receiverEnt));
        returnList.addAll(mapToListDta(service.findByReceiverAndSender(receiverEnt, senderEnt)));

        ResponseEntity<List<MessageDto>> responseEntity = new ResponseEntity<List<MessageDto>>(returnList, HttpStatus.OK);
        return responseEntity;
    }

    public List<MessageDto> mapToListDta(List<MessageEntity> messagList){
        List<MessageDto> messageDtos = new ArrayList<>();
        for (MessageEntity ent : messagList){
            MessageDto dto = new MessageDto(ent.getMid(), ent.getSenderId().getId(), ent.getReceiverId().getId(),ent.getTime(), ent.getMessage());
            messageDtos.add(dto);
        }
        return messageDtos;
    }

}
