package cn.mh.mapper;

import java.util.List;
import java.util.Map;

import cn.mh.po.ChatFile;

public interface ChatFileMapper {
	
	public Integer insert(ChatFile f);

	public List<ChatFile> getFileByGid(String id);
  
	public ChatFile findByName(Map<String, String> map);
	
}