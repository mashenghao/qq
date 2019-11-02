package cn.mh.view.friendShip;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import cn.mh.po.User;
import cn.mh.service.UserService;
import cn.mh.service.impl.UserServiceImpl;

public class FriendAddUI extends JFrame implements ActionListener {
	FriendListUI listFrame;
	JLabel lb1, lb2;
	JPanel p1, p2;
	JTextField tf1;
	JButton bt1;
	JTextArea ta1;
	JButton bt2;

	public FriendAddUI(FriendListUI listFrame) {
		this.listFrame = listFrame;
		this.setTitle("查找用户");
		p1 = new JPanel();
		p1.setLayout(new FlowLayout(FlowLayout.LEFT));

		lb1 = new JLabel("账号:");
		tf1 = new JTextField(12);
		bt1 = new JButton("查找");
		bt1.addActionListener(this);

		p1.add(lb1);
		p1.add(tf1);
		p1.add(bt1);
		this.add(p1, BorderLayout.NORTH);

		ta1 = new JTextArea(10, 6);
		ta1.setText("用户信息：");
		this.add(ta1);

		p2 = new JPanel();
		bt2 = new JButton("+好友");
		bt2.addActionListener(this);
		p2.add(bt2);
		this.add(p2, BorderLayout.SOUTH);
		this.setSize(400, 260);
		this.setLocation(500, 300);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		UserService userService = new UserServiceImpl();
		if (e.getSource() == bt1) {

			String name = tf1.getText();
			User user = userService.findByName(name);
			if (user != null) {
				ta1.setText("账号：" + user.getUsername() + "\n" + "昵称：" + user.getNickname() + "\n" + "性别："
						+ user.getSex() + "\n" + "年龄：" + user.getAge() + "\n" + "个性签名： " + user.getSignature() + "\n");
			}else{
				ta1.setText("没有这个人");
			}
		}
		if (e.getSource() == bt2) {
			String friendId = tf1.getText().trim();
			new AddUI(friendId);
		}
	}

	public static void main(String[] args) {
		new FriendAddUI(new FriendListUI());
	}

}
