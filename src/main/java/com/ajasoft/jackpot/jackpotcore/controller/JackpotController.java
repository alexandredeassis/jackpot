package com.ajasoft.jackpot.jackpotcore.controller;

import com.ajasoft.jackpot.jackpotcore.domain.Jackpot;
import com.ajasoft.jackpot.jackpotcore.domain.Lottery;
import com.ajasoft.jackpot.jackpotcore.service.JackpotService;
import com.ajasoft.jackpot.jackpotcore.service.LotteryService;
import com.ajasoft.jackpot.jackpotcore.validators.JackpotValidator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ldap.embedded.EmbeddedLdapProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.Optional;

@AllArgsConstructor
@RequestMapping("/jackpot")
@RestController
//@CrossOrigin(origins = "http://localhost:4200")
@Slf4j
public class JackpotController {

    private JackpotService jackpotService;
    private JackpotValidator jackpotValidator;

    @GetMapping
    public Flux<Jackpot> getAll() {

        return Flux.just(jackpotService.findAll().toArray(new Jackpot[0]));
    }


    @GetMapping("{id}")
    public ResponseEntity<Mono<Jackpot>> get(@PathVariable("id") Long id) {
        Optional<Jackpot> aux = jackpotService.find(id);
        if (aux.isPresent()) {
            return ResponseEntity.ok(Mono.just(aux.get()));
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @PostMapping
    public ResponseEntity<Mono<Jackpot>> post(@RequestBody @Validated Jackpot jackpot) {
        jackpot = jackpotService.save(jackpot);
        log.info("jackpot created: " + jackpot);
        return ResponseEntity.status(HttpStatus.CREATED).body(Mono.just(jackpot));
    }

    @PutMapping
    public ResponseEntity<Mono<Jackpot>> put(@RequestBody Jackpot jackpot) {
        Optional<Jackpot> aux = jackpotService.find(jackpot.getId());
        if (aux.isPresent()) {
            jackpot = jackpotService.save(jackpot);
            log.info("jackpot updated: " + jackpot);
            return ResponseEntity.status(HttpStatus.OK).body(Mono.just(jackpot));
        } else {
            log.info("jackpot not found: " + jackpot);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Mono.just(jackpot));
        }

    }

    @DeleteMapping
    public ResponseEntity<Mono<Jackpot>> delete(@RequestBody Jackpot jackpot) {
        Optional<Jackpot> aux = jackpotService.find(jackpot.getId());
        if (aux.isPresent()) {
            jackpotService.delete(jackpot);
            log.info("jackpot deleted: " + jackpot);
            return ResponseEntity.status(HttpStatus.CREATED).body(Mono.just(jackpot));
        } else {
            log.info("jackpot not found: " + jackpot);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Mono.just(jackpot));
        }

    }

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(jackpotValidator);
        log.info("jackpotValidator up");
    }
}

