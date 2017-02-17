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
	
	private JPanel panel_table=null;   //�ű��
	private JTable table=null;  //table ���
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
 						JOptionPane.showMessageDialog(UserQueryRent.this, "���ݿ��е��鼮Ϊ��");
 						return;
 					}
 				}else if (status==1){
 					BookList=bookbiz.canLendBook();
 					if (BookList.size()==0){
 						JOptionPane.showMessageDialog(UserQueryRent.this, "û�пɽ���鼮");
 						return;
 					}
 				}else if (status==2){
 					BookList=bookbiz.hasLendedBook();
 					if (BookList.size()==0){
 						JOptionPane.showMessageDialog(UserQueryRent.this, "û���ѽ���鼮");
 						return;
 					}
 				}else if (status==3){
 					BookList=bookbiz.query_topTen_Books();
 					if (BookList.size()==0){
 						JOptionPane.showMessageDialog(UserQueryRent.this, "���ݿ���û���鼮");
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
				int flag=JOptionPane.showInternalConfirmDialog(UserQueryRent.this, "�Ƿ�ȷ���˳���","�˳���Ϣ",
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
					JOptionPane.showMessageDialog(UserQueryRent.this, "���ݿ���û���Ȿ��");
					return;
				}else if (res==1){
					JOptionPane.showMessageDialog(UserQueryRent.this, "���鲻�ɽ�");
					return;
				}else if (res==2){
					JOptionPane.showMessageDialog(UserQueryRent.this, "����ɹ�");
					return;
				}else if (res==3){
					JOptionPane.showMessageDialog(UserQueryRent.this, "ϵͳ���ֹ���, ��ϵ����Ա");
					return;
				}
			}
		});
	}
	
	public void init(){
		  this.setTitle("DVD��ѯ");
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
				  "��ѯ��Ϣ"));
		  panel_table.add(new JScrollPane(table),BorderLayout.CENTER);
		  this.add(panel_table,BorderLayout.CENTER);
		  
		  button_panel=new JPanel(new GridLayout(7, 1, 0, 28));
		  btn_search=new JButton("��ѯ");
		  btn_rent=new JButton("����");
		  btn_rent.setEnabled(false);
		  btn_exit=new JButton("�˳�");
		  lb_type=new JLabel("��ѯ����");
		  cb_type=new JComboBox<String>(new String[]{"ȫ���鼮","�ɽ��鼮","�ѽ��鼮","�����鼮"});
		  button_panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(null, null),
				  "��ѯ����"));
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
				return "�鼮��ID";
			}else if (columnIndex==1){
				return "�鼮������";
			}else if (columnIndex==2){
				return "�鼮�������";
			}else if (columnIndex==3){
				return "�鼮״̬";
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
				return book.getStatus()==1?"�ɽ�":"���ɽ�";
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
