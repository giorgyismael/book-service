package com.gioliveira.bookservice.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cambio {
    private Long id;
    private String fromCurrency;
    private String toCurrency;
    private Double fromCurrencyconversionFactor;
    private Double convertedValue;
    private String environment;    
}
