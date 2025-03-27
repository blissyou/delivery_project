package org.delivery.api.domain.user.controller;

//로그인을 해야 사용할수 있는 컨트롤러

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/user")
public class UserApiController {
}
