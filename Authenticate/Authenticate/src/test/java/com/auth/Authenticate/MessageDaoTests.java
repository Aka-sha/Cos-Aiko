package com.auth.Authenticate;

import com.auth.Authenticate.dao.MessageRepository;
import com.auth.Authenticate.dao.UserRepository;
import com.auth.Authenticate.entity.MessageEntity;
import com.auth.Authenticate.entity.UserEntity;
import com.auth.Authenticate.service.UserService;
import org.apache.catalina.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class MessageDaoTests {

    @Autowired
    MessageRepository messageRepo;

    @Autowired
    private UserService uService;

    @Autowired
    private UserRepository uRepo;

    @Test
    void testMessageEntity() {
        UserEntity pgriff = uService.getByEmail("pgriff@123.com");
        UserEntity freddy = uService.getByEmail("freddy@gmail.com");
        long ut2 = System.currentTimeMillis() / 1000L;
        MessageEntity messageEnt = new MessageEntity(pgriff, freddy, "Message created by testMessageEntity", (int)ut2 );
        messageRepo.save(messageEnt);
    }

    @Test
    void testGetMessages(){
        List<MessageEntity> ent = messageRepo.findAll();

        System.out.println(ent.get(0).getMessage());
    }

    @Test
    void testFindByReceiverAndSender(){
        UserEntity sender = uRepo.findById(7).get();
        UserEntity receiver = uRepo.findById(3).get();
        List<MessageEntity> message = messageRepo.findMessageEntityBySenderIdAndReceiverId(sender, receiver);

        System.out.println(message.get(1).getMessage());
    }
}
