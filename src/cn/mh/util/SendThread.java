package cn.mh.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

import cn.mh.view.chat.ChatUI;

public class SendThread implements Runnable {
	private Socket s;
	private ChatUI chatUI;

	public SendThread(Socket s, ChatUI chatUI) {
		this.s = s;
		this.chatUI = chatUI;
	}

	public void run() {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			PrintWriter out = new PrintWriter(s.getOutputStream(), true);
			while (true) {
				String line = br.readLine();
				chatUI.ta.append(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
				chatUI.ta.append("\n"+line+"\n");
				out.println(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
