package com.luki.xcodechallenge.controller

import com.luki.xcodechallenge.dao.NumbersResponseDto
import com.luki.xcodechallenge.service.SortingService
import org.spockframework.spring.SpringBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Specification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status


@SpringBootTest(classes = [SortingController])
@AutoConfigureMockMvc
@AutoConfigureWebMvc
class SortingControllerTest extends Specification {

    @Autowired
    protected MockMvc mvc

    @SpringBean
    SortingService sortingService = Stub()


    def "when get is performed then the response has status 200"() {
//        given:
//        sortingService.sortNumbers(_,_) >> new NumbersResponseDto(numbers: [10, 9, 7, 5, 3, 1, 1, -5] as List)

        def requestBody = """
        {
            "numbers": ["1", "5", "3", "9", "7", "10", "1", "-5"],
            "order": "DESC"
        }
        """

        when:
        def respond = mvc.perform(post("/numbers/sort-command")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody)
        )

        then:
        respond.andExpect(status().isOk())
        respond.andExpect(content().json("""
        {
            "numbers": [
                10,
                9,
                7,
                5,
                3,
                1,
                1,
                -5
            ]
        }
        """));
    }
}
