package com.ajasoft.jackpot.jackpotcore.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Data
public class Bid {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int maxMembers;
    private int minMembers;

    private LocalDateTime created;
    private LocalDateTime limit;

    @Enumerated(value = EnumType.STRING)
    private BidStatus bidStatus;

    @ManyToOne
    @JoinColumn(name = "jackpot_id", nullable = false)
    private Jackpot jackpot;

    @OneToMany(mappedBy = "bid", fetch = FetchType.EAGER)
    private Set<BidSequence> bidSequences;

    @ManyToOne
    @JoinColumn(name = "bidmaster_id", nullable = false)
    private BidMaster bidMaster;

}
