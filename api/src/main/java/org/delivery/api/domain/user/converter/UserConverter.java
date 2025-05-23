package org.delivery.api.domain.user.converter;

import io.swagger.v3.oas.annotations.Hidden;
import lombok.RequiredArgsConstructor;
import org.delivery.api.common.annotation.Converter;
import org.delivery.api.common.error.ErrorCode;
import org.delivery.api.common.exception.ApiException;
import org.delivery.api.domain.user.controller.model.UserRegisterRequest;
import org.delivery.api.domain.user.controller.model.UserResponse;
import org.delivery.db.user.UserEntity;

import java.util.Optional;

@RequiredArgsConstructor
@Hidden
@Converter
public class UserConverter {

    public UserEntity toEntity(UserRegisterRequest request) {

        return Optional.ofNullable(request).map(it->{
            //to entity
            return UserEntity.builder()
                    .name(request.getName())
                    .email(request.getEmail())
                    .password(request.getPassword())
                    .address(request.getAddress())
                    .build();
        }).orElseThrow(
                ()-> new ApiException(ErrorCode.NULL_POINT,"UserRegisterRequest Null")
        );
    }

    public UserResponse toResponse(UserEntity userEntity) {

        return Optional.ofNullable(userEntity)
                .map(it->{
                    return UserResponse.builder()
                            .id(userEntity.getId())
                            .name(userEntity.getName())
                            .address(userEntity.getAddress())
                            .email(userEntity.getEmail())
                            .status(userEntity.getStatus())
                            .registeredAt(userEntity.getRegisteredAt())
                            .unregisteredAt(userEntity.getUnregisteredAt())
                            .lastLoginAt(userEntity.getLastLoginAt())
                            .build();
                }).orElseThrow(
                        ()-> new ApiException(ErrorCode.NULL_POINT,"UserEntity Null")
                );
    }
}
