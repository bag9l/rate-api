package com.rate.api.controller;

import com.rate.api.dto.PasswordUpdate;
import com.rate.api.dto.UserProfile;
import com.rate.api.dto.UserUpdateData;
import com.rate.api.dto.auth.AuthenticationResponse;
import com.rate.api.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RequiredArgsConstructor
@RequestMapping("user")
@RestController
public class UserController {

    private final UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<UserProfile> getUserById(@PathVariable("id") String id) {
        return ResponseEntity.status(HttpStatus.OK).body(
                userService.getUserProfileById(id)
        );
    }

    @PutMapping(value = "/{id}",
            consumes = {"multipart/form-data"})
    public ResponseEntity<Void> setAvatar(@ModelAttribute UserUpdateData userUpdateData,
                                          @PathVariable("id") String userId,
                                          @AuthenticationPrincipal UserDetails user) throws IOException {
        userService.updateUser(userUpdateData, userId, user.getUsername());
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping(value = "/password")
    public ResponseEntity<AuthenticationResponse> changePassword(@RequestBody PasswordUpdate passwordUpdate,
                                                                 @AuthenticationPrincipal UserDetails user) {
        return ResponseEntity.status(HttpStatus.OK).body(
                userService.updatePassword(user, passwordUpdate));
    }
}
