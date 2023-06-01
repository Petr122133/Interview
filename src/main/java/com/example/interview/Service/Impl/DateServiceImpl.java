package com.example.interview.Service.Impl;

import com.example.interview.Service.DateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.Clock;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class DateServiceImpl implements DateService {

    final Clock clock;

    public Mono<String> getDay() {
        return Mono.just(
                        LocalDateTime.now(clock)
                                .getDayOfWeek()
                                .toString()
                )
                .doOnError(error -> log.error("DateService: ", error));
    }
}
