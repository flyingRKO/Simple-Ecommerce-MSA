package com.simple.memberservice.service;

import com.simple.memberservice.dto.UserDto;
import com.simple.memberservice.entity.UserEntity;
import com.simple.memberservice.exception.ErrorCode;
import com.simple.memberservice.exception.SimpleEcommerceException;
import com.simple.memberservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public UserDto registerUser(String loginId, String userName) {
        return UserDto.from(userRepository.save(UserEntity.of(loginId, userName)));
    }

    @Transactional
    public UserDto modifyUser(Long userId, String userName){
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new SimpleEcommerceException(ErrorCode.NOT_FOUND_USER));
        user.setUserName(userName);
        return UserDto.from(user);
    }

    @Transactional
    public UserDto getUser(String loginId) {
       UserEntity user = userRepository.findByLoginId(loginId)
               .orElseThrow(() -> new SimpleEcommerceException(ErrorCode.NOT_FOUND_USER));
       return UserDto.from(user);
    }
}
