package cn.mh.view.user;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import cn.mh.po.Message;
import cn.mh.po.User;
import cn.mh.service.UserService;
import cn.mh.service.impl.UserServiceImpl;
import cn.mh.util.Config;
import cn.mh.util.ReceThread;
import cn.mh.view.friendShip.FriendListUI;

public class LoginUI extends JFrame implements ActionListener {
	
	public static Socket s ;
	private JLabel lab1, lab2;
	private JTextField text1, text2;
	private JButton but1, but2;
	private Container container;

	// 构造方法，生成窗体
	public LoginUI() {
		container = this.getContentPane();
		container.setLayout(new FlowLayout());
		lab1 = new JLabel("账户");
		lab2 = new JLabel("密码");
		text1 = new JTextField(20);
		text2 = new JTextField(20);
		text1.setText("2018001");
		text2.setText("000");
		but1 = new JButton("登录");
		but2 = new JButton("注册");
		container.add(lab1);
		container.add(text1);
		container.add(lab2);
		container.add(text2);
		container.add(but1);
		container.add(but2);

		this.setTitle("登录窗口");
		this.setSize(280, 250);
		this.setVisible(true);

		text1.addActionListener((e)->{
			System.out.println("123");
		});
		text2.addActionListener(this);
		but1.addActionListener(this);
		but2.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == but1) { // 登录窗口
			String name = text1.getText();
			String pwd = text2.getText();
			if(null==name||"".equals(name)){
				JOptionPane.showMessageDialog(container, "账号不能为空", "提示", JOptionPane.INFORMATION_MESSAGE);
				return;
			}
			if(null==pwd||"".equals(pwd)){
				JOptionPane.showMessageDialog(container, "密码不能为空", "提示", JOptionPane.INFORMATION_MESSAGE);
				return;
			}
			UserService userService = new UserServiceImpl();
			User user = userService.findByNameAndPass(name, pwd);
			if (user != null) {
				try {
					Config.id=user.getId();
					Config.username=user.getUsername();
					System.out.println(Config.username);
					Config.nickname=user.getNickname();
					s = new Socket("localhost", 8000);
					System.out.println(s);
					ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream());
					Message ms = new Message(Message.LOGIN,Config.id+"",Config.id+"用户登录",null);
					out.writeObject(ms);
					out.flush();
					new Thread(new ReceThread(s)).start();
					new FriendListUI();
					this.dispose();
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(container, "登录失败", "提示", JOptionPane.INFORMATION_MESSAGE);
					return ;
				}
				
			} else {
				JOptionPane.showMessageDialog(container, "账号或者密码错误，核实后登录", "提示", JOptionPane.INFORMATION_MESSAGE);
			}  
		}
		if (e.getSource() == but2) {
			new RegUI();
			this.dispose();
		}
	}
	//
	public static void main(String[] args) {
		new  LoginUI();
	}
}

