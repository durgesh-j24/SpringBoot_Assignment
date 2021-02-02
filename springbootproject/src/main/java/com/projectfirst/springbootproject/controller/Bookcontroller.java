package com.projectfirst.springbootproject.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projectfirst.springbootproject.model.Book;
import com.projectfirst.springbootproject.services.BookService;

@RestController
@RequestMapping("BankData")
public class Bookcontroller {
	
	@Autowired
	private BookService serviceBook;
	
	@GetMapping("/")
	public String message() {
		
		return "welcome to spring boot";
	}

	//get books
	@GetMapping("/books")
	public List<Book> getBooks(){
				
		return this.serviceBook.getBooks();		
	}
	
	@GetMapping("/books/{bookId}")
	public Book getBook(@PathVariable int bookId){

		return this.serviceBook.getBook(bookId);
		
	}
	
	@PostMapping("/books")
	public Book addBook(@RequestBody Book book) {
		
		return this.serviceBook.addBook(book);
	}
	@DeleteMapping("/books/{bookId}")
	public ResponseEntity<HttpStatus> deleteBook(@PathVariable int bookId) {
		try {
		 this.serviceBook.deleteBook(bookId);
		 return new ResponseEntity<HttpStatus>(HttpStatus.OK);
		}
		catch (Exception e) {
			return new ResponseEntity<HttpStatus>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
