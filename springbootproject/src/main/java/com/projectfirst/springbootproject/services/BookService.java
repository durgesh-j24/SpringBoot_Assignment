package com.projectfirst.springbootproject.services;

import java.util.List;

import com.projectfirst.springbootproject.model.Book;

public interface BookService {
	public List<Book> getBooks();
	public Book getBook(int bookId);
	public Book addBook(Book book);
	public void deleteBook(int bookId);
}
