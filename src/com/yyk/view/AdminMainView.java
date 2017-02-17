package com.yyk.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class AdminMainView extends JFrame{
	private static final long serialVersionUID = 1L;
	
	private JPanel panel_main=null;
	private JPanel button_panel=null;
	private JPanel wel_panel=null;
	private JDesktopPane funcdeskpane=null;
	
	private JLabel wel_label=null;
	private JButton btn_book=null;
	private JButton btn_record=null;
	private JButton btn_exit=null;
	private JLabel image_label=null;
	
	public AdminMainView(){
		init();
		registerListener();
	}
	public void registerListener(){
		btn_book.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				AdminOperatorView aov=new AdminOperatorView();
				funcdeskpane.add(aov);
				aov.toFront();
			}
		});
		
		btn_exit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int flag=JOptionPane.showConfirmDialog(AdminMainView.this, "是否确认退出","退出信息", 
						JOptionPane.YES_NO_OPTION);
				if (flag==JOptionPane.YES_OPTION){
					AdminMainView.this.dispose();
				}
			}
		});
		
		btn_record.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				AdminRecordView arv=new AdminRecordView();
				funcdeskpane.add(arv);
				arv.toFront();
			}
		});
	}
	public void init(){
		panel_main=new JPanel(new BorderLayout());
		
		wel_panel=new JPanel();
		wel_label=new JLabel("欢    迎    使    用    图    书    租    赁    系    统");
		wel_label.setFont(new Font("宋体", Font.BOLD, 28));
		wel_label.setForeground(Color.blue);
		wel_panel.add(wel_label);
		
		button_panel=new JPanel(new GridLayout(7, 1, 0, 30));
		btn_book=new JButton("书籍管理");
		btn_record=new JButton("书籍借还记录查询");
		btn_exit=new JButton("退出系统");
		button_panel.add(new JLabel());
		button_panel.add(new JLabel());
		button_panel.add(btn_book);
		button_panel.add(btn_record);
		button_panel.add(btn_exit);
		button_panel.add(new JLabel());
		button_panel.add(new JLabel());
		button_panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(null, null),
				"菜单栏"));
		
		funcdeskpane=new JDesktopPane();
		ImageIcon image=new ImageIcon("src/book.jpg");
		image_label=new JLabel(image);
		image_label.setBounds(0, 0, image.getIconWidth(), image.getIconHeight());
		funcdeskpane.add(image_label, -1000);
		
		panel_main.add(wel_panel,BorderLayout.NORTH);
		panel_main.add(button_panel,BorderLayout.EAST);
		panel_main.add(funcdeskpane,BorderLayout.CENTER);
		
		this.setTitle("书籍租赁管理系统");
		this.getContentPane().add(panel_main);
		this.setSize(1300, 900);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
	}
}
