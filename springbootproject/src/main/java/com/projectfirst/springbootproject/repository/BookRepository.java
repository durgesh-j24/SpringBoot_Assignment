package com.projectfirst.springbootproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projectfirst.springbootproject.model.Book;


public interface BookRepository extends JpaRepository<Book, Integer> {

    
}