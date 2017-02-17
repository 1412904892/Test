package com.yyk.biz;

import java.util.List;

import com.yyk.entity.Book;

public interface BookBiz {
	public boolean addBook(Book book);  //����鼮
	
	public boolean delBook(int bid);    //ɾ���鼮
	
	public boolean modifyBook(Book book); //�޸��鼮
	
	public List<Book> queryAllBooks();   //��ѯ���е��鼮
	
	public List<Book> query_topTen_Books(); //��ѯ����ǰʮ���鼮
	
	public List<Book> queryBookByName(String bname); //ͨ�����������鼮
	
	public Book queryBookById(int bid);  //ͨ��Id���鼮
	
	public int lendBook(int bid,int uid);  //����
	
	public int returnBook(int rid);  //����
	
	public List<Book> canLendBook();  //�ɽ���鼮
	
	public List<Book> hasLendedBook(); //�ѽ���鼮
}
