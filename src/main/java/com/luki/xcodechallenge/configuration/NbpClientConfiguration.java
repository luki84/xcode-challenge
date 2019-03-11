package com.luki.xcodechallenge.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.constraints.NotNull;
import java.net.URI;

@Data
@Component
@ConfigurationProperties("nbp.client")
public class NbpClientConfiguration {
    @NotNull
    URI uri;

    public UriComponentsBuilder uriBuilder() {
        return UriComponentsBuilder.fromUri(uri);
    }
}
