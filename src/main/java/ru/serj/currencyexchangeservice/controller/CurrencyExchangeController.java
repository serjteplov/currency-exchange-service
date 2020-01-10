package ru.serj.currencyexchangeservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.serj.currencyexchangeservice.model.ExchangeValue;
import ru.serj.currencyexchangeservice.repository.ExchangeValueRepository;

import java.math.BigDecimal;

@RestController
public class CurrencyExchangeController {

    @Autowired
    Environment environment;

    @Autowired
    ExchangeValueRepository repository;

    @Value("${server.port:0}")
    int port;

    @GetMapping("/currency-exchange/{from}/to/{to}")
    public ExchangeValue retrieveExchangeValue(@PathVariable String from, @PathVariable String to) {
        ExchangeValue exchangeValue = new ExchangeValue(1000L,
                from,
                to,
                BigDecimal.valueOf(65),
                port);

        ExchangeValue exchangeValueByFromAndTo = repository.findExchangeValueByFromAndTo(from, to);
        exchangeValueByFromAndTo.setPort(port);
        return exchangeValueByFromAndTo;
    }

}
