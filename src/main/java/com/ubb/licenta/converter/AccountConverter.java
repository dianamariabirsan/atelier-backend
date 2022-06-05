package com.ubb.licenta.converter;

import com.ubb.licenta.dto.AccountDto;
import com.ubb.licenta.model.User;
import org.springframework.stereotype.Component;

@Component
public class AccountConverter extends BaseConverter<User, AccountDto> {
    @Override
    public User convertDtoToModel(AccountDto dto) {
        User account = new User();
        account.setEmail(dto.getEmail());
        account.setName(dto.getName());
        account.setPhoneNumber(dto.getPhoneNumber());
        account.setPassword(dto.getPassword());
        return account;
    }

    @Override
    public AccountDto convertModelToDto(User user) {
        AccountDto accountDto = new AccountDto();
        accountDto.setEmail(user.getEmail());
        accountDto.setName(user.getName());
        accountDto.setPhoneNumber(user.getPhoneNumber());
        accountDto.setPassword(user.getPassword());
        return accountDto;
    }
}
