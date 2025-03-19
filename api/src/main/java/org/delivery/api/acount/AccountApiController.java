package org.delivery.api.acount;

import lombok.RequiredArgsConstructor;
import org.delivery.api.acount.model.AccountMeResponse;
import org.delivery.api.common.api.Api;
import org.delivery.api.common.error.UserErrorCode;
import org.delivery.db.account.AccountEntity;
import org.delivery.db.account.AccountRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/account")
public class AccountApiController {

    private final AccountRepository accountRepository;

    @GetMapping("/me")
    public Api<AccountMeResponse> save(){
        var response = AccountMeResponse.builder()
                .name("홍길동")
                .email("me@mail.com")
                .registrationAt(LocalDateTime.now())
                .build();
        return Api.ok(response);
    }

    //test code
    @GetMapping("/me/error")
    public Api<Object> error(){
        var response = AccountMeResponse.builder()
                .name("홍길동")
                .email("me@mail.com")
                .registrationAt(LocalDateTime.now())
                .build();
        return Api.ERROR(UserErrorCode.USER_ERROR_CODE,"홍길동 이라는 사람은 없습니다");
    }
}
