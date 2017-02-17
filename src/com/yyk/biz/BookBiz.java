package com.yyk.biz;

import java.util.List;

import com.yyk.entity.Book;

public interface BookBiz {
	public boolean addBook(Book book);  //添加书籍
	
	public boolean delBook(int bid);    //删除书籍
	
	public boolean modifyBook(Book book); //修改书籍
	
	public List<Book> queryAllBooks();   //查询所有的书籍
	
	public List<Book> query_topTen_Books(); //查询排名前十的书籍
	
	public List<Book> queryBookByName(String bname); //通过书名查找书籍
	
	public Book queryBookById(int bid);  //通过Id找书籍
	
	public int lendBook(int bid,int uid);  //借书
	
	public int returnBook(int rid);  //还书
	
	public List<Book> canLendBook();  //可借的书籍
	
	public List<Book> hasLendedBook(); //已借的书籍
}
