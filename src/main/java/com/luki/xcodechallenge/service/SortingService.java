package com.luki.xcodechallenge.service;

import com.luki.xcodechallenge.dto.NumbersQuery;
import com.luki.xcodechallenge.dto.NumbersResponseDto;
import com.luki.xcodechallenge.exception.XCodeException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class SortingService {

    public NumbersResponseDto sortNumbers (List<? extends Number> numbers, NumbersQuery.Order order) {
        if (numbers == null) return null;

        int multiplier = (order == NumbersQuery.Order.DESC) ? -1 : 1;

        List<? extends Number> sortedNumbers = numbers.stream()
                .sorted((number1, number2) -> {
                    Double doubleValue1 = getDoubleValue(number1);
                    Double doubleValue2 = getDoubleValue(number2);
                    return multiplier * (doubleValue1).compareTo(doubleValue2);
                })
                .collect(Collectors.toList());

        return new NumbersResponseDto(sortedNumbers);
    }

    private Double getDoubleValue(Number number) {
        Double doubleValue;
        if (number instanceof Integer)
            doubleValue = number.doubleValue();
        else if (number instanceof Double)
            doubleValue = (Double) number;
        else throw new XCodeException(XCodeException.Code.INVALID_REQUEST);
        return doubleValue;
    }
}
