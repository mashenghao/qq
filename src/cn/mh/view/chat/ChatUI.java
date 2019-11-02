package cn.mh.view.chat;

import java.awt.Button;
import java.awt.Dialog;
import java.awt.FileDialog;
import java.awt.FlowLayout;
import java.awt.Label;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFrame;

import cn.mh.po.Message;
import cn.mh.util.Config;
import cn.mh.view.friendShip.AddFriendUser;
import cn.mh.view.user.LoginUI;

public class ChatUI {

	private JFrame f;
	private TextField tf;
	private Button but, but2,but3,but4;
	public TextArea ta;

	private Dialog d;
	private Label lab;
	String name;
	Integer id;
	FileDialog openDia;

	public ChatUI(String val) {
		this.name = val.substring(0, val.indexOf("("));
		this.id = Integer.valueOf(val.substring(val.indexOf("(") + 1, val.indexOf(")")));
		try {
			init();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void init() throws Exception {

		String title = "----与好友【  " + name + "  】聊天中----";
		if (name.startsWith("群聊:")) {
			title = "----与好友们【  " + name.substring(3, name.length()) + "  】聊天中----";
		}
		f = new JFrame(title);

		f.setBounds(300, 100, 600, 500);
		f.setLayout(new FlowLayout());

		tf = new TextField(60);

		but = new Button("send");
		if (name.startsWith("群聊:")) {
			but2 = new Button("upload file");
			but3=new Button("download file");
			but4=new Button("add Friend");
			openDia = new FileDialog(f, "选择文件", FileDialog.LOAD);
		}
		ta = new TextArea(25, 70);

		d = new Dialog(f, "提示信息-self", true);
		d.setBounds(400, 200, 240, 150);
		d.setLayout(new FlowLayout());
		lab = new Label();

		d.add(lab);

		f.add(ta);
		f.add(tf);
		f.add(but);
		if (name.startsWith("群聊:")) {
			f.add(but2);
			f.add(but3);
			f.add(but4);
		}
		myEvent();
		f.setVisible(true);
	}

	private void myEvent() {
		but.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sendMsg();
			}
		});

		if (name.startsWith("群聊:")) {
			but2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					openDia.setVisible(true);
					try {
						String path = openDia.getDirectory();
						String filename = openDia.getFile();
						if (path != null && filename != null) {
							File f = new File(path + filename);
							ObjectOutputStream out = new ObjectOutputStream(LoginUI.s.getOutputStream());
							Message ms = new Message(Message.FILE, Config.id + "", null, id + "");
							ms.setFile(f);
							ms.setFileName(filename);
							ms.setLen(f.length());
							out.writeObject(ms);
							out.flush();
							
							BufferedInputStream br = new BufferedInputStream(new FileInputStream(f));
							BufferedOutputStream bo =new BufferedOutputStream(LoginUI.s.getOutputStream());
							int len =0;
							while((len=br.read())!=-1){
								bo.write(len);
							}
							bo.flush();
						}
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			});
			
			but3.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					new FileDownUI(id);
				}
			});
			
			but4.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					new AddFriendUser(id);
					System.out.println(111111);
				}
			});
			
		}
		tf.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER)
					sendMsg();
			}
		});
	}
	public void sendMsg() {
		try {
			ObjectOutputStream out = new ObjectOutputStream(LoginUI.s.getOutputStream());
			String line = tf.getText();
			this.ta.append(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			this.ta.append("\n" + line + "\n\n");
			int type = Message.CHAT;
			if (name.startsWith("群聊:")) {
				type = Message.GROUP_CHAT;
			}
			Message ms = new Message(type, Config.id + "", line, id + "");
			out.writeObject(ms);
			out.flush();
			System.out.println("客户端" + ms);
			tf.setText("");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
