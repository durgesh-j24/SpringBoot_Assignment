package com.projectfirst.springbootproject;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.projectfirst.springbootproject.controller.Bookcontroller;
import com.projectfirst.springbootproject.model.Book;
import com.projectfirst.springbootproject.repository.BookRepository;
import com.projectfirst.springbootproject.services.BookService;
import com.projectfirst.springbootproject.services.BookServiceImpl;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringRunner.class)
@WebMvcTest(value = Bookcontroller.class)
public class BookTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private BookService bookservice;
	
	@MockBean
	private BookRepository repo;
	
	private BookServiceImpl bsi=new BookServiceImpl(); 
	
	@Test
	public void testGetBooks() throws Exception {
		List<Book> list=new ArrayList<>();
		list.add(new Book(1,"BTD",200));
		list.add(new Book(2,"shala",100));
		
		Mockito.when(bookservice.getBooks()).thenReturn(list);
		
		String url="/BankData/books";
		
		mockMvc.perform(get(url)).andExpect(status().isOk());
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				url).accept(
				MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		String expectedJson = this.mapToJson(list);
		String outputInJson = result.getResponse().getContentAsString();
		assertThat(outputInJson).isEqualTo(expectedJson);
	}

	@Test
	public void testGetBook() throws Exception {
		
		Book b=new Book(1,"BTD",200);
		
		Mockito.when(bookservice.getBook(Mockito.anyInt())).thenReturn(b);
		
		String url="/BankData/books/1";
		
		mockMvc.perform(get(url)).andExpect(status().isOk());
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				url).accept(
				MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		String expectedJson = this.mapToJson(b);
		String outputInJson = result.getResponse().getContentAsString();
		assertThat(outputInJson).isEqualTo(expectedJson);
	}
	
	@Test
	public void testAddBook() throws Exception {
		Book b=new Book(3,"shiva",200);
		
		String inputInJson = this.mapToJson(b);
	
		String url="/BankData/books";
		Mockito.when(bookservice.addBook(Mockito.any(Book.class))).thenReturn(b);
		
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post(url)
				.accept(MediaType.APPLICATION_JSON).content(inputInJson)
				.contentType(MediaType.APPLICATION_JSON);
				
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		
		String outputInJson = response.getContentAsString();
		assertThat(outputInJson).isEqualTo(inputInJson);
		assertEquals(HttpStatus.OK.value(), response.getStatus());
		
	}
	
	@Test
	public void testDeleteBook() throws Exception {
			
		String url="/BankData/books/3";
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete(url)).andReturn();
		
		int status = mvcResult.getResponse().getStatus();
		
		assertEquals(200, status);
		//String content = mvcResult.getResponse().getContentAsString();
		//assertEquals(content,"deleted successsfully");

	}
	
	private String mapToJson(Object object) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(object);
	}
	

}
