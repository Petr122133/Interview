package com.example.interview.integration;

import com.example.interview.InterviewApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.nio.charset.StandardCharsets;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(classes = InterviewApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class InterviewControllerIntegrationTests {
    @Autowired
    Clock clock;

    @LocalServerPort
    private int port;

    @Test
    public void testGetWordPlusDate() {
        final String day = LocalDateTime.now(clock)
                .getDayOfWeek()
                .toString();
        final String word = "hi";

        var result = WebTestClient.bindToServer()
                .baseUrl("http://localhost:" + port)
                .build()
                .get()
                .uri("/interview/{word}", word)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody()
                .returnResult()
                .getResponseBody();

        assertThat(result)
                .isEqualTo(word.concat(day).getBytes(StandardCharsets.UTF_8));
    }

    @TestConfiguration
    public static class TestConfig {

        @Bean
        @Primary
        public Clock getClock() {
            return Clock.fixed(
                    Instant.parse("2018-08-19T16:02:42.00Z"),
                    ZoneId.of("Europe/Berlin")
            );
        }

    }
}
