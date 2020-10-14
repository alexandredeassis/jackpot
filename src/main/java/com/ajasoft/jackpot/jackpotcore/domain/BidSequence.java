package com.ajasoft.jackpot.jackpotcore.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.Set;

@Entity
@Data
public class BidSequence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="bid_id", nullable = false)
    private Bid bid;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name="bidsequence_id")
    private Set<BidSequenceNumber> bidSequenceNumbers;



}
