package cn.mh.util;

import java.io.ObjectInputStream;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

import cn.mh.po.Message;
import cn.mh.view.chat.ChatUI;
import cn.mh.view.chat.SaveFileUI;
import cn.mh.view.friendShip.FriendListUI;

public class ReceThread implements Runnable {

	private Socket s;

	public ReceThread(Socket s) {
		this.s = s;
	}

	public void run() {
		try {
			while (true) {
				ObjectInputStream in = new ObjectInputStream(s.getInputStream());
				Message ms = (Message) in.readObject();
				System.out.println("客户端---" + ms);
				if(Message.FILE_DOWN.equals(ms.getTypeNo())){
					System.out.println("下载-------------");
					new SaveFileUI(ms);
				}else{
					ChatUI chatUI = FriendListUI.ChatUIs.get(ms.getSendId());
					if (chatUI != null) {
						String s = "|-----------------------------------------";
						chatUI.ta.append(s + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "\n");
						chatUI.ta.append(s + ms.getContent()+"\n");
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
