package org.delivery.api.domain.user.business;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.delivery.api.common.annotation.Business;
import org.delivery.api.common.error.ErrorCode;
import org.delivery.api.common.exception.ApiException;
import org.delivery.api.domain.user.controller.model.UserRegisterRequest;
import org.delivery.api.domain.user.controller.model.UserResponse;
import org.delivery.api.domain.user.converter.UserConverter;
import org.delivery.api.domain.user.service.UserService;

import java.util.Optional;


@RequiredArgsConstructor
@Business
public class UserBusiness {

    private final UserService userService;
    private final UserConverter userConverter;

    /*
    * 사용자에 대한 가입처리 로직
    * 1. request -> entity
    * 2. entity -> save
    * 3. save Entity -< response
    * 4. response return
    */
    public UserResponse register(@Valid UserRegisterRequest request) {

        /* java 8 버전 이상부터는 함수형으로 사용 가능
        장점 : 코드에 가독성이 좋다
        var entity = userConverter.toEntity(request);
        var newEntity = userService.register(entity);
        var response = userConverter.toResponse(newEntity);
        return response;*/

        return Optional.ofNullable(request)
                .map(userConverter :: toEntity)
                .map(userService::register)
                .map(userConverter::toResponse)
                .orElseThrow(()->new ApiException(ErrorCode.NULL_POINT, "request null"));

    }
}
