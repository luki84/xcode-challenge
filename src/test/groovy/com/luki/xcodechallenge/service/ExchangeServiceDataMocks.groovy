package com.luki.xcodechallenge.service

import com.luki.xcodechallenge.dao.Currency
import com.luki.xcodechallenge.dao.Rate

class ExchangeServiceDataMocks {
    static currencyEUR = new Currency(
            currency: "euro",
            code: "EUR",
            rates: [new Rate(
                    mid: new BigDecimal("1.2345")
            )]
    )
}