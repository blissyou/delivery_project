package org.delivery.api.exceptionhandler;

import io.swagger.v3.oas.annotations.Hidden;
import lombok.extern.slf4j.Slf4j;
import org.delivery.api.common.api.Api;
import org.delivery.api.common.error.ErrorCode;
import org.delivery.api.domain.user.controller.UserApiController;
import org.delivery.api.domain.user.controller.UserOpenApiController;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@Hidden //swagger ui 에서 지원하는 어노테이션 충돌 에러 떄문에 swagger-ui  숨기는 것을 명시
@RestControllerAdvice(annotations = {RestController.class} , basePackageClasses = {UserApiController.class, UserOpenApiController.class})
@Order(value = Integer.MAX_VALUE) //가장 마지막에 실행 적용
public class GlobalExceptionHandler {

    @ExceptionHandler(value=Exception.class)
    public ResponseEntity<Api<Object>> exception(
            Exception exception
    ){
        log.error("",exception);

        return ResponseEntity
                .status(500)
                .body(
                        Api.ERROR(ErrorCode.SERVER_ERROR)
                );
    }
}
