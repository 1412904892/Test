package com.yyk.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import com.yyk.biz.BookBiz;
import com.yyk.biz.Impl.BookBizImpl;
import com.yyk.check.IsNumber;
import com.yyk.entity.Book;

public class AdminOperatorView extends JInternalFrame{
	private static final long serialVersionUID = 1L;
    
	private JPanel panel_table=null;
	private JTable table=null;
	private JPanel button_panel=null;
	private JPanel content_panel=null;
	private JLabel lb_type=null;
	private JComboBox<String> cb_type=null;
	private JTextField text_research=null;
	private JButton btn_research=null;
	private JButton btn_addBook=null;
	private JButton btn_updateBook=null;
	private JButton btn_deleteBook=null;
	private JButton btn_exit=null;
	private JLabel lb_BookName=null;
	private JTextField text_BookName=null;
	private JLabel lb_count=null;
	private JTextField text_count=null;
	private JLabel lb_status=null;
	private JComboBox<String> cb_status=null;
	
	private BookInfoModel bookinfomodel=null;
	private List<Book> BookList=null;
	private BookBiz bookbiz=null;
	public AdminOperatorView(){
		init();
		bookbiz=new BookBizImpl();
		registerListener();
	}
	
	public void registerListener(){
		btn_research.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int index=cb_type.getSelectedIndex();
				String text=text_research.getText().trim();
				if (index!=0 && text.equals("")){
					JOptionPane.showMessageDialog(AdminOperatorView.this, "查询内容不能为空");
					return;
				}
				if (BookList!=null){
					BookList.clear();
				}
				if (index==0){
					BookList=bookbiz.queryAllBooks();
				}else if (index==2){
					if (!IsNumber.isnumber(text)){
						JOptionPane.showMessageDialog(AdminOperatorView.this, "书籍编号只能为数字");
						return;
					}else{
						Book book=bookbiz.queryBookById(Integer.parseInt(text));
						if (book!=null){
							BookList.add(book);
						}
					}
				}else if (index==1){
					BookList=bookbiz.queryBookByName(text);
				}
				refreshTable(BookList);
				btn_updateBook.setEnabled(false);
				btn_deleteBook.setEnabled(false);
				if (BookList.size()==0){
					JOptionPane.showMessageDialog(AdminOperatorView.this, "没有书籍记录");
					return;
				}
			}
		});
		
		btn_addBook.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String bookname=text_BookName.getText().trim();
				String bookcount=text_count.getText().trim();
				int status=1-cb_status.getSelectedIndex();
				if (bookname.equals("")){
					JOptionPane.showMessageDialog(AdminOperatorView.this, "书名不能为空");
					return;
				}else if (bookcount.equals("")){
					JOptionPane.showMessageDialog(AdminOperatorView.this, "借出次数不能为空");
					return;
				}
				if (!IsNumber.isnumber(bookcount)){
					JOptionPane.showMessageDialog(AdminOperatorView.this, "借出次数只能为数字");
					return;
				}
				int flag=JOptionPane.showConfirmDialog(AdminOperatorView.this, "是否确认添加？",
						"确认信息",JOptionPane.YES_NO_OPTION);
				if (flag==JOptionPane.YES_OPTION){
					boolean flag2=bookbiz.addBook(new Book(bookname,
							new Integer(bookcount),status));
					if (flag2==true){
						JOptionPane.showMessageDialog(AdminOperatorView.this, "添加成功");
						return;
					}else {
						JOptionPane.showMessageDialog(AdminOperatorView.this, "添加失败");
						return;
					}
				}
			}
		});
		
		btn_updateBook.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String bookname=text_BookName.getText().trim();
				String bookcount=text_count.getText().trim();
				int status=cb_status.getSelectedIndex();
				if (bookname.equals("")){
					JOptionPane.showMessageDialog(AdminOperatorView.this, "书名不能为空");
					return;
				}else if (bookcount.equals("")){
					JOptionPane.showMessageDialog(AdminOperatorView.this, "借出次数不能为空");
					return;
				}
				if (!IsNumber.isnumber(bookcount)){
					JOptionPane.showMessageDialog(AdminOperatorView.this, "次数只能为数字");
					return;
				}
				int flag=JOptionPane.showConfirmDialog(AdminOperatorView.this, "是否确认更新？", 
						"更新信息", JOptionPane.YES_NO_OPTION);
				if (flag==JOptionPane.YES_OPTION){
				
					int row=table.getSelectedRow();
					if (table.getValueAt(row, 3)=="不可借"){
						JOptionPane.showMessageDialog(AdminOperatorView.this, "该书籍已借出，不能更新");
						return;
					}
					boolean flag2=bookbiz.modifyBook(new Book((Integer) table.getValueAt(row, 0),
							bookname,new Integer(bookcount),status));
					if (flag2==true){
						JOptionPane.showMessageDialog(AdminOperatorView.this, "更新成功");
						return;
					}else{
						JOptionPane.showMessageDialog(AdminOperatorView.this, "更新失败");
						return;
					}
				}
			}
		});
		
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e){
				if (table.getSelectedRow()!=-1){
					btn_deleteBook.setEnabled(true);
					btn_updateBook.setEnabled(true);
					text_research.setEditable(true);
					
				}
				int row=table.getSelectedRow();
				String bookname=table.getValueAt(row, 1).toString();
				String bookcount=table.getValueAt(row, 2).toString();
				String status=table.getValueAt(row, 3).toString();
				
				text_BookName.setText(bookname);
				text_count.setText(bookcount);
				cb_status.setSelectedItem(status);
			}
		});
		
		btn_deleteBook.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int row=table.getSelectedRow();
				int bookid=new Integer(table.getValueAt(row, 0).toString());
				int flag=JOptionPane.showConfirmDialog(AdminOperatorView.this, "是否确认删除改本书籍", 
						"删除信息", JOptionPane.YES_NO_OPTION);
				if (flag==JOptionPane.YES_OPTION){
					boolean flag2=bookbiz.delBook(bookid);
					if (flag2){
						JOptionPane.showMessageDialog(AdminOperatorView.this, "删除成功");
						return;
					}else{
						JOptionPane.showMessageDialog(AdminOperatorView.this, "删除失败");
						return;
					}
				}
			}
		});
		
		cb_type.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				text_research.setText("");
				if (cb_type.getSelectedIndex()==0){
					text_research.setEditable(false);
				}else{
					text_research.setEditable(true);
				}
			}
		});
		
		btn_exit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int flag=JOptionPane.showConfirmDialog(AdminOperatorView.this, "是否确认退出?",
						"退出信息",JOptionPane.YES_NO_OPTION);
				if (flag==JOptionPane.YES_OPTION){
					AdminOperatorView.this.dispose();
				}
			}
		});
	}
	
	public void init(){
		this.setTitle("管理员书籍操作");
		this.setSize(800,500);
		this.setIconifiable(true);
		this.setVisible(true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setClosable(true);
		
		BookList=new ArrayList<Book>();
		panel_table=new JPanel(new BorderLayout());
		table=new JTable();
		refreshTable(BookList);
		panel_table.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(null, null),
				"书籍记录查询"));
		panel_table.add(new JScrollPane(table),BorderLayout.CENTER);
		this.add(panel_table,BorderLayout.CENTER);
		
		button_panel=new JPanel(new GridLayout(9, 1, 0, 30));
		lb_type=new JLabel("查询类型");
		cb_type=new JComboBox<String>(new String[]{"所有书籍","书籍名字","书籍编号"});
		text_research=new JTextField();
		text_research.setEditable(false);
		btn_research=new JButton("查询");
		btn_addBook=new JButton("添加书籍");
		btn_updateBook=new JButton("更新书籍");
		btn_deleteBook=new JButton("删除书籍");
		btn_updateBook.setEnabled(false);
		btn_deleteBook.setEnabled(false);
		btn_exit=new JButton("退出");
		button_panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(null,null),
				"查询操作"));
		button_panel.add(lb_type);
		button_panel.add(cb_type);
		button_panel.add(text_research);
		button_panel.add(btn_research);
		button_panel.add(btn_addBook);
		button_panel.add(btn_updateBook);
		button_panel.add(btn_deleteBook);
		button_panel.add(new JLabel());
		button_panel.add(btn_exit);
		
		content_panel=new JPanel(new GridLayout(1, 6,30,0));
		lb_BookName=new JLabel("书名");
		text_BookName=new JTextField(8);
		lb_count=new JLabel("借出次数");
		text_count=new JTextField(8);
		lb_status=new JLabel("状态");
		cb_status=new JComboBox<String>(new String[]{"可借","不可借"});
		content_panel.add(lb_BookName);
		content_panel.add(text_BookName);
		content_panel.add(lb_count);
		content_panel.add(text_count);
		content_panel.add(lb_status);
		content_panel.add(cb_status);

		this.add(panel_table,BorderLayout.CENTER);
		this.add(button_panel, BorderLayout.EAST);
		this.add(content_panel,BorderLayout.SOUTH);
		//this.add(content_panel,BorderLayout.SOUTH);
	}
	
	private class BookInfoModel implements TableModel{
		private List<Book> booklist=null;
		public BookInfoModel(List<Book> booklist) {
			this.booklist=booklist;
		}
		@Override
		public int getRowCount() {
			return booklist.size();
		}

		@Override
		public int getColumnCount() {
			
			return 4;
		}

		@Override
		public String getColumnName(int columnIndex) {
			if (columnIndex==0){
				return "书籍ID";
			}else if (columnIndex==1){
				return "书名";
			}else if (columnIndex==2){
				return "书籍借出次数";
			}else if (columnIndex==3){
				return "书籍状态";
			}else 
			return null;
		}

		@Override
		public Class<?> getColumnClass(int columnIndex) {
			// TODO Auto-generated method stub
			return String.class;
		}

		@Override
		public boolean isCellEditable(int rowIndex, int columnIndex) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			Book book=booklist.get(rowIndex);
			if (columnIndex==0){
				return book.getId();
			}else if (columnIndex==1){
				return book.getBname();
			}else if (columnIndex==2){
				return book.getBcount();
			}else if (columnIndex==3){
				return book.getStatus()==1?"可借":"不可借";
			}else 
			return null;
		}

		@Override
		public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void addTableModelListener(TableModelListener l) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void removeTableModelListener(TableModelListener l) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	
	private void refreshTable(List<Book> booklist){
		bookinfomodel=new BookInfoModel(booklist);
		table.setModel(bookinfomodel);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
