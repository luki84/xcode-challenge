package com.luki.xcodechallenge.service

import com.luki.xcodechallenge.dto.Rate
import com.luki.xcodechallenge.dto.Currency

class ExchangeServiceDataMocks {
    static currencyEUR = new Currency(
            currency: "euro",
            code: "EUR",
            rates: [new Rate(
                    mid: new BigDecimal("1.2345")
            )]
    )
}