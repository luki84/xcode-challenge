package com.luki.xcodechallenge.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Currency {
    String table;
    String currency;
    Code code;
    List<Rate> rates;

    public enum Code {
        THB, USD, AUD, HKD, CAD, NZD, SGD, EUR, HUF, CHF, GBP, UAH, JPY, CZK, DKK, ISK,
        NOK, SEK, HRK, RON, BGN, TRY, ILS, CLP, PHP, MXN, ZAR, BRL, MYR, RUB, IDR, INR,
        KRW, CNY, XDR
    }
}
