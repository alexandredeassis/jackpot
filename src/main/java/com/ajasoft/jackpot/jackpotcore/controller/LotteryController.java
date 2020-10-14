package com.ajasoft.jackpot.jackpotcore.controller;

import com.ajasoft.jackpot.jackpotcore.domain.Lottery;
import com.ajasoft.jackpot.jackpotcore.service.LotteryService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@RequestMapping("/lottery")
@RestController
//@CrossOrigin(origins = "http://localhost:4200")
public class LotteryController {

    private LotteryService lotteryService;

    @GetMapping
    public Flux<Lottery> get() {
        return Flux.just(lotteryService.findAll().toArray(new Lottery[0]));
    }

}

