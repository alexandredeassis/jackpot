package com.ajasoft.jackpot.jackpotcore.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.Set;

@Entity
@Data
public class Lottery {

    @Id
    private Long id;
    private String name;
    private int minNumber;
    private int maxNumber;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name="lottery_id")
    private Set<LotteryPrice> lotteryPrice;

}
