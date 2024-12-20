package com.yangxj96.saas.server.gateway

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import reactor.core.publisher.Mono
import java.time.Duration

@SpringBootTest
internal class ApplicationTest {

    @Test
    fun test01() {
        Mono
            .defer { Mono.just("hello world") }
            .delayElement(Duration.ofMillis(5))
            .subscribe()
        Assertions.assertTrue(true)
    }

}