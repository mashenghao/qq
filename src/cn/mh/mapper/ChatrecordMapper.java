package cn.mh.mapper;

import java.util.List;
import java.util.Map;

import cn.mh.po.Chatrecord;

public interface ChatrecordMapper {
	
	public Chatrecord selectById(Integer id);
	
	public void delete(Integer id);
	
	/**保存消息*/
	public Integer insert(Chatrecord c);
	
	/**
	 * 查找聊天记录
	 * @param map
	 * 
	 */
	public void findChat(Map<Integer,Integer> map);

	public List<Chatrecord> findChats(Chatrecord c);
	
}