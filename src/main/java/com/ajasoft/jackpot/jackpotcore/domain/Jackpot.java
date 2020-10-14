package com.ajasoft.jackpot.jackpotcore.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
@Data
public class Jackpot {

    @Id
    private Long id;
    private Date date;
    private String description;

    @ManyToOne
    @JoinColumn(name="lottery_id")
    private Lottery lottery;

}
