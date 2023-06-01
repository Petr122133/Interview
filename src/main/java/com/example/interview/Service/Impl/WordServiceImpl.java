package com.example.interview.Service.Impl;

import com.example.interview.Service.WordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;


@Service
@Slf4j
public class WordServiceImpl implements WordService {

    public Mono<String> getWord(String s) {
        return Mono.just(s)
                .doOnError(error -> log.error("WordService: ", error));
    }
}
