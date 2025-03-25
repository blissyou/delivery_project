package org.delivery.api.acount;

import lombok.RequiredArgsConstructor;
import org.delivery.api.acount.model.AccountMeResponse;
import org.delivery.api.common.api.Api;
import org.delivery.api.common.error.ErrorCode;
import org.delivery.api.common.error.UserErrorCode;
import org.delivery.api.common.exception.ApiException;
import org.delivery.db.account.AccountEntity;
import org.delivery.db.account.AccountRepository;
import org.springframework.http.ResponseEntity;
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

        var str = "안녕하세요";
        var age= 0;
        try{
            Integer.parseInt(str);
        }catch(Exception e){
            throw new ApiException(ErrorCode.SERVER_ERROR, e , "사용자 호출 에러");
        }
        return Api.OK(response);

    }

    //test code
    @GetMapping("/me/error")
    public Api<Object> error(){
        var response = AccountMeResponse.builder()
                .name("홍길동")
                .email("me@mail.com")
                .registrationAt(LocalDateTime.now())
                .build();

        return Api.OK(response);
    }
}
