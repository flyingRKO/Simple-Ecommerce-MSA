package com.simple.memberservice.controller;

import com.simple.memberservice.dto.ModifyUserRequest;
import com.simple.memberservice.dto.RegisterUserRequest;
import com.simple.memberservice.dto.Response;
import com.simple.memberservice.dto.UserDto;
import com.simple.memberservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MemberController {
    private final UserService userService;

    @PostMapping("/member/users/register")
    public Response<UserDto> registerUser(@RequestBody RegisterUserRequest request) {
        return Response.success(userService.registerUser(request.loginId(), request.userName()));
    }

    @PutMapping("/member/users/{userId}/modify")
    public Response<UserDto> modifyUser(@PathVariable Long userId, @RequestBody ModifyUserRequest request){
        return Response.success(userService.modifyUser(userId, request.userName()));
    }

    @PostMapping("/member/users/{loginId}/login")
    public Response<UserDto> login(@PathVariable String loginId) {
        return Response.success(userService.getUser(loginId));
    }



}
