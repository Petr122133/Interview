package com.example.interview.Service;

import reactor.core.publisher.Mono;

public interface WordService {
    Mono<String> getWord(String s);
}
