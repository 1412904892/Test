package com.yyk.view;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.yyk.biz.UserBiz;
import com.yyk.biz.Impl.UserBizImpl;
import com.yyk.entity.User;

public class RegisterView extends JFrame{
	private static final long serialVersionUID = 1L;
	
	JPanel panel_main=null;
	JPanel panel1=null;
	JPanel panel2=null;
	JPanel panel3=null;
	JPanel panel4=null;
	JPanel panel5=null;
	JLabel lb_uname=null;
	JTextField jtf_uname=null;
	JLabel lb_init_password=null;
	JPasswordField init_pass=null;
	JLabel lb_confirm=null;
	JPasswordField confirm_pass=null;
	JButton btn_confirm=null;
	JButton btn_exit=null;
	private UserBiz userbiz=null;
	
	public RegisterView(){
		init();
		userbiz=new UserBizImpl();
		registerListener();
	}
    public void registerListener(){
    	
    	btn_exit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				RegisterView.this.dispose();
			}
		});
    	
    	btn_confirm.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String uname=jtf_uname.getText().trim();
				String init_upass=new String(init_pass.getPassword());
				String confirm_upass=new String(confirm_pass.getPassword());
				if (uname.equals("")){
					JOptionPane.showMessageDialog(RegisterView.this, "用户名不能为空");
					return;
				}else if(init_upass.equals("")){
					JOptionPane.showMessageDialog(RegisterView.this, "初始密码不能为空");
					return;
				}else if (confirm_upass.equals("")){
					JOptionPane.showMessageDialog(RegisterView.this, "确认密码不能为空");
					return;
				}else if(!init_upass.equals(confirm_upass)){
					JOptionPane.showMessageDialog(RegisterView.this, "密码不一致");
					return;
				}
				User user=new User(uname,init_upass,0);
				int flag=userbiz.register(user);
				if (flag==1){
					JOptionPane.showMessageDialog(RegisterView.this, "用户已经存在");
					return;
				}else if (flag==2){
					JOptionPane.showMessageDialog(RegisterView.this, "注册成功");
					return;
				}else if (flag==3){
					JOptionPane.showMessageDialog(RegisterView.this, "注册失败");
					return;
				}
			}
		});
    }
	public void init(){
		
		this.setSize(320, 220);
    	this.setLocationRelativeTo(null);
    	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	
		panel_main=new JPanel(new GridLayout(5, 1));
		panel1=new JPanel();
		panel2=new JPanel();
		panel3=new JPanel();
		panel4=new JPanel();
		panel5=new JPanel();
		
		lb_uname=new JLabel("用户名 ");
		lb_uname.setFont(new Font("微软雅黑", Font.BOLD, 14));
		lb_init_password=new JLabel("初始密码");
		lb_init_password.setFont(new Font("微软雅黑", Font.BOLD, 14));
		lb_confirm=new JLabel("确认密码");
		lb_confirm.setFont(new Font("微软雅黑", Font.BOLD, 14));
		
		jtf_uname=new JTextField(8);
		init_pass=new JPasswordField(8);
		confirm_pass=new JPasswordField(8);
		
		btn_confirm=new JButton("确 认");
		btn_exit=new JButton("退 出");
		
		panel2.add(lb_uname);
		panel2.add(jtf_uname);
		
		panel3.add(lb_init_password);
		panel3.add(init_pass);
		
		panel4.add(lb_confirm);
		panel4.add(confirm_pass);
		
		panel5.add(btn_confirm);
		panel5.add(btn_exit);
		
		panel_main.add(panel1);
		panel_main.add(panel2);
		panel_main.add(panel3);
		panel_main.add(panel4);
		panel_main.add(panel5);
		
		this.getContentPane().add(panel_main);
    	
    	this.pack();
    	this.setVisible(true);
		
	}
}
