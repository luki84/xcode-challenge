package com.luki.xcodechallenge.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class Rate {
    String no;
    Date effectiveDate;
    BigDecimal mid;
}
