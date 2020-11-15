package com.ajasoft.jackpot.jackpotcore.controller;

import com.ajasoft.jackpot.jackpotcore.domain.Customer;
import com.ajasoft.jackpot.jackpotcore.domain.View;
import com.ajasoft.jackpot.jackpotcore.service.CustomerService;
import com.ajasoft.jackpot.jackpotcore.validators.CustomerValidator;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

@AllArgsConstructor
@RequestMapping("/auth")
@RestController
@CrossOrigin
@Slf4j
public class AuthController {

    private CustomerService customerService;
    private CustomerValidator CustomerValidator;



    @GetMapping
    public Mono<String> get() {
        return Mono.just("Test");
    }

    @RequestMapping(method = RequestMethod.OPTIONS)
    public ResponseEntity<Mono<Customer>> options(@RequestBody Customer customer) {
        return null;
    }

    @CrossOrigin
    @PostMapping
    @JsonView(value = View.CustomerView.External.class)
    public ResponseEntity<Mono<Customer>> post(@RequestBody Customer customer) {

        if (Objects.nonNull(customer.getToken())) {
            Customer resp = customerService.refreshToken(customer.getToken());
            if (Objects.nonNull(resp)) {
                log.info("auth refresh: " + resp);
                return ResponseEntity.status(HttpStatus.OK).body(Mono.just(resp));
            }
        }


        Customer resp = customerService.login(customer.getEmail(), customer.getPassword());
        if (Objects.nonNull(resp)) {
            log.info("auth login: " + resp);
            return ResponseEntity.status(HttpStatus.OK).body(Mono.just(resp));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

    }

    @DeleteMapping
    public ResponseEntity<Mono<Customer>> delete(@RequestBody Customer Customer) {
        Optional<Customer> aux = customerService.find(Customer.getId());
        if (aux.isPresent()) {
            customerService.delete(Customer);
            log.info("Customer deleted: " + Customer);
            return ResponseEntity.status(HttpStatus.CREATED).body(Mono.just(Customer));
        } else {
            log.info("Customer not found: " + Customer);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Mono.just(Customer));
        }

    }


}

