package com.auth.Authenticate.service;

import com.auth.Authenticate.dao.MessageRepository;
import com.auth.Authenticate.dao.UserRepository;
import com.auth.Authenticate.entity.MessageEntity;
import com.auth.Authenticate.entity.UserEntity;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("messageService")
public class MessageService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private MessageRepository messageRepo;

    public MessageEntity findByMid(Integer mid){
        return messageRepo.findByMid(mid);
    }

    public List<MessageEntity> findAll(){
        return messageRepo.findAll();
    }
    public void save(MessageEntity messageEntity){
        messageRepo.save(messageEntity);
    }
    public List<MessageEntity> findByReceiverAndSender(UserEntity sender, UserEntity receiver){
        return messageRepo.findMessageEntityBySenderIdAndReceiverId(sender, receiver);
    }
}
