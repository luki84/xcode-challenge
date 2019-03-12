package com.luki.xcodechallenge.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class NumbersResponseDto {
    List<? extends Number> numbers;
}
