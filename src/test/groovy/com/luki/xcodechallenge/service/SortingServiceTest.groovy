package com.luki.xcodechallenge.service

import spock.lang.Specification

class SortingServiceTest extends Specification {

    SortingService service = new SortingService()

    def "should sort numbers in ascending order"() {
        when:
        def response = service.sortNumbers(
                SortingServiceDataMocks.sortingDataASC.numbers,
                SortingServiceDataMocks.sortingDataASC.order
        )
        then:
        response == [1, 3, 5, 7, 9]
    }

    def "should sort numbers in descending order"() {
        when:
        def response = service.sortNumbers(
                SortingServiceDataMocks.sortingDataDESC.numbers,
                SortingServiceDataMocks.sortingDataDESC.order
        )
        then:
        response == [9, 7, 5, 3, 1]
    }

    def "should allow "() {
        when:
        def response = service.sortNumbers(
                SortingServiceDataMocks.sortingDataNull.numbers,
                SortingServiceDataMocks.sortingDataNull.order
        )
        then:
        response == null
    }
}
