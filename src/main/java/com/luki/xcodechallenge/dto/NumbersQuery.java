package com.luki.xcodechallenge.dto;

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
public class NumbersQuery {
    @NotNull (message = "Numbers cannot be empty.")
    List<? extends Number> numbers;
    @NotNull (message = "Order cannot be empty. It must have one of two values: 'ASC' or 'DESC'")
    Order order;

    public enum Order {
        ASC, DESC
    }

    @AssertTrue
    public boolean isValid() {
        if(numbers == null) return false;
        else if (numbers.size() == 0) return false;
        else return !numbers.contains(null);
    }

}
