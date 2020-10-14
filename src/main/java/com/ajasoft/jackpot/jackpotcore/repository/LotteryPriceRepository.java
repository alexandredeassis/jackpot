package com.ajasoft.jackpot.jackpotcore.repository;

import com.ajasoft.jackpot.jackpotcore.domain.Lottery;
import com.ajasoft.jackpot.jackpotcore.domain.LotteryPrice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LotteryPriceRepository extends JpaRepository<LotteryPrice, Long> {
}
