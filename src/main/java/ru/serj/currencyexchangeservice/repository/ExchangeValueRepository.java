package ru.serj.currencyexchangeservice.repository;

import org.springframework.data.repository.CrudRepository;
import ru.serj.currencyexchangeservice.model.ExchangeValue;

public interface ExchangeValueRepository extends CrudRepository<ExchangeValue, Long> {

    ExchangeValue findExchangeValueByFromAndTo(String from, String to);

}
