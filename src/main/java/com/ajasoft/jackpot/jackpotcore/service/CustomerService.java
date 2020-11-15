package com.ajasoft.jackpot.jackpotcore.service;

import com.ajasoft.jackpot.jackpotcore.domain.Bid;
import com.ajasoft.jackpot.jackpotcore.domain.Customer;
import com.ajasoft.jackpot.jackpotcore.repository.BidRepository;
import com.ajasoft.jackpot.jackpotcore.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
public class CustomerService {

    private CustomerRepository customerRepository;

    public Optional<Customer> find(Long id) {
        return customerRepository.findById(id);
    }

    public List<Customer> findAll() {
        List<Customer> list = customerRepository.findAll();
        return list;
    }

    public Customer save(Customer customer) {

        return customerRepository.save(customer);
    }

    public Customer updateActivity(Customer customer) {
        customer.setLastActivity(new Date());
        return customerRepository.save(customer);
    }

    public Customer refreshToken(String token) {

        Customer customer = customerRepository.findByToken(token);
        if (!Objects.nonNull(customer)) {
            return null;
        }
       // Instant aux = Instant.now().minus(30, ChronoUnit.MINUTES);
        Instant aux = Instant.now().minus(20, ChronoUnit.SECONDS);
        Instant activity = customer.getLastActivity().toInstant();
        if (activity.isAfter(aux)) {
            customer.setLastActivity(new Date());
            return customerRepository.save(customer);
        } else {
            return null;
        }

    }

    public Customer login(String email, String password) {
        Customer customer = customerRepository.findByEmailAndPassword(email, password);
        if (Objects.nonNull(customer)) {
            customer.setToken(UUID.randomUUID().toString());
            customer = updateActivity(customer);
        }
        return customer;
    }

    public void delete(Customer customer) {
        customerRepository.delete(customer);
    }

}
