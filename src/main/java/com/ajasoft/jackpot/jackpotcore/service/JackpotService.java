package com.ajasoft.jackpot.jackpotcore.service;

import com.ajasoft.jackpot.jackpotcore.domain.Jackpot;
import com.ajasoft.jackpot.jackpotcore.domain.Lottery;
import com.ajasoft.jackpot.jackpotcore.repository.JackpotRepository;
import com.ajasoft.jackpot.jackpotcore.repository.LotteryRepository;
import lombok.AllArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.boot.ansi.Ansi8BitColor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class JackpotService {

    private JackpotRepository jackpotRepository;

    public List<Jackpot> findAll() {
        List<Jackpot> list = jackpotRepository.findAll();
        //list.stream().forEach(p-> Hibernate.initialize(p.getJackpotNumbers()));
        return list;
    }

    public Jackpot save(Jackpot jackpot) {

        return jackpotRepository.save(jackpot);
    }

}
