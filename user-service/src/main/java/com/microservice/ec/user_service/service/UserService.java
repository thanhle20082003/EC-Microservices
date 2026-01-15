package com.microservice.ec.user_service.service;

import com.microservice.ec.user_service.dto.PageResponse;
import com.microservice.ec.user_service.dto.request.UserRequest;
import com.microservice.ec.user_service.dto.response.UserResponse;
import com.microservice.ec.user_service.entity.User;
import com.microservice.ec.user_service.exception.ArchitectureException;
import com.microservice.ec.user_service.exception.ErrorCode;
import com.microservice.ec.user_service.mapper.UserMapper;
import com.microservice.ec.user_service.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {

    UserRepository userRepository;
    UserMapper userMapper;

    public PageResponse<UserResponse> getAllUsers(int page, int size) {

        Pageable pageable = PageRequest.of(page - 1, size);

        Page<User> users = userRepository.findAll(pageable);

        var data = users.getContent()
                .stream()
                .map(userMapper::toUserResponse)
                .toList();

        return PageResponse.<UserResponse>builder()
                .currentPages(page)
                .pageSizes(size)
                .totalPages(users.getTotalPages())
                .totalElements(users.getTotalElements())
                .data(data)
                .build();
    }

    public UserResponse getUserById(UUID id) {
        User user = userRepository.findById(id).orElseThrow(() -> new ArchitectureException(ErrorCode.ENTITY_NOT_FOUND, "User"));
        return userMapper.toUserResponse(user);
    }

    public UserResponse createUser(UserRequest request) {
        User user = userMapper.toUser(request);
        return userMapper.toUserResponse(userRepository.save(user));
    }

    public UserResponse updateUser(UserRequest request, UUID id) {
        User user = userRepository.findById(id).orElseThrow(() -> new ArchitectureException(ErrorCode.ENTITY_NOT_FOUND, "User"));
        userMapper.updateUser(user, request);
        return userMapper.toUserResponse(userRepository.save(user));
    }

    public void deleteById(UUID id) {
        if (!userRepository.existsById(id))
            throw new ArchitectureException(ErrorCode.ENTITY_NOT_FOUND, "User");
        userRepository.deleteById(id);
    }
}
