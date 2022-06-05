package com.ubb.licenta.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AccountDto extends BaseDto implements Serializable {
    private String email;
    private String name;
    private String phoneNumber;
    private String password;
    private String newPassword;
    private String rewrittenPassword;
}
