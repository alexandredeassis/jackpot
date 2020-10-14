package com.ajasoft.jackpot.jackpotcore.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Data
public class BidSequenceNumber {

    @Id
    private Long id;
    private int value;

    @ManyToOne
    @JoinColumn(name="bidsequence_id")
    private BidSequence bidSequence;

}
