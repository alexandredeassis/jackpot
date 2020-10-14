package com.ajasoft.jackpot.jackpotcore.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
@Data
public class Incomming {

    @Id
    private Long id;
    private int amount;

    private Date date;

    private String description;

    @ManyToOne
    @JoinColumn(name="wallet_id")
    private Wallet wallet;

}
