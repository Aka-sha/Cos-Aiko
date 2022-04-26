package com.auth.Authenticate.dao;

import com.auth.Authenticate.Message;
import com.auth.Authenticate.entity.MessageEntity;
import com.auth.Authenticate.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<MessageEntity, Integer> {
    MessageEntity findByMid(Integer mid);
    List<MessageEntity> findAll();

    List<MessageEntity> findMessageEntityBySenderIdAndReceiverId(UserEntity senderId, UserEntity receiverId);
}
