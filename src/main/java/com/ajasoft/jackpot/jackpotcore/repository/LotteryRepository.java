package com.ajasoft.jackpot.jackpotcore.repository;

import com.ajasoft.jackpot.jackpotcore.domain.Lottery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LotteryRepository extends JpaRepository<Lottery, Long> {
}
