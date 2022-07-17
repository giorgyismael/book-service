package com.gioliveira.bookservice.controller;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gioliveira.bookservice.model.Book;
import com.gioliveira.bookservice.proxy.CambioProxy;
import com.gioliveira.bookservice.repository.BookRepository;

import lombok.var;

@RestController
@RequestMapping("book-service")
public class BookController {

    @Autowired
    private Environment environment;
    
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CambioProxy cambioProxy;

    @GetMapping(value="/{id}/{currency}")
    public Book findBook(
        @PathVariable("id") Long id,
        @PathVariable("currency") String currency

    ){
        var port = environment.getProperty("local.server.port");
        var book = bookRepository.findBookById(id);
        if (Objects.isNull(book)) throw new IllegalStateException("Produto não encontrado");
        
        /** Utilizando RestTemplate Consulta Serviço
        HashMap<String, String> params = new HashMap<>();
        params.put("amont", book.getPrice().toString());
        params.put("from", "USD");
        params.put("to", currency);
        
        var response = new RestTemplate().getForEntity("http://localhost:8000/cambio-service/{amont}/{from}/{to}", Cambio.class, params);
        var cambio = response.getBody();
        */

        /*Utilizanfo Feing */
        var cambio = cambioProxy.calculeCambio(book.getPrice(), "USD", currency);
       
        book.setCurrency(currency);
        book.setEnvironment(
            "BookService Port: " +port+" CambioService Port: " + cambio.getEnvironment());
        book.setPrice(cambio.getConvertedValue());
        return book;
    }
    
}
