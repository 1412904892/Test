package com.yyk.Dao;

import java.util.List;

import com.yyk.entity.Book;

public interface BookDao {
	public boolean saveBook(Book book);
	public boolean delBook(int did);
	public boolean updateBook(Book book); 
	public List<Book> queryBooks();
	public List<Book> queryBooksByName(String bname);
	public List<Book> queryBooksByLimit(int index,int number);
	public Book queryById(int did);
	public List<Book> queryBookByStatus(int status); 
}
