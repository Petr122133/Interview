package com.example.interview.service;

import com.example.interview.Service.Impl.DateServiceImpl;
import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;


class DateServiceTests {

    final Clock clock = Clock.fixed(
            Instant.parse("2018-08-19T16:02:42.00Z"),
            ZoneId.of("Europe/Berlin")
    );

    final String day = LocalDateTime.now(clock)
            .getDayOfWeek()
            .toString();

    @Test
    public void testGetDay() {
        final var dateService = new DateServiceImpl(clock);

        StepVerifier.create(dateService.getDay())
                .expectNext(day)
                .verifyComplete();
    }
}
