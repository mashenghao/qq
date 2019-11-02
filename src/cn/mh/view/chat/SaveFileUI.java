package cn.mh.view.chat;

import java.awt.FileDialog;
import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.net.Socket;

import javax.swing.JFrame;

import cn.mh.po.ChatFile;
import cn.mh.po.Message;
import cn.mh.service.FileServcie;
import cn.mh.view.user.LoginUI;

public class SaveFileUI {

	private Message ms;
	FileDialog saveDia;

	public SaveFileUI(Message ms) {
		this.ms = ms;
		JFrame f = new JFrame("保存文件");
		f.setVisible(false);
		saveDia = new FileDialog(f, "保存文件", FileDialog.SAVE);
		saveDia.setVisible(true);
		String path = saveDia.getDirectory();
		String filename1 = saveDia.getFile();
		System.out.println(path + filename1);
		if (path != null && filename1 != null) {
			try {
				String stuff = ms.getFileName().substring(ms.getFileName().lastIndexOf("."), ms.getFileName().length());
				System.out.println(stuff);	
				FileOutputStream out = new FileOutputStream(new File(path + filename1+stuff));
				BufferedInputStream in1 = new BufferedInputStream(LoginUI.s.getInputStream());
				byte[] bt =new byte[1024*5];
				int len =0;
				while(len<ms.getLen()&&in1.read(bt)!=-1){
					out.write(bt,0,bt.length);
					len+=bt.length;
					System.out.println("下载中---");
				}
				System.out.println("下载完成---");
				
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}

}
