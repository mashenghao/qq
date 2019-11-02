package cn.mh.view.user;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import cn.mh.po.User;
import cn.mh.service.UserService;
import cn.mh.service.impl.UserServiceImpl;

public class RegUI extends JFrame implements ActionListener{
	private JLabel lab1,lab2,lab3,lab4,lab5,lab6,lab7,lab8;
	private JTextField text1,text2,text3,text4,text5,text6,text7,text8;
	private JButton but1;
	private Container container;
	//构造方法，生成窗体
	public RegUI (){
		container=this.getContentPane();
		container.setLayout(new FlowLayout());
		but1=new JButton("注册");
		lab1=new JLabel("账户");
		lab2=new JLabel("密码");
		lab3=new JLabel("确认密码");
		lab4=new JLabel("qq昵称");
		lab5=new JLabel("性别");
		lab6=new JLabel("年龄");
		lab7=new JLabel("个性签名");
		lab8=new JLabel("备注");
		text1=new JTextField(20);
		text2= new JTextField(20);
		text3=new JTextField(20);
		text4=new JTextField(20);
		text5= new JTextField(20);
		text6=new JTextField(20);
		text7=new JTextField(20);
		text8= new JTextField(20);
		container.add(lab1);
		container.add(text1);
		container.add(lab2);
		container.add(text2);
		container.add(lab3);
		container.add(text3);
		container.add(lab4);
		container.add(text4);
		container.add(lab5);
		container.add(text5);
		container.add(lab6);
		container.add(text6);
		container.add(lab7);
		container.add(text7);
		container.add(lab8);
		container.add(text8);
		container.add(but1);
		but1.addActionListener(this);
		this.setTitle("注册窗口");
		this.setSize(295, 400);
		this.setVisible(true);
		this.setResizable(false);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==but1){ //注册按钮
			UserService userService = new UserServiceImpl();
			String name =text1.getText();
			String pwd = text2.getText();
			String rePwd= text3.getText();
			if(null==name||"".equals(name)){
				JOptionPane.showMessageDialog(container, "账号不能为空", "提示", JOptionPane.INFORMATION_MESSAGE);
				return;
			}
			if(null==pwd||"".equals(pwd)){
				JOptionPane.showMessageDialog(container, "密码不能为空", "提示", JOptionPane.INFORMATION_MESSAGE);
				return;
			}
			User checkUser =userService.findByName(name);
			if (checkUser!=null){
				JOptionPane.showMessageDialog(container, "用户名重复", "提示", JOptionPane.INFORMATION_MESSAGE);
				return;
			}
			if (!pwd.equals(rePwd)){
				JOptionPane.showMessageDialog(container, "两次密码不一致", 
						"提示", JOptionPane.INFORMATION_MESSAGE);
			}else{
				User user = new User();
				user.setUsername(text1.getText());
				user.setPassword(text2.getText());
				user.setNickname(text4.getText());
				user.setSex(text5.getText());
				String age = text6.getText();
				if (null!=age&&!"".equals(age)){
					user.setAge(Integer.valueOf(age));
				}
				user.setSignature(text7.getText());
				user.setContent(text8.getText());
				userService.insertUser(user);
				JOptionPane.showMessageDialog(container, "新注册用户成功,进入登录页面", 
						"提示", JOptionPane.INFORMATION_MESSAGE);
				new LoginUI();
				this.dispose();
			}	
		}
	}
	
	public static void main(String[] args) {
		new RegUI();
	}
}
