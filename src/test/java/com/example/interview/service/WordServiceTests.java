package com.example.interview.service;

import com.example.interview.Service.Impl.WordServiceImpl;
import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;


class WordServiceTests {

    @Test
    public void testGetWord() {
        final var wordService = new WordServiceImpl();
        final var word = "hi";

        StepVerifier.create(wordService.getWord(word))
                .expectNext(word)
                .verifyComplete();
    }
}
