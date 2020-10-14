package com.ajasoft.jackpot.jackpotcore.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
@Data
public class Outcomming {

    @Id
    private Long id;
    private int amount;

    private Date date;

    @ManyToOne
    @JoinColumn(name="bid_id")
    private Bid bid;

    @ManyToOne
    @JoinColumn(name="wallet_id")
    private Wallet wallet;

}
