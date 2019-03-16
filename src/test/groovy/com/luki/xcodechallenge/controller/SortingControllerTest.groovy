package com.luki.xcodechallenge.controller

import com.luki.xcodechallenge.dto.NumbersResponseDto
import com.luki.xcodechallenge.service.SortingService
import org.spockframework.spring.SpringBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import spock.lang.Specification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status


@SpringBootTest(classes = SortingController)
@AutoConfigureMockMvc
@AutoConfigureWebMvc
class SortingControllerTest extends Specification {

    @SpringBean
    private SortingService service = Stub()

    @Autowired
    protected MockMvc mvc = MockMvcBuilders
            .standaloneSetup(new SortingController(service))
            .build()

    def "POST on /numbers/sort-command (Integers)"() {
        given:
        service.sortNumbers(*_) >> new NumbersResponseDto([10, 9, 7, 5, 3, 1, 1, -5] as List)
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
        respond.andExpect(content().json("""{"numbers":[10,9,7,5,3,1,1,-5]}"""));
    }

    def "POST on /numbers/sort-command (Floating-piont)"() {
        given:
        service.sortNumbers(*_) >> new NumbersResponseDto([10.1, 9.1, 7.1, 5.1, 3.1, 1.1, 1.1, -5.1] as List)
        def requestBody = """
        {
            "numbers": ["1.1", "5.1", "3.1", "9.1", "7.1", "10.1", "1.1", "-5.1"],
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
        respond.andExpect(content().json("""{"numbers":[10.1,9.1,7.1,5.1,3.1,1.1,1.1,-5.1]}"""));
    }

    def "POST on /numbers/sort-command (with incorrect numbers)"() {
        given:
        def requestBody = """
        {
            "numbers": ["a", "5", "3"],
            "order": "DESC"
        }
        """
        when:
        def respond = mvc.perform(post("/numbers/sort-command")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody)
        )
        then:
        respond.andExpect(status().isBadRequest())
    }

    def "POST on /numbers/sort-command (without numbers)"() {
        given:
        def requestBody = """
        {
            "numbers": [],
            "order": "DESC"
        }
        """
        when:
        def respond = mvc.perform(post("/numbers/sort-command")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody)
        )
        then:
        respond.andExpect(status().isBadRequest())
    }

    def "POST on /numbers/sort-command (with order other than 'ASC' / 'DESC')"() {
        given:
        def requestBody = """
        {
            "numbers": [],
            "order": "ASCENDING"
        }
        """
        when:
        def respond = mvc.perform(post("/numbers/sort-command")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody)
        )
        then:
        respond.andExpect(status().isBadRequest())
    }
}
