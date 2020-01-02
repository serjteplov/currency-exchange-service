package ru.serj.currencyexchangeservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExchangeValue {

    @Id
    Long id;
    @Column(name = "currency_from")
    String from;
    @Column(name = "currency_to")
    String to;
    BigDecimal conversionMultiple;
    int port;
}
