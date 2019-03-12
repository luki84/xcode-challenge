package com.luki.xcodechallenge.dao;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class NumbersResponseDto {
    List<? extends Number> numbers;
}
