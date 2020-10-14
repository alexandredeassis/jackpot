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
import java.util.Optional;

@Service
@AllArgsConstructor
public class JackpotService {

    private JackpotRepository jackpotRepository;

    public Optional<Jackpot> find(Long id) {
        return jackpotRepository.findById(id);
    }

    public List<Jackpot> findAll() {
        List<Jackpot> list = jackpotRepository.findAll();
        return list;
    }

    public Jackpot save(Jackpot jackpot) {

        return jackpotRepository.save(jackpot);
    }
    public void delete(Jackpot jackpot) {
        jackpotRepository.delete(jackpot);
    }

}
