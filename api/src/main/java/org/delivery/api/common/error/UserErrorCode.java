package org.delivery.api.common.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
*  User의 경우 1000번때 에러코드 사용
**/
@AllArgsConstructor
@Getter
public enum UserErrorCode implements ErrorCodeIfs {
    USER_ERROR_CODE(400, 1404,"사용자를 찾을수 없습니다" );

    private final Integer httpStatusCode;
    private final Integer errorCode;
    private final String errorDescription;
}
