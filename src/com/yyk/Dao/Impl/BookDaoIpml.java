package com.yyk.Dao.Impl;

import java.util.ArrayList;
import java.util.List;

import com.yyk.Dao.BookDao;
import com.yyk.entity.Book;

public class BookDaoIpml extends BaseDao implements BookDao {

	@Override
	public boolean saveBook(Book book) {
		String sql="insert into books(bname,bcount,status) values(?,?,?)";
		List<Object> params=new ArrayList<Object>();
		
		params.add(book.getBname());
		params.add(book.getBcount());
		params.add(book.getStatus());
		return this.operUpdate(sql, params);
	}

	@Override
	public boolean delBook(int did) {
		String sql="delete from books where id=?";
		List<Object> params=new ArrayList<Object>();
		
		params.add(did);
		return this.operUpdate(sql, params);
	}

	@Override
	public boolean updateBook(Book book) {
		String sql="update books set bname=?,bcount=?,status=? where id=?";
		List<Object> params=new ArrayList<Object>();
		
	    params.add(book.getBname());
		params.add(book.getBcount());
		params.add(book.getStatus());
		params.add(book.getId());
		return this.operUpdate(sql, params);
	}

	@Override
	public List<Book> queryBooks() {
		String sql="select id,bname,bcount,status from books";
		List<Book> bList=null;
		
		try {
			bList=this.operquery(sql, null, Book.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bList;
	}

	@Override
	public List<Book> queryBooksByName(String bname) {
		String sql="select id,bname,bcount,status from books where bname=?";
		List<Object> params=new ArrayList<Object>();
		List<Book> bList=null;
		
		params.add(bname);
		try {
			bList=this.operquery(sql, params, Book.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bList;
	}

	@Override
	public List<Book> queryBooksByLimit(int index, int number) {
		String sql="select id,bname,bcount,status from books order by bcount desc limit "
				+index+"," +number;
		List<Book> bList=null;
		try {
			bList=this.operquery(sql, null, Book.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bList;
	}

	@Override
	public Book queryById(int did) { 
		String sql="select id,bname,bcount,status from books where id=?";
		List<Object> params=new ArrayList<Object>();
		List<Book> bList=null;
		
		params.add(did);
		try {
			bList=this.operquery(sql, params, Book.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (bList.size()>0) return bList.get(0);
		else return null;
	}

	@Override
	public List<Book> queryBookByStatus(int status) {
		String sql="select id,bname,bcount,status from books where status=?";
		List<Object> params=new ArrayList<Object>();
		List<Book> bList= null;
		
		params.add(status);
		try {
			bList=this.operquery(sql, params, Book.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
				
		return bList;
	}

}
