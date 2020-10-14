package com.ajasoft.jackpot.jackpotcore.controller;

import com.ajasoft.jackpot.jackpotcore.domain.Jackpot;
import com.ajasoft.jackpot.jackpotcore.domain.Lottery;
import com.ajasoft.jackpot.jackpotcore.service.JackpotService;
import com.ajasoft.jackpot.jackpotcore.service.LotteryService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@RequestMapping("/jackpot")
@RestController
//@CrossOrigin(origins = "http://localhost:4200")
public class JackpotController {

    private JackpotService jackpotService;

    @GetMapping
    public Flux<Jackpot> get() {
        return Flux.just(jackpotService.findAll().toArray(new Jackpot[0]));
    }

    @PostMapping
    public Mono<Jackpot> post(@RequestBody Jackpot jackpot) {
        System.out.println("jackpot: "+jackpot);
        return Mono.just(jackpotService.save(jackpot));
    }

}

