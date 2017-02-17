package com.yyk.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import com.yyk.biz.BookBiz;
import com.yyk.biz.Impl.BookBizImpl;
import com.yyk.entity.Book;

public class UserQueryRent extends JInternalFrame{
	private static final long serialVersionUID = 1L;
	
	private JPanel panel_table=null;   //放表格
	private JTable table=null;  //table 表格
	private JPanel button_panel=null;
	private JLabel lb_type=null;
	private JButton btn_search=null;
	private JButton btn_rent=null;
	private JButton btn_exit=null;
	private JComboBox<String> cb_type=null;
	private BookInfonModel infotableModel=null;
	private List<Book> BookList=null; 
	private BookBiz bookbiz=null;
	
	public UserQueryRent(){
		bookbiz=new BookBizImpl();
		init();
		registerListener();
	}
	
	public void registerListener(){
		btn_search.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
 				btn_rent.setEnabled(false);
 				int status=cb_type.getSelectedIndex();
 				if (status==0){
 					BookList=bookbiz.queryAllBooks();
 					if (BookList.size()==0){
 						JOptionPane.showMessageDialog(UserQueryRent.this, "数据库中的书籍为空");
 						return;
 					}
 				}else if (status==1){
 					BookList=bookbiz.canLendBook();
 					if (BookList.size()==0){
 						JOptionPane.showMessageDialog(UserQueryRent.this, "没有可借的书籍");
 						return;
 					}
 				}else if (status==2){
 					BookList=bookbiz.hasLendedBook();
 					if (BookList.size()==0){
 						JOptionPane.showMessageDialog(UserQueryRent.this, "没有已借的书籍");
 						return;
 					}
 				}else if (status==3){
 					BookList=bookbiz.query_topTen_Books();
 					if (BookList.size()==0){
 						JOptionPane.showMessageDialog(UserQueryRent.this, "数据库中没有书籍");
 						return;
 					}
 				}
 				refreshTable(BookList);
			}
		});
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e){
				if (table.getSelectedRow()!=-1){
					btn_rent.setEnabled(true);
				}
			}
		});
		
		btn_exit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int flag=JOptionPane.showInternalConfirmDialog(UserQueryRent.this, "是否确定退出？","退出信息",
						JOptionPane.YES_NO_OPTION);
				if (flag==JOptionPane.YES_OPTION){
					UserQueryRent.this.dispose();
				}
			}
		});
		
		btn_rent.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int row=table.getSelectedRow();
				String bid=table.getValueAt(row, 0).toString();
				int res=bookbiz.lendBook(Integer.parseInt(bid), LoginView.cur_user.getId());
				if (res==0){
					JOptionPane.showMessageDialog(UserQueryRent.this, "数据库中没有这本书");
					return;
				}else if (res==1){
					JOptionPane.showMessageDialog(UserQueryRent.this, "该书不可借");
					return;
				}else if (res==2){
					JOptionPane.showMessageDialog(UserQueryRent.this, "借出成功");
					return;
				}else if (res==3){
					JOptionPane.showMessageDialog(UserQueryRent.this, "系统出现故障, 联系管理员");
					return;
				}
			}
		});
	}
	
	public void init(){
		  this.setTitle("DVD查询");
		  this.setSize(800,500);
		  this.setIconifiable(true);
		  this.setVisible(true);
		  this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		  this.setClosable(true);
		  
		  BookList=new ArrayList<Book>();
		  table=new JTable();
		  refreshTable(BookList);
		  panel_table=new JPanel(new BorderLayout());
		  panel_table.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(null, null),
				  "查询信息"));
		  panel_table.add(new JScrollPane(table),BorderLayout.CENTER);
		  this.add(panel_table,BorderLayout.CENTER);
		  
		  button_panel=new JPanel(new GridLayout(7, 1, 0, 28));
		  btn_search=new JButton("查询");
		  btn_rent=new JButton("借书");
		  btn_rent.setEnabled(false);
		  btn_exit=new JButton("退出");
		  lb_type=new JLabel("查询类型");
		  cb_type=new JComboBox<String>(new String[]{"全部书籍","可借书籍","已借书籍","热门书籍"});
		  button_panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(null, null),
				  "查询条件"));
		  button_panel.add(lb_type);
		  button_panel.add(cb_type);
		  button_panel.add(btn_search);
		  button_panel.add(btn_rent);
		  button_panel.add(new JLabel());
		  button_panel.add(new JLabel());
		  button_panel.add(btn_exit);
		  this.add(button_panel,BorderLayout.EAST);		  
		  
	}
	private class BookInfonModel implements TableModel{

		private List<Book> booklist=null;
		public BookInfonModel(List<Book> booklist) {
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
				return "书籍的ID";
			}else if (columnIndex==1){
				return "书籍的名字";
			}else if (columnIndex==2){
				return "书籍借出次数";
			}else if (columnIndex==3){
				return "书籍状态";
			}else 
			return null;
		}

		@Override
		public Class<?> getColumnClass(int columnIndex) {

			return String.class;
		}

		@Override
		public boolean isCellEditable(int rowIndex, int columnIndex) {

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

			
		}

		@Override
		public void addTableModelListener(TableModelListener l) {

			
		}

		@Override
		public void removeTableModelListener(TableModelListener l) {

			
		}
		
	}
	private void refreshTable(List<Book> booklist){
		infotableModel=new BookInfonModel(booklist);
		table.setModel(infotableModel);
	}
}
