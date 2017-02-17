package com.yyk.biz.Impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.yyk.Dao.BookDao;
import com.yyk.Dao.RecordDao;
import com.yyk.Dao.Impl.BookDaoIpml;
import com.yyk.Dao.Impl.RecordDaoImpl;
import com.yyk.biz.BookBiz;
import com.yyk.entity.Book;
import com.yyk.entity.Record;

public class BookBizImpl implements BookBiz {
  private BookDao bookdao=null;
  private RecordDao recordao=null;
    public BookBizImpl() {
    	bookdao=new BookDaoIpml();
    	recordao=new RecordDaoImpl();
	}
	@Override
	public boolean addBook(Book book) {
		return bookdao.saveBook(book);
	}

	@Override
	public boolean delBook(int bid) {
		return bookdao.delBook(bid);
	}

	@Override
	public boolean modifyBook(Book book) {

		return bookdao.updateBook(book);
	}

	@Override
	public List<Book> queryAllBooks() {

		return bookdao.queryBooks();
	}

	@Override
	public List<Book> query_topTen_Books() {

		return bookdao.queryBooksByLimit(0, 10);
	}

	@Override
	public List<Book> queryBookByName(String bname) {

		return bookdao.queryBooksByName(bname);
	}

	@Override
	public Book queryBookById(int bid) {

		return bookdao.queryById(bid);
	}

	@Override
	public int lendBook(int bid, int uid) {
		Book book=null;
		book=bookdao.queryById(bid);
		if (book==null){
			return 0;   //没有这本书
		}else{
			if(book.getStatus()==0)  //不可借
				return 1;    
			else{
				book.setStatus(0); //更新书籍的状态为不可借
				book.setBcount(book.getBcount()+1);  //借出次数+1
				boolean flag1=bookdao.updateBook(book);
				Record record=new Record(uid,book.getId(),
						new SimpleDateFormat("yyyy-MM-dd").format(new Date()),null);
				boolean flag2=recordao.saveRecord(record);
				System.out.println(flag1+"   "+flag2);
				if (flag1 && flag2){
					return 2;   //借出成功 
				}else{
					return 3;  //借出失败
				}
			}
			
		}
	}

	@Override
	public int returnBook(int rid) {
        Record record=recordao.queryRecordById(rid);
        if (record==null){
        	return 0;  //记录不存在
        }else if (record.getReturntime()!=null){
        	return 1;  //已经归还
        }
        else{
        	record.setReturntime(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        	boolean flag1=recordao.updateRecord(record);
        	Book book=bookdao.queryById(record.getBid());
        	book.setStatus(1);
        	boolean flag2=bookdao.updateBook(book);
        	if (flag1 && flag2) return 2;  //归还成功
        	else return 3;  //归还失败
        }
	}

	@Override
	public List<Book> canLendBook() {
        List<Book> lbook=null;
        lbook=bookdao.queryBookByStatus(1);
		return lbook;
	}

	@Override
	public List<Book> hasLendedBook() {

		return bookdao.queryBookByStatus(0);
	}

}
