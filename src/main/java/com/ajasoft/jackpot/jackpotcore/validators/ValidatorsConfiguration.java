package com.ajasoft.jackpot.jackpotcore.validators;

import com.ajasoft.jackpot.jackpotcore.domain.Jackpot;
import com.ajasoft.jackpot.jackpotcore.service.LotteryService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

@Slf4j
@Configuration
@AllArgsConstructor
public class ValidatorsConfiguration {

    private JackpotValidator jackpotValidator;




}


