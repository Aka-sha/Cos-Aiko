package com.auth.Authenticate.service;

/**
 * This class serves as a bridge between the FriendController and the FriendRepository
 */

import com.auth.Authenticate.dao.FriendRepository;
import com.auth.Authenticate.data.UserDto;
import com.auth.Authenticate.entity.Friendship;
import com.auth.Authenticate.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("friendService")
public class FriendService {
    @Autowired
    // FriendRepository
    private FriendRepository fRepo;
    @Autowired
    // UserService
    private UserService uService;

    /**
     *
     * @param userEmail
     * @param friendEmail
     */
    public void saveFriend(String userEmail, String friendEmail){
        UserEntity currUser = uService.getByEmail(userEmail);
        UserEntity newFriend = uService.getByEmail(friendEmail);

        Friendship friendship = new Friendship();
        UserEntity firstUser = currUser;
        UserEntity secondUser = newFriend;

        if(currUser.getId() > newFriend.getId()){
                firstUser = newFriend;
                secondUser = currUser;
        }

        if(!(fRepo.existsByRequesterIdAndReceiverId(firstUser, secondUser))){
            friendship.setRequesterId(firstUser);
            friendship.setReceiverId(secondUser);
            fRepo.save(friendship);
        }

    }

    /**
     *
     * @param id
     * @return
     */
    public List<UserDto> getFriends(Integer id){
        UserEntity currUser = uService.get(id);

        List<Friendship> friendByRequester = fRepo.findByRequesterId(currUser);
        List<Friendship> friendByReceiver = fRepo.findByReceiverId(currUser);
        List<UserDto> userFriends = new ArrayList<>();

        for(Friendship friend: friendByRequester){
            UserEntity user = uService.get(friend.getReceiverId().getId());
            UserDto userDto = new UserDto();

            userDto.setfName(user.getfName());
            userDto.setlName(user.getlName());
            userDto.setEmail(user.getEmail());
            userDto.setPassword(user.getPassword());

            userFriends.add(userDto);
        }
        for(Friendship friend: friendByReceiver){
            UserEntity user = uService.get(friend.getRequesterId().getId());
            UserDto userDto = new UserDto();

            userDto.setfName(user.getfName());
            userDto.setlName(user.getlName());
            userDto.setEmail(user.getEmail());
            userDto.setPassword(user.getPassword());

            userFriends.add(userDto);
        }

        return userFriends;
    }

}
