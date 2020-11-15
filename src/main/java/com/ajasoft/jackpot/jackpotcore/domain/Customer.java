package com.ajasoft.jackpot.jackpotcore.domain;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
@Data
@Inheritance(strategy = InheritanceType.JOINED)
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(value = {View.CustomerView.External.class})
    private Long id;

    @JsonView(value = {View.CustomerView.External.class})
    private String name;

    @JsonView(value = {View.CustomerView.External.class})
    private String email;

    @JsonView(value = {View.CustomerView.Internal.class})
    private String password;

    @JsonView(value = {View.CustomerView.External.class})
    @Column(unique = true, nullable = true)
    private String token;

    @JsonView(value = {View.CustomerView.External.class})
    private Date lastActivity;


    @ManyToOne
    @JoinColumn(name = "invite_id", nullable = true)
    private Invite invite;

    public String getProfile() {
        return this.getClass().getSimpleName();
    }

}
