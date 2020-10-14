package com.ajasoft.jackpot.jackpotcore.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class Bid {

    @Id
    private Long id;
    private int maxMembers;
    private int minMembers;

    private Date limitDate;

    @ManyToOne
    @JoinColumn(name="jackpot_id")
    private Jackpot jackpot;

    @OneToMany(mappedBy = "bid")
    private List<BidSequence> bidSequences;

    @ManyToOne
    @JoinColumn(name="bidmaster_id")
    private BidMaster bidMaster;

}
