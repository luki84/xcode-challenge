package com.luki.xcodechallenge.controller;

import com.luki.xcodechallenge.dto.CurrencyQuery;
import com.luki.xcodechallenge.dto.CurrencyResponseDto;
import com.luki.xcodechallenge.service.ExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/currencies")
public class CurrenciesController {

    @Autowired
    ExchangeService exchangeService;

    @PostMapping("/get-current-currency-value-command")
    public ResponseEntity<CurrencyResponseDto> getMid(@RequestBody CurrencyQuery currencyQuery) {
        CurrencyResponseDto value = exchangeService.getMid (currencyQuery.getCurrency());
        return new ResponseEntity<>(value, HttpStatus.OK);
    }
}
