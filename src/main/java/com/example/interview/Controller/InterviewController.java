package com.example.interview.Controller;

import com.example.interview.Service.DateService;
import com.example.interview.Service.WordService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/interview")
@RequiredArgsConstructor
@Slf4j
public class InterviewController {

    final WordService wordService;
    final DateService dateService;

    @GetMapping("/{word}")
    public Mono<String> getWordPlusDate(@PathVariable String word) {
        return Mono.zip(wordService.getWord(word), dateService.getDay())
                .flatMap(results -> {
                    var wordResult = results.getT1();
                    var dateResult = results.getT2();

                    return Mono.just(wordResult.concat(dateResult));
                })
                .doOnError(error -> log.error("InterviewController: ", error));
    }
}
