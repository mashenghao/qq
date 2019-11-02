package cn.mh.view.chat;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import cn.mh.po.Chatrecord;
import cn.mh.service.ChatrecordService;
import cn.mh.service.impl.ChatrecordServiceImpl;
import cn.mh.util.Config;

public class RecordUI extends JFrame{
	JList list = new JList();
	JButton bt;
	JLabel l1, l2,l3;
	JTextField tf1, tf2,tf3;
	Vector<String> v = new Vector<String>();

	public RecordUI() {

		this.setTitle("查询聊天记录");
		JScrollPane scrollpane = new JScrollPane(list);
		this.add(list);
		bt = new JButton("查询");
		bt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == bt) {
					Chatrecord c = new Chatrecord();
					c.setContent(tf3.getText().trim());
					String fId = tf1.getText().trim();
					Integer fid = null;
					if(fId.length()>0){
						fid = Integer.valueOf(fId);
					}
					c.setFriendId(fid);
					c.setId(Config.id);
					c.setUserId(Config.id);
					c.setSe(tf2.getText());
					ChatrecordService s = new ChatrecordServiceImpl(); 
					Vector<String> v = s.findChat(c);
					list.setListData(v);
				}
			}
		});
		
		l1 = new JLabel("用户名");
		tf1 = new JTextField(10);
		l3 = new JLabel("关键字");
		tf3 = new JTextField(10);
		l2 = new JLabel("时    间");
		tf2 = new JTextField(10);
		JPanel p = new JPanel();
		p.add(l1);
		p.add(tf1);
		p.add(l3);
		p.add(tf3);
		p.add(l2);
		p.add(tf2);
		p.add(bt);
		this.add(p, BorderLayout.NORTH);
		this.setSize(600, 300);
		this.setVisible(true);
		this.setLocation(300, 600);
	}
	public static void main(String[] args) {
		new RecordUI();
	}
}
