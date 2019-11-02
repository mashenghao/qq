package cn.mh.view.friendShip;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import cn.mh.po.Groups;
import cn.mh.service.FriendshipService;
import cn.mh.service.GroupService;
import cn.mh.service.impl.FriendShipServiceImpl;
import cn.mh.service.impl.GroupServiceImpl;
import cn.mh.util.Config;

public class CheckFriend extends JFrame implements ActionListener {
	FriendshipService fService;
	GroupService groupService;
	JList<String> list;
	JButton bt1;
	JButton bt2, bt3;
	Vector<String> v1;
	JComboBox<String> jg;
	JLabel lb;
	TextField tx;
	public CheckFriend() {
		fService = new FriendShipServiceImpl();
		groupService = new GroupServiceImpl();
		this.setTitle("好友申请");
		this.setLayout(new BorderLayout());
		// 获取好友信息
		v1 = this.getFriendApply(Config.id);
		// 创建列表框，填充的数据为好友账号信息
		list = new JList<String>(v1);
		// 把列表框添加到具有滚动功能的容器中
		JScrollPane scollPane = new JScrollPane(list);
		// 把中间容器添加到窗口
		this.add(scollPane);

		// 添加按钮
		bt1 = new JButton("同意");
		bt1.addActionListener(this);
		bt2 = new JButton("拒绝");
		bt2.addActionListener(this);
		lb = new JLabel("备注");
		tx= new TextField(4);
		jg = new JComboBox<String>();
		List<Groups> gs = groupService.findAll(Config.id);
		for (Groups g : gs) {
			jg.addItem(g.getName());
		}

		JPanel p1 = new JPanel();
		p1.add(lb);
		p1.add(tx);
		p1.add(jg);
		p1.add(bt1);
		p1.add(bt2);
		this.add(p1, BorderLayout.SOUTH);

		this.setSize(500, 250);
		// 将窗口位置放置在屏幕中央
		Dimension dim1 = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) dim1.getWidth() - 200 - 50;
		this.setLocation(x, 0);
		this.setVisible(true);
	}

	public Vector<String> getFriendApply(Integer id) {
		List<Friendship> friendships = fService.findAllApply(Config.id);
		System.out.println(Config.id);
		Vector<String> v = new Vector<String>();
		for (Friendship f : friendships) {
			v.addElement(f.getFriendId()+"");
		}
		return v;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String groupName=null;
		String friendId = list.getSelectedValue();
		FU f = new FU();
		f.setUserId(Config.id);
		f.setFriendId(Integer.valueOf(friendId));
		String name = tx.getText();
		if (e.getSource() == bt1) {
			groupName= (String) jg.getSelectedItem();
			if("".equals(groupName)||groupName==null)
				return;
			f.setName(name);
			Integer gid = Config.getGroupId(groupName);
			f.setGroupId(gid);
			f.setState(1);
		}
		if (e.getSource() == bt2) {
			f.setState(2);
		}
		System.out.println(friendId+groupName);
		fService.UpdateFriend(f);
		CheckFriend.this.dispose();
	}

	public static void main(String[] args) {
		new CheckFriend();
	}
}
