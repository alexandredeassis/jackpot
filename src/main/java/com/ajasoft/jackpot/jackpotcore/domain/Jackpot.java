package com.ajasoft.jackpot.jackpotcore.domain;


import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Set;


@Entity
@Data
public class Jackpot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate date;


    private String description;



    @ManyToOne
    @JoinColumn(name="lottery_id")
    private Lottery lottery;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name="jackpot_id")
    private Set<JackpotNumber> jackpotNumbers;


}
