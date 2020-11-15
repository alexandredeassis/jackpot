package com.ajasoft.jackpot.jackpotcore.validators.impl;

import com.ajasoft.jackpot.jackpotcore.domain.Jackpot;
import com.ajasoft.jackpot.jackpotcore.service.LotteryService;
import com.ajasoft.jackpot.jackpotcore.validators.JackpotValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

@Component
public class JackpotValidatorImpl implements JackpotValidator {

    @Autowired
    private LotteryService lotteryService;

    @Override
    public boolean supports(Class<?> aClass) {
        return Jackpot.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, "lottery", "lottery.empty");

        if (errors.getErrorCount() > 0)
            return;

        Jackpot jackpot = (Jackpot) o;
        if (!lotteryService.find(jackpot.getLottery().getId()).isPresent()) {
            errors.rejectValue("lottery", "lottery.notfound", "lottery was not found for id: " + jackpot.getLottery().getId());
        }

    }

}
