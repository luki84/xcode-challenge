package com.luki.xcodechallenge.service;

import com.luki.xcodechallenge.client.NbpClient;
import com.luki.xcodechallenge.dao.Currency;
import com.luki.xcodechallenge.dao.CurrencyResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ExchangeService {
    private final NbpClient nbpClient;

    @Autowired
    public ExchangeService(NbpClient nbpClient) {
        this.nbpClient = nbpClient;
    }

    public CurrencyResponseDto getMid(Currency.Code code) {
        return nbpClient.getMid(code).getRates().stream()
                .findFirst()
                .map(rate -> new CurrencyResponseDto(rate.getMid().toPlainString()))
                .orElse(new CurrencyResponseDto("123332")); //TODO: throw an exception here
    }

    private CurrencyResponseDto buildCurrencyResponseDTO(BigDecimal mid) {
        return new CurrencyResponseDto(mid.toPlainString());
    }

}
