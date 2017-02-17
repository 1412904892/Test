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
import com.yyk.biz.RecordBiz;
import com.yyk.biz.Impl.BookBizImpl;
import com.yyk.biz.Impl.RecordBizimpl;
import com.yyk.entity.Record2;

public class UserQueryRecordView extends JInternalFrame{
	private static final long serialVersionUID = 1L;
	
	private JPanel panel_talbe=null;
	private JTable table=null;
	private JPanel button_panel=null;
	private JLabel lb_type=null;
	private JButton btn_reasearch=null;
	private JButton btn_return=null;
	private JButton btn_exit=null;
	private JComboBox<String> cb_type=null;
	private RecordBiz recordbiz=null;
	private BookBiz bookbiz=null;
	private RecordInfoModel recordInfoModel=null;
	private List<Record2> RecordList=null;
	
	public UserQueryRecordView(){
		init();
		recordbiz=new RecordBizimpl();
		bookbiz=new BookBizImpl();
		registerListener();
	}
	
	public void registerListener(){
		btn_exit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int flag=JOptionPane.showInternalConfirmDialog(UserQueryRecordView.this, "是否退出",
						"退出信息",JOptionPane.YES_NO_OPTION);
				if (flag==JOptionPane.YES_OPTION){
					UserQueryRecordView.this.dispose();
				}
			}
		});
		
		table.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				int row=table.getSelectedRow();
				if (row!=-1){
					btn_return.setEnabled(true);
			   }
		    }
		});
		
		btn_return.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int row=table.getSelectedRow();
				String rid=table.getValueAt(row, 0).toString();
				int flag=bookbiz.returnBook(Integer.parseInt(rid));
				if (flag==1){
					JOptionPane.showMessageDialog(UserQueryRecordView.this, "这本书你已经归还");
					return;
				}else if(flag==2){
					JOptionPane.showMessageDialog(UserQueryRecordView.this,"归还成功");
					return;
				}else if (flag==3){
					JOptionPane.showMessageDialog(UserQueryRecordView.this, "系统故障，联系管理员");
					return;
				}
			}
		});
		
		btn_reasearch.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				btn_return.setEnabled(false);
				int index=cb_type.getSelectedIndex();
				if (index==0){
					RecordList=recordbiz.queryUserRecord(LoginView.cur_user.getUname());
					if (RecordList.size()==0){
						JOptionPane.showMessageDialog(UserQueryRecordView.this, "您没有借还记录");
						return;
					}
				}else if (index==1){
					RecordList=recordbiz.queryHasReturnReocrd(LoginView.cur_user.getUname());
					if (RecordList.size()==0){
						JOptionPane.showMessageDialog(UserQueryRecordView.this, "您没有归还记录");
						return;
					}
				}else if (index==2){
					RecordList=recordbiz.queryNotReturnRecord(LoginView.cur_user.getUname());
					if (RecordList.size()==0){
						JOptionPane.showMessageDialog(UserQueryRecordView.this,"您没有未归还记录");
					}
				}
				refreshTable(RecordList);
			}
		});
	}
	
	public void init(){
		this.setTitle("借还记录查询");
		this.setSize(800,500);
		this.setIconifiable(true);
		this.setVisible(true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setClosable(true);
		
		table=new JTable();
		RecordList=new ArrayList<Record2>();
		refreshTable(RecordList);
		panel_talbe=new JPanel(new BorderLayout());
		panel_talbe.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(null,null),
				"本人借还记录"));
		panel_talbe.add(new JScrollPane(table),BorderLayout.CENTER);
		this.add(panel_talbe,BorderLayout.CENTER);
		
		button_panel=new JPanel(new GridLayout(7, 1,0,28));
		button_panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(null,null),
				"查询条件"));
		lb_type=new JLabel("查询类型");
		cb_type=new JComboBox<String>(new String[]{"全部借还记录","已还记录","未还记录"});
		btn_reasearch=new JButton("查询");
		btn_return=new JButton("还书");
		btn_return.setEnabled(false);
		btn_exit=new JButton("退出");
		button_panel.add(lb_type);
		button_panel.add(cb_type);
		button_panel.add(btn_reasearch);
		button_panel.add(btn_return);
		button_panel.add(new JLabel());
		button_panel.add(new JLabel());
		button_panel.add(btn_exit);
		this.add(button_panel, BorderLayout.EAST);
		
	}
	private class RecordInfoModel implements TableModel{
		private List<Record2> recordList=null;
		public RecordInfoModel(List<Record2> recordList) {
			this.recordList=recordList;
		}
		@Override
		public int getRowCount() {
           
			return recordList.size();
		}

		@Override
		public int getColumnCount() {

			return 6;
		}

		@Override
		public String getColumnName(int columnIndex) {
			if (columnIndex==0){
				return "记录ID";
			}else if (columnIndex==1){
				return "书籍ID";
			}else if (columnIndex==2){
				return "用户名";
			}else if (columnIndex==3){
				return "书籍名字";
			}else if (columnIndex==4){
				return "租借时间";
			}else if (columnIndex==5){
				return "归还时间";
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
			Record2 record=recordList.get(rowIndex);
			if (columnIndex==0){
				return record.getId();
			}else if (columnIndex==1){
				return record.getBid();
			}else if (columnIndex==2){
				return record.getUname();
			}else if (columnIndex==3){
				return record.getBname();
			}else if (columnIndex==4){
				return record.getLendtime();
			}else if (columnIndex==5){
				return record.getReturntime();
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
	private void refreshTable(List<Record2> recordList){
		recordInfoModel=new RecordInfoModel(recordList);
		table.setModel(recordInfoModel);
	}
}
