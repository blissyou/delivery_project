package org.delivery.api.acount.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class AccountMeResponse {

    private String email;
    private String name;
    private LocalDateTime registrationAt;
}
