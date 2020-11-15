package com.ajasoft.jackpot.jackpotcore.repository;

import com.ajasoft.jackpot.jackpotcore.domain.Customer;
import com.ajasoft.jackpot.jackpotcore.domain.Jackpot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Customer findByEmailAndPassword(String email, String password);
    Customer findByToken(String token);
}
