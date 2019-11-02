package cn.mh.view.friendShip;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import cn.mh.po.FU;
import cn.mh.po.Friendship;
import cn.mh.po.Groupchat;
import cn.mh.po.Groups;
import cn.mh.service.FriendshipService;
import cn.mh.service.GroupChatService;
import cn.mh.service.GroupService;
import cn.mh.service.impl.FriendShipServiceImpl;
import cn.mh.service.impl.GroupChatServiceImpl;
import cn.mh.service.impl.GroupServiceImpl;
import cn.mh.util.Config;

public class newGroup extends JFrame implements ActionListener {
	FriendshipService fService;
	GroupService groupService;
	JList<String> list;
	JButton bt1;
	JButton bt2, bt3;
	Vector<String> v1;
	JComboBox<String> jg;
	JLabel lb;
	TextField tx;

	public newGroup() {
		fService = new FriendShipServiceImpl();
		groupService = new GroupServiceImpl();
		this.setTitle("添加分组或群聊");
		this.setLayout(new BorderLayout());
		// 添加按钮
		bt1 = new JButton("确定");
		bt1.addActionListener(this);

		lb = new JLabel("名字");
		tx = new TextField(4);
		jg = new JComboBox<String>();
		jg.addItem("分组");
		jg.addItem("群聊");
		JPanel p1 = new JPanel();
		p1.add(lb);
		p1.add(tx);
		p1.add(jg);
		p1.add(bt1);
		this.add(p1, BorderLayout.SOUTH);

		this.setSize(500, 250);
		// 将窗口位置放置在屏幕中央
		Dimension dim1 = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) dim1.getWidth() - 200 - 50;
		this.setLocation(x, 0);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == bt1) {
			String name = tx.getText();
			String name1 = (String) jg.getSelectedItem();
			if("分组".equals(name1)){
				Groups g = new Groups();
				g.setName(name);
				g.setUserId(Config.id);
				groupService.add(g);
			}else{
				Groupchat gc = new Groupchat();
				gc.setName(name);
				gc.setUserid(Config.id);
				GroupChatService gs = new GroupChatServiceImpl();
				gs.add(gc);
			}
		}
		newGroup.this.dispose();
	}

	public static void main(String[] args) {
		new newGroup();
	}
}
