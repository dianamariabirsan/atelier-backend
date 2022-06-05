package com.ubb.licenta.dto;

import com.ubb.licenta.model.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto extends BaseDto {
    private Long id;
    private String name;
    private String phoneNumber;
    private String email;
    private String password;
    private Role role;
}
