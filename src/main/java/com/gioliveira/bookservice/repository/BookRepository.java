package com.gioliveira.bookservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gioliveira.bookservice.model.Book;

public interface BookRepository extends JpaRepository<Book, Long>{
    Book findBookById(Long id);
    Book findBookByAuthorAndTitle(String author, String title);
    
}
