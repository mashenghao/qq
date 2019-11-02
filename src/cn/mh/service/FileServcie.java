package cn.mh.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.apache.ibatis.session.SqlSession;

import cn.mh.mapper.ChatFileMapper;
import cn.mh.mapper.UserMapper;
import cn.mh.po.ChatFile;
import cn.mh.po.User;
import cn.mh.util.DBUtil;

public class FileServcie {
	
	public void insert(ChatFile f ) {
		SqlSession session = DBUtil.getSession();
		ChatFileMapper mapper  = session.getMapper(ChatFileMapper.class);
		mapper.insert(f);
		session.commit();
		session.close();
	}

	public Vector<String> getFileByGid(String id) {
		SqlSession session = DBUtil.getSession();
		ChatFileMapper mapper  = session.getMapper(ChatFileMapper.class);
		List<ChatFile> list = mapper.getFileByGid(id);
		Vector<String> v =new Vector<String>();
		for(ChatFile c : list){
			v.addElement(c.getFileName());
		}
		return v;
	}

	public ChatFile findByName(String filename, String id) {
		SqlSession session = DBUtil.getSession();
		ChatFileMapper mapper  = session.getMapper(ChatFileMapper.class);
		Map<String,String> map = new HashMap<String,String>();
		map.put("filename", filename);
		map.put("id",id);
		return mapper.findByName(map);
	}
}
