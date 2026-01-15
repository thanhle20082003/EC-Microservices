package com.microservice.ec.user_service.controller;

import com.microservice.ec.user_service.dto.ApiResponse;
import com.microservice.ec.user_service.dto.PageResponse;
import com.microservice.ec.user_service.dto.request.UserRequest;
import com.microservice.ec.user_service.dto.response.UserResponse;
import com.microservice.ec.user_service.service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/api/users")
public class UserController {

    UserService userService;

    @GetMapping
    public ApiResponse<PageResponse<UserResponse>> getAllUsers(@RequestParam(defaultValue = "1") @Min(1) int page,
                                                               @RequestParam(defaultValue = "10") @Min(10) @Max(100) int size
    ) {
        return ApiResponse.<PageResponse<UserResponse>>builder()
                .code(HttpStatus.OK.value())
                .message("Get all user successfully")
                .result(userService.getAllUsers(page, size))
                .build();
    }

    @GetMapping("/{id}")
    public ApiResponse<UserResponse> getUserById(@PathVariable UUID id) {
        return ApiResponse.<UserResponse>builder()
                .code(HttpStatus.OK.value())
                .message("Get user successfully")
                .result(userService.getUserById(id))
                .build();
    }

    @PostMapping("/create")
    public ApiResponse<UserResponse> createUser(@Valid @RequestBody UserRequest request) {
        return ApiResponse.<UserResponse>builder()
                .code(HttpStatus.CREATED.value())
                .message("Create user successfully")
                .result(userService.createUser(request))
                .build();
    }

    @PutMapping("/update")
    public ApiResponse<UserResponse> updateUser(@Valid @RequestBody UserRequest request,
                                                @RequestParam UUID id
    ) {
        return ApiResponse.<UserResponse>builder()
                .code(HttpStatus.ACCEPTED.value())
                .message("Update user successfully")
                .result(userService.updateUser(request, id))
                .build();
    }

    @DeleteMapping("/delete/{id}")
    public ApiResponse<Void> deleteUser(@PathVariable UUID id) {
        userService.deleteById(id);
        return ApiResponse.<Void>builder()
                .code(HttpStatus.NO_CONTENT.value())
                .message("Delete user successfully")
                .build();
    }
}
