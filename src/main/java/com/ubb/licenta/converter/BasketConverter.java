package com.ubb.licenta.converter;

import com.ubb.licenta.dto.BasketDto;
import com.ubb.licenta.model.Basket;
import com.ubb.licenta.model.User;
import com.ubb.licenta.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BasketConverter extends BaseConverter<Basket, BasketDto> {
    private static final Logger log = LoggerFactory.getLogger(BasketConverter.class);

    @Autowired
    private UserRepository userRepository;

    @Override
    public Basket convertDtoToModel(BasketDto dto) {
        User user = userRepository.findUserById(dto.getClientId());
        return new Basket(dto.getId(), dto.getProducts(), user);
    }

    @Override
    public BasketDto convertModelToDto(Basket basket) {
        BasketDto basketDto = new BasketDto(basket.getProducts(), basket.getClient().getId());
        basketDto.setId(basket.getId());
        return basketDto;
    }
}
