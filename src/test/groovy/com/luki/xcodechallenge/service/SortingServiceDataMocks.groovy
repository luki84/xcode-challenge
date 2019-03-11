package com.luki.xcodechallenge.service

import com.luki.xcodechallenge.dao.Numbers

class SortingServiceDataMocks {
    static sortingDataASC = new Numbers(
            numbers: [1, 5, 3, 9, 7],
            order: Numbers.Order.ASC
    )

    static sortingDataDESC = new Numbers(
            numbers: [1, 5, 3, 9, 7],
            order: Numbers.Order.DESC
    )

    static sortingDataNull = new Numbers(
            numbers: null,
            order: Numbers.Order.DESC
    )
}
