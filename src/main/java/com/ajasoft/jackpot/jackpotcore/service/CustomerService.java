package com.ajasoft.jackpot.jackpotcore.service;

import com.ajasoft.jackpot.jackpotcore.domain.Bid;
import com.ajasoft.jackpot.jackpotcore.domain.Customer;
import com.ajasoft.jackpot.jackpotcore.repository.BidRepository;
import com.ajasoft.jackpot.jackpotcore.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
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

    public void delete(Customer customer) {
        customerRepository.delete(customer);
    }

}
