package com.luki.xcodechallenge.service

import com.luki.xcodechallenge.dto.NumbersQuery

class SortingServiceDataMocks {
    static sortingDataASC = new NumbersQuery(
            numbers: [1, 5, 3, 9, 7],
            order: NumbersQuery.Order.ASC
    )

    static sortingDataDESC = new NumbersQuery(
            numbers: [1, 5, 3, 9, 7],
            order: NumbersQuery.Order.DESC
    )

    static sortingDataNull = new NumbersQuery(
            numbers: null,
            order: NumbersQuery.Order.DESC
    )
}
