package com.ajasoft.jackpot.jackpotcore.controller;

import com.ajasoft.jackpot.jackpotcore.domain.Bid;
import com.ajasoft.jackpot.jackpotcore.domain.BidSequence;
import com.ajasoft.jackpot.jackpotcore.domain.BidStatus;
import com.ajasoft.jackpot.jackpotcore.domain.Jackpot;
import com.ajasoft.jackpot.jackpotcore.service.BidService;
import com.ajasoft.jackpot.jackpotcore.service.JackpotService;
import com.ajasoft.jackpot.jackpotcore.validators.BidValidator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;
import java.util.Optional;

@AllArgsConstructor
@RequestMapping("/bid")
@RestController
//@CrossOrigin(origins = "http://localhost:4200")
@Slf4j
public class BidController {

    private BidService bidService;
    private BidValidator bidValidator;
    private JackpotService jackpotService;

    @GetMapping
    public Flux<Bid> getAll() {
        return Flux.just(bidService.findAll().toArray(new Bid[0]));
    }


    @GetMapping("{id}")
    public ResponseEntity<Mono<Bid>> get(@PathVariable("id") Long id) {
        Optional<Bid> aux = bidService.find(id);
        if (aux.isPresent()) {
            return ResponseEntity.ok(Mono.just(aux.get()));
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @PostMapping
    public ResponseEntity<Mono<Bid>> post(@RequestBody @Validated Bid bid) {
        bid.setBidStatus(BidStatus.DRAFT);
        bid.setCreated(LocalDateTime.now());

        Optional<Jackpot> jackpot = jackpotService.find(bid.getJackpot().getId());
        LocalDateTime limitDate = LocalDateTime.of(jackpot.get().getDate(), LocalTime.of(13, 0));

        bid.setLimitDate(limitDate);
        bid.setMinMembers(1);
        bid = bidService.save(bid);

        log.info("bid created: " + bid);
        return ResponseEntity.status(HttpStatus.CREATED).body(Mono.just(bid));
    }

    @PutMapping
    public ResponseEntity<Mono<Bid>> put(@RequestBody Bid bid) {
        Optional<Bid> aux = bidService.find(bid.getId());
        if (aux.isPresent()) {
            bid = bidService.save(bid);
            log.info("bid updated: " + bid);
            return ResponseEntity.status(HttpStatus.OK).body(Mono.just(bid));
        } else {
            log.info("bid not found: " + bid);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Mono.just(bid));
        }

    }

    @DeleteMapping
    public ResponseEntity<Mono<Bid>> delete(@RequestBody Bid bid) {
        Optional<Bid> aux = bidService.find(bid.getId());
        if (aux.isPresent()) {
            bidService.delete(bid);
            log.info("bid deleted: " + bid);
            return ResponseEntity.status(HttpStatus.CREATED).body(Mono.just(bid));
        } else {
            log.info("bid not found: " + bid);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Mono.just(bid));
        }

    }

    @PostMapping("/{bid}/sequence")
    public ResponseEntity<Mono<Bid>> addSequence(@RequestBody BidSequence bidSequence, @PathVariable Long bid) {

        if (Objects.nonNull(bidSequence.getId())) {
            bidService.save(bidSequence);
            Bid oBid = bidService.find(bid).get();
            log.info("bid sequence updated: " + bid);
            return ResponseEntity.status(HttpStatus.CREATED).body(Mono.just(oBid));

        } else {
            Bid oBid = bidService.find(bid).get();
            oBid.getBidSequences().add(bidSequence);
            oBid = bidService.save(oBid);
            log.info("bid sequence saved: " + bid);
            return ResponseEntity.status(HttpStatus.CREATED).body(Mono.just(oBid));
        }

    }

    @DeleteMapping("/{bid}/sequence")
    public ResponseEntity<Mono<Bid>> deleteSequence(@RequestBody BidSequence bidSequence) {
        Bid bid = bidService.deleteSequence(bidSequence);
        return ResponseEntity.status(HttpStatus.CREATED).body(Mono.just(bid));
    }

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(bidValidator);
        log.info("bidValidator up");
    }
}

