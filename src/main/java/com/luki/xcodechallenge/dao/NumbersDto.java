package com.luki.xcodechallenge.dao;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class NumbersDto {
    List<? extends Number> numbers;
    @NotNull
    Order order;

    public enum Order {
        ASC, DESC
    }

    @AssertTrue
    public boolean isValid() {
        if(numbers == null) return true;
        else if (numbers.size() >= 0) return true;
        else return false;
    }

}
