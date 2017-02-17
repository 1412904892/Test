package com.yyk.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

import com.yyk.biz.RecordBiz;
import com.yyk.biz.Impl.RecordBizimpl;
import com.yyk.entity.Record2;

public class AdminRecordView extends JInternalFrame{
	private static final long serialVersionUID = 1L;
	
	private JPanel panel_table=null;
	private JTable table=null;
	private JPanel button_panel=null;
	private JLabel lb_type=null;
	private JComboBox<String> cb_type=null;
	private JTextField text_research=null;
	private JButton btn_research=null;
	private JButton btn_exit=null;
	
	private List<Record2> RecordList=null;
	private RecordInfoModel recordInfoModel;
	private RecordBiz recordbiz=null;
	
	public AdminRecordView(){
		init();
		recordbiz=new RecordBizimpl();
		registerListener();
	}
	
	public void registerListener(){
		btn_research.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int status=cb_type.getSelectedIndex();
				String content=text_research.getText().trim();
				if (content.equals("")){
					JOptionPane.showMessageDialog(AdminRecordView.this, "查找的用户名不能为空");
					return;
				}
				if (RecordList!=null){
					RecordList.clear();
				}
				if (status==0){
					RecordList=recordbiz.queryUserRecord(content);
				}else if (status==1){
					RecordList=recordbiz.queryBookRecord(content);
				}
				refreshModel(RecordList);
				if (RecordList.size()==0){
					JOptionPane.showMessageDialog(AdminRecordView.this, "查询内容为空");
					return;
				}
			}
		});
		
		btn_exit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int flag=JOptionPane.showConfirmDialog(AdminRecordView.this, "是否确认退出?",
						"退出信息", JOptionPane.YES_NO_OPTION);
				if (flag==JOptionPane.YES_OPTION){
					AdminRecordView.this.dispose();
				}
			}
		});
	}
	
	public void init(){
		this.setTitle("管理员借还记录操作");
		this.setSize(800,500);
		this.setIconifiable(true);
		this.setVisible(true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setClosable(true);
		
		panel_table=new JPanel(new BorderLayout());
		RecordList=new ArrayList<Record2>();
		table=new JTable();
		refreshModel(RecordList);
		panel_table.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(null,null),
				"书籍借还记录查询"));
		panel_table.add(new JScrollPane(table),BorderLayout.CENTER);
		
		button_panel=new JPanel(new GridLayout(7, 1, 0, 30));
		lb_type=new JLabel("查询条件");
		cb_type=new JComboBox<String>(new String[]{"指定用户借还记录","指定书籍借还 记录"});
		text_research=new JTextField(8);
		btn_research=new JButton("查询");
		btn_exit=new JButton("退出");
		button_panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(null,null),
				"查询条件"));
		button_panel.add(lb_type);
		button_panel.add(cb_type);
		button_panel.add(text_research);
		button_panel.add(btn_research);
		button_panel.add(new JLabel(""));
		button_panel.add(new JLabel(""));
		button_panel.add(btn_exit);
		
		this.add(panel_table,BorderLayout.CENTER);
		this.add(button_panel,BorderLayout.EAST);
		
	}
	
	private class RecordInfoModel implements TableModel{
		private List<Record2> recordlist=null;
		public RecordInfoModel(List<Record2> recordlist){
			this.recordlist=recordlist;
		}
		@Override
		public int getRowCount() {
			return recordlist.size();
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
				return "租赁时间";
			}else if (columnIndex==5){
				return "归还时间";
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
			Record2 record=recordlist.get(rowIndex);
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
	private void refreshModel(List<Record2> recordlist){
		recordInfoModel=new RecordInfoModel(recordlist);
		table.setModel(recordInfoModel);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
