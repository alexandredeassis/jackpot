package com.ajasoft.jackpot.jackpotcore.service;

import com.ajasoft.jackpot.jackpotcore.domain.Bid;
import com.ajasoft.jackpot.jackpotcore.domain.Jackpot;
import com.ajasoft.jackpot.jackpotcore.repository.BidRepository;
import com.ajasoft.jackpot.jackpotcore.repository.JackpotRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BidService {

    private BidRepository bidRepository;

    public Optional<Bid> find(Long id) {
        return bidRepository.findById(id);
    }

    public List<Bid> findAll() {
        List<Bid> list = bidRepository.findAll();
        return list;
    }

    public Bid save(Bid bid) {

        return bidRepository.save(bid);
    }

    public void delete(Bid bid) {
        bidRepository.delete(bid);
    }

}
