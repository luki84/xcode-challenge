package com.luki.xcodechallenge.service;

import com.luki.xcodechallenge.dao.Numbers;
import com.luki.xcodechallenge.dao.NumbersResponseDto;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class SortingService {

    public NumbersResponseDto sortNumbers (List<Integer> numbers, Numbers.Order order) {
        if (numbers == null) return null;

        Comparator<Integer> comparator = (order == Numbers.Order.DESC) ? Comparator.reverseOrder() : Comparator.naturalOrder();
        List<Integer> sortedNumbers = numbers.stream()
                .sorted(comparator)
                .collect(Collectors.toList());

        return new NumbersResponseDto(sortedNumbers);
    }
}
