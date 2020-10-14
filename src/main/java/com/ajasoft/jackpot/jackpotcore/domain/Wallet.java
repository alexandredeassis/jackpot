package com.ajasoft.jackpot.jackpotcore.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
@Data
public class Wallet {

    @Id
    private Long id;
    private Date date;

    @ManyToOne
    @JoinColumn(name="customer_id", unique = true)
    private Customer customer;

}
