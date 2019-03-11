package com.luki.xcodechallenge.dao;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Numbers {
    List<Integer> numbers;
    @NotBlank
    Order order;

    public enum Order {
        ASC, DESC
    }
}
