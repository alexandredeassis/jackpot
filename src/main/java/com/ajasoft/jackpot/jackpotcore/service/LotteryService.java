package com.ajasoft.jackpot.jackpotcore.service;

import com.ajasoft.jackpot.jackpotcore.domain.Lottery;
import com.ajasoft.jackpot.jackpotcore.repository.LotteryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class LotteryService {

    private LotteryRepository lotteryRepository;

    public List<Lottery> findAll() {
        return lotteryRepository.findAll();
    }

    public Optional<Lottery> find(Long id) {
        return lotteryRepository.findById(id);
    }

}
