package com.luki.xcodechallenge.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = "classpath:nbp.properties", ignoreResourceNotFound = true)
public class NbpPropertySourceConfiguration {
}

