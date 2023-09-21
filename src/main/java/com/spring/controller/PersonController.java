package com.spring.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("person")
public class PersonController {


    @GetMapping("/person")
    public Integer getPersonCount() {
        return 20;
    }

    @GetMapping(value = "/personm", produces = {"text/event-stream"})
    public Mono<Integer> getPersonCountReactive() {
        return Mono.just(20);
    }

    @GetMapping("/persons")
    public List<Integer> getPersonInt() throws InterruptedException {

        List<Integer> persons = new ArrayList<Integer>();
        for (int i = 0; i < 20; i++) {
            persons.add(i + 1);
            Thread.sleep(500);
        }
        return persons;
    }

    @GetMapping(value = "/personsm", produces = {"text/event-stream"})
    public Flux<Integer> getPersonIntFlux() {

        return Flux.create(fluxList -> {
            for (int i = 0; i < 20; i++) {
                fluxList.next(i);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            fluxList.complete();
        });
    }


}
