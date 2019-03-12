package com.luki.xcodechallenge.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CurrencyQuery {
    @NotBlank
    Currency.Code currency;
}
