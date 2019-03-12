package com.luki.xcodechallenge.service;

import com.luki.xcodechallenge.client.NbpClient;
import com.luki.xcodechallenge.dto.Currency;
import com.luki.xcodechallenge.dto.CurrencyResponseDto;
import com.luki.xcodechallenge.dto.Rate;
import com.luki.xcodechallenge.exception.XCodeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ExchangeService {
    private final NbpClient nbpClient;

    @Autowired
    public ExchangeService(NbpClient nbpClient) {
        this.nbpClient = nbpClient;
    }

    public CurrencyResponseDto getMid(Currency.Code code) {
        Optional<Rate> rate = nbpClient.getMid(code).getRates().stream().findFirst();

        if (rate.isPresent())
            return new CurrencyResponseDto(rate.get().getMid().toPlainString());
        else
            throw new XCodeException(XCodeException.Code.INVALID_REQUEST);
    }
}
