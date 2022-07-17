package com.gioliveira.bookservice.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.gioliveira.bookservice.response.Cambio;

/*
Utilizando manualmente a espesificação do serviço
@FeignClient(name="cambio-service", url="localhost:8000", "localhost:8001")
public interface CambioProxy {
    @GetMapping(value="/cambio-service/{amount}/{from}/{to}")
    public Cambio calculeCambio(
        @PathVariable("amount") Double amount,
        @PathVariable("from") String from,
        @PathVariable("to") String to);
}
*/

//Utilizando ServiceRegister Eureka
@FeignClient(name="cambio-service")
public interface CambioProxy {
    @GetMapping(value="/cambio-service/{amount}/{from}/{to}")
    public Cambio calculeCambio(
        @PathVariable("amount") Double amount,
        @PathVariable("from") String from,
        @PathVariable("to") String to);
}