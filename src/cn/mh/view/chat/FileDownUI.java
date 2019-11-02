package cn.mh.view.chat;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import cn.mh.po.ChatFile;
import cn.mh.po.Message;
import cn.mh.service.FileServcie;
import cn.mh.util.Config;
import cn.mh.view.user.LoginUI;

public class FileDownUI extends JFrame implements ActionListener {

	String id;
	JList<String> list;
	JButton bt1;
	Vector<String> v1;
	

	public FileDownUI(Integer id) {
		this.id = id + "";
		this.setTitle("文件列表");
		this.setLayout(new BorderLayout());

		// 获取好友信息
		v1 = this.getFile(id + "");
		// 创建列表框，填充的数据为好友账号信息
		list = new JList<String>(v1);
		// 把列表框添加到具有滚动功能的容器中
		JScrollPane scollPane = new JScrollPane(list);
		// 把中间容器添加到窗口
		this.add(scollPane);

		// 添加按钮
		bt1 = new JButton("下载");
		bt1.addActionListener(this);
		JPanel p1 = new JPanel();
		p1.add(bt1);
		this.add(p1, BorderLayout.SOUTH);

		this.setSize(400, 500);
		this.setVisible(true);
	}

	public Vector<String> getFile(String username) {
		FileServcie fileServcie = new FileServcie();
		Vector<String> v1 = fileServcie.getFileByGid(id);
		return v1;
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == bt1) {
			String filename = list.getSelectedValue();
			System.out.println(filename);
			if (null != filename &&! "".equals(filename)) {
				try {
					ObjectOutputStream out = new ObjectOutputStream(LoginUI.s.getOutputStream());
					Message ms = new Message(Message.FILE_DOWN, Config.id + "", null, id);
					ms.setFileName(filename);
					out.writeObject(ms);
					out.flush();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}

		}
	}

	public static void main(String[] args) {
		new FileDownUI(1000);
	}
}
