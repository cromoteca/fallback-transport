package com.example.application.services;

import java.time.Duration;
import java.util.logging.Level;

import com.vaadin.flow.server.auth.AnonymousAllowed;

import dev.hilla.BrowserCallable;

import org.springframework.stereotype.Service;

import dev.hilla.EndpointSubscription;
import reactor.core.publisher.Flux;
import reactor.util.retry.Retry;

@BrowserCallable
@AnonymousAllowed
@Service
public class HelloWorldService {

    public String sayHello(String name) {
        if (name.isEmpty()) {
            return "Hello stranger";
        } else {
            return "Hello " + name;
        }
    }

    public Flux<Long> countForever() {
        return Flux.interval(Duration.ofMillis(400))
                .onBackpressureBuffer()
                .doOnNext(tick -> System.out.println("Tick: " + tick))
                .doOnError(err -> System.out.println("Error: " + err))
                .retryWhen(Retry.indefinitely().doAfterRetry(rs -> System.out.println("Retry: " + rs)));
    }
}
