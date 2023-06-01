package com.example.interview.Service;

import reactor.core.publisher.Mono;

public interface DateService {
    Mono<String> getDay();
}
