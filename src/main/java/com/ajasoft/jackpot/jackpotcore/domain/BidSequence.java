package com.ajasoft.jackpot.jackpotcore.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Data
public class BidSequence {

    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name="bid_id")
    private Bid bid;

    @OneToMany(mappedBy = "bidSequence")
    private List<BidSequenceNumber> bidSequenceNumbers;
}
