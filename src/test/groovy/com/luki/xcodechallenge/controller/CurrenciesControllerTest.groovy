package com.luki.xcodechallenge.controller

import com.luki.xcodechallenge.client.NbpClient
import com.luki.xcodechallenge.dto.CurrencyResponseDto
import com.luki.xcodechallenge.service.ExchangeService
import com.luki.xcodechallenge.service.ExchangeServiceDataMocks
import org.spockframework.spring.SpringBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import spock.lang.Specification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest(classes = CurrenciesController)
@AutoConfigureMockMvc
@AutoConfigureWebMvc
class CurrenciesControllerTest extends Specification{

    @SpringBean
    private NbpClient nbpClient = Stub()
    @SpringBean
    ExchangeService service = new ExchangeService(nbpClient)
    @Autowired
    MockMvc mvc = MockMvcBuilders
            .standaloneSetup(new CurrenciesController(service))
            .build()

    def "POST on /currencies/get-current-currency-value-command"() {
        given:
        nbpClient.getMid(*_) >> ExchangeServiceDataMocks.currencyEUR
        service.getMid(*_) >> new CurrencyResponseDto("1.2345")
        def requestBody = """
        {
            "currency": "CZK"
        }
        """
        when:
        def respond = mvc.perform(post("/currencies/get-current-currency-value-command")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody)
        )
        then:
        respond.andExpect(status().isOk())
        respond.andExpect(content().json("""{"value":"1.2345"}"""));
    }

    def "POST on /currencies/get-current-currency-value-command (non-existing currency)"() {
        given:
        def requestBody = """
        {
            "currency": "ABC"
        }
        """
        when:
        def respond = mvc.perform(post("/currencies/get-current-currency-value-command")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody)
        )
        then:
        respond.andExpect(status().isBadRequest())
    }
}
