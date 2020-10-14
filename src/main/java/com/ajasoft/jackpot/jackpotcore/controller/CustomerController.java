package com.ajasoft.jackpot.jackpotcore.controller;

import com.ajasoft.jackpot.jackpotcore.domain.Customer;
import com.ajasoft.jackpot.jackpotcore.service.CustomerService;
import com.ajasoft.jackpot.jackpotcore.validators.CustomerValidator;
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

import java.util.Optional;

@AllArgsConstructor
@RequestMapping("/customer")
@RestController
//@CrossOrigin(origins = "http://localhost:4200")
@Slf4j
public class CustomerController {

    private CustomerService customerService;
    private CustomerValidator CustomerValidator;


    @GetMapping
    public Flux<Customer> getAll() {

        return Flux.just(customerService.findAll().toArray(new Customer[0]));
    }


    @GetMapping("{id}")
    public ResponseEntity<Mono<Customer>> get(@PathVariable("id") Long id) {
        Optional<Customer> aux = customerService.find(id);
        if (aux.isPresent()) {
            return ResponseEntity.ok(Mono.just(aux.get()));
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @PostMapping
    public ResponseEntity<Mono<Customer>> post(@RequestBody @Validated Customer Customer) {
        Customer = customerService.save(Customer);
        log.info("Customer created: " + Customer);
        return ResponseEntity.status(HttpStatus.CREATED).body(Mono.just(Customer));
    }

    @PutMapping
    public ResponseEntity<Mono<Customer>> put(@RequestBody Customer Customer) {
        Optional<Customer> aux = customerService.find(Customer.getId());
        if (aux.isPresent()) {
            Customer = customerService.save(Customer);
            log.info("Customer updated: " + Customer);
            return ResponseEntity.status(HttpStatus.OK).body(Mono.just(Customer));
        } else {
            log.info("Customer not found: " + Customer);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Mono.just(Customer));
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

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(CustomerValidator);
        log.info("CustomerValidator up");
    }
}

