package com.luki.xcodechallenge.controller;

import com.luki.xcodechallenge.dao.CurrencyDto;
import com.luki.xcodechallenge.dao.CurrencyResponseDto;
import com.luki.xcodechallenge.service.ExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/currencies")
public class CurrenciesController {

    @Autowired
    ExchangeService exchangeService;

    @PostMapping("/get-current-currency-value-command")
    public ResponseEntity<CurrencyResponseDto> getMid(@RequestBody CurrencyDto currencyDto) {
        CurrencyResponseDto value = exchangeService.getMid (currencyDto.getCurrency());
        return new ResponseEntity<>(value, HttpStatus.OK);
    }
}
