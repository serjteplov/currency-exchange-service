package ru.serj.currencyexchangeservice.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import ru.serj.currencyexchangeservice.model.ExchangeValue;
import ru.serj.currencyexchangeservice.repository.ExchangeValueRepository;
import ru.serj.currencyexchangeservice.util.InstanceInformationService;

import java.util.Map;

@RestController
@Slf4j
public class CurrencyExchangeController {

    @Autowired
    ExchangeValueRepository repository;

    @Autowired
    InstanceInformationService informationService;

    @Value("${server.port:0}")
    int port;

    @GetMapping("healthz")
    public String health() {
        return "Ok";
    }

    @GetMapping("currency-exchange/{from}/to/{to}")
    public ExchangeValue retrieveExchangeValue(@PathVariable String from, @PathVariable String to,
                                               @RequestHeader Map<String, String> headers) {
        printAllHeaders(headers);
        ExchangeValue exchangeValue = repository.findExchangeValueByFromAndTo(from, to);
        if (exchangeValue == null) {
            throw new RuntimeException("Unable to find data to convert " + from + " to " + to);
        }
        log.info("{} {} {}", from, to, exchangeValue);
        exchangeValue.setPort(port);
        exchangeValue.setExchangeEnvironmentInfo(informationService.retrieveInstanceInfo());
        return exchangeValue;
    }

    private void printAllHeaders(Map<String, String> headers) {
        headers.forEach((key, value) -> {
            log.info(String.format("Header '%s' = %s", key, value));
        });
    }

}
