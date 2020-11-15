package com.ajasoft.jackpot.jackpotcore.validators.impl;

import com.ajasoft.jackpot.jackpotcore.domain.Bid;
import com.ajasoft.jackpot.jackpotcore.domain.BidMaster;
import com.ajasoft.jackpot.jackpotcore.domain.Customer;
import com.ajasoft.jackpot.jackpotcore.domain.Jackpot;
import com.ajasoft.jackpot.jackpotcore.service.CustomerService;
import com.ajasoft.jackpot.jackpotcore.service.JackpotService;
import com.ajasoft.jackpot.jackpotcore.service.LotteryService;
import com.ajasoft.jackpot.jackpotcore.validators.BidValidator;
import com.ajasoft.jackpot.jackpotcore.validators.JackpotValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import java.time.LocalDate;
import java.util.Optional;

@Component
public class BidValidatorImpl implements BidValidator {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private JackpotService jackpotService;

    @Override
    public boolean supports(Class<?> aClass) {
        return Bid.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, "jackpot", "jackpot.empty");
        ValidationUtils.rejectIfEmpty(errors, "bidMaster", "bidMaster.empty");

        if (errors.getErrorCount() > 0)
            return;

        Bid bid = (Bid) o;
        Optional<Customer> customer = customerService.find(bid.getBidMaster().getId());
        if (!customer.isPresent()) {
            errors.rejectValue("bidMaster", "bidMaster.notfound", "bidMaster was not found for id: " + bid.getBidMaster().getId());
            return;
        }
        if (!(customer.get() instanceof BidMaster)) {
            errors.rejectValue("bidMaster", "bidMaster.invalid", "customer is not a bidMaster, id: " + bid.getBidMaster().getId());
            return;
        }

        Optional<Jackpot> jackpot = jackpotService.find(bid.getJackpot().getId());

        if (!(jackpot.isPresent())) {
            errors.rejectValue("jackpot", "jackpot.notfound", "jackpot was not found for id: " + bid.getJackpot().getId());
            return;
        }

        /*nao aceitar  criação de bids para jackpots anteriores a amanhã..*/
        LocalDate tomorrow = LocalDate.now().plusDays(1);
        LocalDate jackpotDate = jackpot.get().getDate();
        if (jackpotDate.isBefore(tomorrow)) {
            errors.rejectValue("jackpot", "jackpot.expired", "jackpot was expired for id: " + bid.getJackpot().getId());
        }

    }

}
