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



public class UserMainView extends JFrame{
	private static final long serialVersionUID = 1L;
	
	private JPanel panel_main=null;
	private JPanel wel_panel=null;
	private JPanel btn_panel=null;
	private JDesktopPane funcdesk=null;
	
	private JButton btn_rent_query=null;
	private JButton btn_record_query=null;
	private JButton btn_exit=null;
	private JLabel wel_label=null;
	private JLabel image_label=null;
	
	public UserMainView(){
		init();
		registerListener();
	}
	
	public void registerListener(){
		btn_exit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int flag=JOptionPane.showConfirmDialog(UserMainView.this, "�Ƿ�ȷ���˳�",
						"�˳���Ϣ", JOptionPane.YES_NO_OPTION);
				if (flag==JOptionPane.YES_OPTION){
					//System.out.println(LoginView.cur_user.getUname());
					UserMainView.this.dispose();
				}
			}
		});
		
		btn_rent_query.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				UserQueryRent qvd=new UserQueryRent();
				funcdesk.add(qvd);
				qvd.toFront();
			}
		});
		
		btn_record_query.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				UserQueryRecordView eve=new UserQueryRecordView();
				funcdesk.add(eve);
				eve.toFront();
			}
		});
	}
	
	public void init(){
		panel_main=new JPanel(new BorderLayout());
		wel_panel=new JPanel();
		btn_panel=new JPanel(new GridLayout(7, 1, 0, 35));
		
		btn_rent_query=new JButton("�鼮��ѯ����");
		btn_record_query=new JButton("�鼮�軹��¼��ѯ����");
		btn_exit=new JButton("�˳�ϵͳ");
		
		btn_panel.add(new JLabel());
		btn_panel.add(new JLabel());
		btn_panel.add(btn_rent_query);
		btn_panel.add(btn_record_query);
		btn_panel.add(btn_exit);
		btn_panel.add(new JLabel());
		btn_panel.add(new JLabel());
		btn_panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createRaisedBevelBorder(),
				"�˵���"));
		
		wel_label=new JLabel("��    ӭ    ʹ    ��    ͼ    ��    ��    ��    ϵ    ͳ");
		wel_label.setFont(new Font("����", Font.BOLD, 22));
		wel_label.setForeground(Color.blue);
		wel_panel.add(wel_label);
		
		funcdesk=new JDesktopPane();
		ImageIcon image=new ImageIcon("src/book.jpg");
		image_label=new JLabel(image);
		image_label.setBounds(0, 0, image.getIconWidth(), image.getIconHeight());
		funcdesk.add(image_label,-1000);
		
		panel_main.add(wel_panel,BorderLayout.NORTH);
		panel_main.add(btn_panel,BorderLayout.EAST);
		panel_main.add(funcdesk,BorderLayout.CENTER);
		
		this.setTitle("�鼮���޹���ϵͳ");
		this.getContentPane().add(panel_main);
		this.setSize(1300, 900);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		
		Thread thread=new Thread(new Runnable() {
			
			@Override
			public void run() {
				new Thread(new OtherThread()).start();
			}
		});
		thread.start();
	}
	
	private class OtherThread implements Runnable{

		@Override
		public void run() {
			while(true){
				for (int i=1300;i>=-750;i--){
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					wel_label.setLocation(i, 5);
				}
			}
		}
		
	}
}

