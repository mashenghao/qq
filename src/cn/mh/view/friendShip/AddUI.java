package cn.mh.view.friendShip;

import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import cn.mh.po.Friendship;
import cn.mh.po.Groups;
import cn.mh.service.FriendshipService;
import cn.mh.service.GroupService;
import cn.mh.service.impl.FriendShipServiceImpl;
import cn.mh.service.impl.GroupServiceImpl;
import cn.mh.util.Config;


public class AddUI extends JFrame implements ActionListener{
	
	JComboBox<String> jg;
	private JLabel lab1,lab2,lab3;
	private JTextField text1,text2;
	private JButton but1;
	private Container container;
	private GroupService groupService = new GroupServiceImpl();
	//构造方法，生成窗体
	public AddUI (String friendId){
		container=this.getContentPane();
		container.setLayout(new FlowLayout());
		but1=new JButton("发送");
		lab1=new JLabel("账户");
		lab2=new JLabel("备注");
		lab3=new JLabel("分组");
		
		text1=new JTextField(20);
		text2= new JTextField(20);
		
		jg=new JComboBox<String>();
		List<Groups> gs =groupService.findAll(Config.id);
		for(Groups g : gs){
			jg.addItem(g.getName());
		}
		container.add(lab1);
		container.add(text1);
		container.add(lab2);
		container.add(text2);
		container.add(lab3);
		container.add(jg);
		container.add(but1);
			
		but1.addActionListener(this);
		this.setTitle("好友添加窗口");
		this.setSize(295, 400);
		this.setVisible(true);
		this.setResizable(false);
		text1.setText(friendId);
	}
	
	
	public static void main(String[] args) {
		new AddUI("2018002");
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource()==but1){
			String friendId= text1.getText();
			String name = text2.getText();
			String groupName = (String) jg.getSelectedItem();
			System.out.println(friendId+name +groupName);
			FriendshipService fService = new FriendShipServiceImpl();
			Friendship f =new Friendship();
			f.setFriendId(Integer.valueOf(friendId));
			f.setUserId(Config.id);
			f.setName(name);
			f.setGroupId(Config.getGroupId(groupName));
			System.out.println(f.getGroupId());
			fService.sendFriend(f);
			JOptionPane.showMessageDialog(null,"发送请求成功，将回到查找页面");
			this.dispose();
		}
	}

}
