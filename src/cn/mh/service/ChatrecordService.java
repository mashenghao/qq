package cn.mh.service;

import java.util.List;
import java.util.Vector;

import cn.mh.po.Chatrecord;

public interface ChatrecordService {
	
	/**新增会话*/
	public void addChat(Chatrecord c);
	
	/**查看聊天记录*/
	public List<Chatrecord> findChat(Integer userId,Integer friendId);

	/**模糊查询历史记录*/
	public Vector<String> findChat(Chatrecord c);
	
	
}
