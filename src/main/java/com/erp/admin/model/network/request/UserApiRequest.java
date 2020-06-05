package com.erp.admin.model.network.request;

import com.erp.admin.model.status.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserApiRequest {

    private Long id;

    private String account;

    private String password;

    private String email;

    private UserStatus status;

    private String phoneNumber;

    private LocalDateTime registeredAt;

    private LocalDateTime unregisteredAt;

}
