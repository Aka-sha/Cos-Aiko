package com.auth.Authenticate.controller;

/**
 * This is a controller class with endpoints for the FRIENDS table in our MySQL database.
 * Any CRUD operation performed to this table by the CosAiko application will be routed
 * via this MainController class
 */

import com.auth.Authenticate.data.UserProfileDto;
import com.auth.Authenticate.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class FriendController {
    @Autowired
    FriendService fService;

    /**
     *
     * @param userEmail
     * @param friendEmail
     * @return
     */
    @PostMapping("/friend/addFriend/{userEmail}/{friendEmail}")
    public ResponseEntity<?> addFriend(@PathVariable String userEmail, @PathVariable String friendEmail){
        fService.saveFriend(userEmail, friendEmail);
        return new ResponseEntity<String>("Friend added successfully", HttpStatus.OK);
    }

    /**
     *
     * @param email
     * @return
     */
    @GetMapping("/friend/listFriends/{email}")
    public ResponseEntity<List<UserProfileDto>> getFriends(@PathVariable String email){
        List<UserProfileDto> userFriends = fService.getFriends(email);
        return new ResponseEntity<List<UserProfileDto>>(userFriends, HttpStatus.OK);
    }

}
