package org.delivery.api.acount;

import lombok.RequiredArgsConstructor;
import org.delivery.api.acount.model.AccountMeResponse;
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
    public AccountMeResponse save(){

        return AccountMeResponse.builder()
                .name("홍길동")
                .email("me@mail.com")
                .registrationAt(LocalDateTime.now())
                .build();
    }
}
