package com.yyk.view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane; 
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.yyk.biz.UserBiz;
import com.yyk.biz.Impl.UserBizImpl;
import com.yyk.entity.User;

public class LoginView extends JFrame{
	private static final long serialVersionUID = 1L;
	private JPanel panel_main=null;
	private JPanel panel_left=null;
	private JPanel panel_right=null;
	private JLabel lb_user=null;
	private JLabel lb_password=null;
	private JLabel lb_type=null;
	private JButton btn_login=null;
	private JButton btn_register=null;
	private JTextField jtf_user=null;
	private JPasswordField jtf_password=null;
	private JComboBox<String> type=null;
	private JLabel lb_image=null;
	public static User cur_user;
	
	private UserBiz userbiz=null;

	public LoginView(){
		init();
		userbiz=new UserBizImpl();
		registerListener();
	}
	
	public void registerListener(){
		btn_register.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new RegisterView();
			}
		});
		
		btn_login.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String uname=jtf_user.getText().trim();
				String upass=new String(jtf_password.getPassword());
				int index=type.getSelectedIndex();
				if (uname.equals("")){
					JOptionPane.showMessageDialog(LoginView.this, "用户名不能为空");
					return;
				}else if (upass.equals("")){
					JOptionPane.showMessageDialog(LoginView.this, "密码不能为空");
					return;
				}
				User user=new User(uname,upass,index);
				user=userbiz.Login(user);
				if (user!=null){
					if (user.getType()==0){
					    cur_user=user;
					    new UserMainView();
						LoginView.this.dispose();
					}else if(user.getType()==1){
						new AdminMainView();
						LoginView.this.dispose();
					}
				}else{
					JOptionPane.showMessageDialog(LoginView.this, "用户名或密码出错");
					return;
				}
			}
		});
	}
    public void init(){
    	this.setSize(320, 220);
    	this.setLocationRelativeTo(null);
    	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	
    	panel_main=new JPanel(new GridLayout(1, 2));
    	panel_left=new JPanel();
    	panel_right=new JPanel(new GridLayout(4, 2, 20, 30));
    	
    	lb_user=new JLabel("用  户",JLabel.CENTER);
    	lb_password=new JLabel("密  码",JLabel.CENTER);
    	lb_type=new JLabel("类  型",JLabel.CENTER);
    	lb_image=new JLabel(new ImageIcon(
    			ClassLoader.getSystemResource("login.jpg")));
    	
    	btn_login=new JButton("登  录");
    	btn_register=new JButton("注  册");
    	
    	jtf_user=new JTextField(8);
    	jtf_password=new JPasswordField(8);
    	
    	type=new JComboBox<String>(new String[]{"普通用户","管理员"});
    	
    	panel_left.add(lb_image);
    	panel_right.add(lb_user);
    	panel_right.add(jtf_user);
    	panel_right.add(lb_password);
    	panel_right.add(jtf_password);
    	panel_right.add(lb_type);
    	panel_right.add(type);
    	panel_right.add(btn_login);
    	panel_right.add(btn_register);
    	
    	panel_main.add(panel_left);
    	panel_main.add(panel_right);
    	
    	this.getContentPane().add(panel_main);
    	
    	this.pack();
    	this.setVisible(true);
    }
}
