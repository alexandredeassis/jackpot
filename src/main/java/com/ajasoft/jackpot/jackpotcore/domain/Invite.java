package com.ajasoft.jackpot.jackpotcore.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
@Data
public class Invite {

    @Id
    private Long id;
    private Date sent;
    private Date accepted;

    @ManyToOne
    @JoinColumn(name="customer_id")
    private Customer customer;


}
