package com.ajasoft.jackpot.jackpotcore.repository;

import com.ajasoft.jackpot.jackpotcore.domain.Bid;
import com.ajasoft.jackpot.jackpotcore.domain.BidSequence;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BidSequenceRepository extends JpaRepository<BidSequence, Long> {
}
