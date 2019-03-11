package com.luki.xcodechallenge.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Specification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content

@SpringBootTest(classes = [StatusController])
@AutoConfigureMockMvc
@AutoConfigureWebMvc
class StatusControllerTest extends Specification {

    @Autowired
    protected MockMvc mvc

    def "when get is performed then the response has status 200"() {
        when:
        def request = mvc.perform(get("/status/ping"))

        then:
        request.andExpect(status().isOk())
    }
}