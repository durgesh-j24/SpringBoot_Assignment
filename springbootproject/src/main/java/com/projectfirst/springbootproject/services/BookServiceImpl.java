package com.projectfirst.springbootproject.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projectfirst.springbootproject.model.Book;
import com.projectfirst.springbootproject.repository.BookRepository;

@Service
public class BookServiceImpl implements BookService{

	@Autowired
	private BookRepository bookRepo;
	
	@Override
	
	public List<Book> getBooks() {
		
		return bookRepo.findAll();
	}

	@Override
	public Book getBook(int bookId) {
		Book book=bookRepo.getOne(bookId);
		 return book;
	}

	@Override
	public Book addBook(Book book) {
		
		return bookRepo.save(book);
	}

	@Override
	public void deleteBook(int bookId) {
		
		Book entity=bookRepo.getOne(bookId);
		bookRepo.delete(entity);
	}

}
