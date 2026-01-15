package com.microservice.ec.user_service.mapper;

import com.microservice.ec.user_service.dto.request.UserRequest;
import com.microservice.ec.user_service.dto.response.UserResponse;
import com.microservice.ec.user_service.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "updatedAt", expression = "java(java.time.LocalDateTime.now())")
    User toUser(UserRequest request);

    UserResponse toUserResponse(User user);

    @Mapping(target = "updatedAt", expression = "java(java.time.LocalDateTime.now())")
    void updateUser(@MappingTarget User user, UserRequest request);
}
