package com.ubb.licenta.controller;

import com.ubb.licenta.converter.BaseConverter;
import com.ubb.licenta.dto.BasketDto;
import com.ubb.licenta.model.Basket;
import com.ubb.licenta.service.BasketService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
@CrossOrigin
@RequestMapping("/basket")
@Slf4j
public class BasketController {

    @Autowired
    private BasketService service;

    @Autowired
    private BaseConverter<Basket, BasketDto> converter;

    @GetMapping
    public ResponseEntity<BasketDto> getBasketByClientId(@RequestHeader("session-id") String sessionId) {
        Long clientId = getClientIdFromSession(sessionId);
        Basket clientBasket = service.getByClientId(clientId);
        BasketDto basketDto = converter.convertModelToDto(clientBasket);
        basketDto.setProducts(Collections.emptyList());

        log.info("Get basket for clientId" + clientId + ": " + basketDto);
        return new ResponseEntity<>(basketDto, HttpStatus.OK);
    }

    private Long getClientIdFromSession(String sessionId) {
        return 1L;
    }

    @PostMapping
    public ResponseEntity<BasketDto> save(@RequestBody BasketDto basketDto) {
        Basket basket = converter.convertDtoToModel(basketDto);
        Basket saved = service.save(basket);
        if (saved == null) {
            log.info("Unable to save basket");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        log.info("Saved" + saved);
        return new ResponseEntity<>(basketDto, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<BasketDto> update(@RequestBody BasketDto basketDto) {
        Basket basket = converter.convertDtoToModel(basketDto);
        Basket saved = service.update(basket);
        if (saved == null) {
            log.info("Unable to update basket");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        log.info("Saved" + saved);
        return new ResponseEntity<>(basketDto, HttpStatus.OK);
    }

    @DeleteMapping("/{basketId}")
    public ResponseEntity<?> deleteBasket(@PathVariable("basketId") Long basketId) {
        service.delete(basketId);
        log.info("Returning basket with id: " + basketId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
