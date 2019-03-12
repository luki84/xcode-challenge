package com.luki.xcodechallenge.controller;

import com.luki.xcodechallenge.dto.NumbersQuery;
import com.luki.xcodechallenge.dto.NumbersResponseDto;
import com.luki.xcodechallenge.service.SortingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/numbers")
public class SortingController {

    @Autowired
    SortingService sortingService;

    @PostMapping("/sort-command")
    public ResponseEntity<NumbersResponseDto> sort (@RequestBody @Valid NumbersQuery numbersQuery) {
        NumbersResponseDto sortedNumbers = sortingService.sortNumbers(
                numbersQuery.getNumbers(),
                numbersQuery.getOrder()
        );
        return new ResponseEntity<>(sortedNumbers, HttpStatus.OK);
    }
}
