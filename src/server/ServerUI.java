package server;

import java.io.*;
import java.net.*;
import java.util.*;
import cn.mh.po.*;
import cn.mh.service.*;
import cn.mh.service.impl.*;

/**
 * 
 * 类名：tcp方式聊天，服务端
 * 
 * @author mahao
 * @date 2018年6月10日 Description:
 */

public class ServerUI {

	public static void main(String[] args) throws Exception {
		Map<String, Socket> map = new HashMap<String, Socket>();
		ServerSocket ss = new ServerSocket(8000);
		System.out.println("服务器已经启动，监听端口8000-----");
		while (true) {
			Socket s = ss.accept();
			System.out.println("服务器新来连接----" + s);
			new Thread(new ServerT(map, s)).start();
		}
	}

}

class ServerT implements Runnable {

	private Map<String, Socket> map;
	private Socket s;

	public ServerT(Map<String, Socket> map, Socket s) {
		System.out.println("------------------------server");
		this.map = map;
		this.s = s;
	}

	public void run() {

		try {
			ChatrecordService chatService = new ChatrecordServiceImpl();
			
			while (true) {
				ObjectInputStream in = new ObjectInputStream(s.getInputStream());
				Message ms = (Message) in.readObject();
				System.out.println(ms);
				int typeNo = ms.getTypeNo();
				if (Message.LOGIN == typeNo) {// 用户登录
					map.put(ms.getSendId(), s);
					FriendshipService friendshipService = new FriendShipServiceImpl();
					List<Friendship> list = friendshipService.findAll(Integer.valueOf(ms.getSendId()));
					for (Friendship f : list) {
						Socket s = map.get(f.getName());
						if (s != null) {
							ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream());
							out.writeObject(ms);
							out.flush();
						}
					}
				} else if (Message.CHAT == typeNo) {// 聊天
					Socket s1 = (Socket) map.get(ms.getReceId());
					if (s1 == null) {
						s1 = s;
						ms.setContent("用户不在线");
						ms.setSendId(ms.getReceId());
					}
					ObjectOutputStream out = new ObjectOutputStream(s1.getOutputStream());
					Chatrecord c = new Chatrecord();
					c.setUserId(Integer.valueOf(ms.getSendId()));
					c.setContent(ms.getContent());
					c.setFriendId(Integer.valueOf(ms.getReceId()));
					c.setTime(new Date());
					chatService.addChat(c);
					out.writeObject(ms);
					out.flush();
				} else if (Message.GROUP_CHAT == typeNo) {// 群聊
					GroupChatService gChatService = new GroupChatServiceImpl();
					Groupchat gchat = gChatService.findByGid(Integer.valueOf(ms.getReceId()));
					// 群发信息
					ms.setSendId(ms.getReceId());
					for (GroupUser gu : gchat.getGroupUsers()) {
						Socket s2 = (Socket) map.get("" + gu.getFriendId());
						if (s2 != null && s2 != s) {
							ObjectOutputStream out = new ObjectOutputStream(s2.getOutputStream());
							out.writeObject(ms);
							out.flush();
						}
					}
				} else if (Message.FILE == typeNo) {// 文件
					//E:/JAVA/workspaces/qq/upload/cc9b7f65-5b96-41af-be4f-3595d1256917
					String path="E:/JAVA/workspaces/qq/upload/";
					File dir = new File(path);
					while (!dir.exists())
						dir.mkdirs();
					String filePath = path + UUID.randomUUID().toString();
					FileOutputStream out1 = new FileOutputStream(new File(filePath));
					BufferedInputStream in1 = new BufferedInputStream(s.getInputStream());
					byte[] bt =new byte[1024*5];
					int len =0;
					while(len<ms.getLen()&&in1.read(bt)!=-1){
						out1.write(bt,0,bt.length);
						len+=bt.length;
						System.out.println("上传中---");
					}
					//in1.close();
					out1.close();
					FileServcie c = new FileServcie();
					c.insert(new ChatFile(filePath, ms.getFileName(), ms.getReceId()));
					System.out.println("上传成功-----------------");
				} else if(Message.FILE_DOWN==typeNo) {//下载文件
					System.out.println("-----下载文件");
					FileServcie fileServcie = new FileServcie();
					ChatFile cf = fileServcie.findByName(ms.getFileName(),ms.getReceId());
					ms.setFile(new File(cf.getPath()));
					ms.setFileName(cf.getFileName());
					ms.setLen(ms.getFile().length());
					Socket s = map.get(ms.getSendId());
					ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream());
					out.writeObject(ms);
					out.flush();
					
					BufferedInputStream br = new BufferedInputStream(new FileInputStream(new File(cf.getPath())));
					BufferedOutputStream bo =new BufferedOutputStream(s.getOutputStream());
					int len =0;
					while((len=br.read())!=-1){
						bo.write(len);
					}
					bo.flush();
					
				}else if (Message.LOGOUT == typeNo) {// 退出
					FriendshipService friendshipService = new FriendShipServiceImpl();
					List<Friendship> list = friendshipService.findAll(Integer.valueOf(ms.getSendId()));
					for (Friendship f : list) {
						Socket s = map.get(f.getName());
						if (s != null) {
							ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream());
							out.writeObject(ms);
							out.flush();
						}
					}
					return;
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}