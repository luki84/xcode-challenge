package com.luki.xcodechallenge.client;

import com.luki.xcodechallenge.configuration.NbpClientConfiguration;
import com.luki.xcodechallenge.dto.Currency;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.time.Duration;

@Component
public class NbpClient {
    private final NbpClientConfiguration nbpClientConfiguration;

    public NbpClient(NbpClientConfiguration nbpClientConfiguration) {
        this.nbpClientConfiguration = nbpClientConfiguration;
    }

    public Currency getMid(Currency.Code code) {
        URI url = nbpClientConfiguration.uriBuilder()
                .path("exchangerates/rates/A")
                .pathSegment(code.name())
                .build().toUri();

        return new RestTemplateBuilder()
                .setConnectTimeout(Duration.ofSeconds(3))
                .setReadTimeout(Duration.ofSeconds(10))
                .build()
                .getForObject(url, Currency.class);
    }
}
