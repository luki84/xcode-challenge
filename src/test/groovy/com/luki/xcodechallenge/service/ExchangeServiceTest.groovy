package com.luki.xcodechallenge.service

import com.luki.xcodechallenge.client.NbpClient
import com.luki.xcodechallenge.dao.Currency
import org.mockito.Mock
import spock.lang.Specification

class ExchangeServiceTest extends Specification {

    NbpClient nbpClient = Mock();
    ExchangeService exchangeService = new ExchangeService(nbpClient)

    def "should return mid for given currency code" () {
        given:
        nbpClient.getMid(_) >> ExchangeServiceDataMocks.currencyEUR
        when:
        def response = exchangeService.getMid(Currency.Code.EUR)
        then:
        response.value == "1.2345"
    }

}
