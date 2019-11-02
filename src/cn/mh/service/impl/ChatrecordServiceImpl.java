package cn.mh.service.impl;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.apache.ibatis.session.SqlSession;

import cn.mh.mapper.ChatrecordMapper;
import cn.mh.po.Chatrecord;
import cn.mh.service.ChatrecordService;
import cn.mh.util.DBUtil;

public class ChatrecordServiceImpl  implements ChatrecordService{

	public void addChat(Chatrecord c) {
		SqlSession session = DBUtil.getSession();
		ChatrecordMapper mapper = session.getMapper(ChatrecordMapper.class);
		mapper.insert(c);
		session.commit();
		session.close();
	}

	@Override
	public List<Chatrecord> findChat(Integer userId, Integer friendId) {
		SqlSession session = DBUtil.getSession();
		ChatrecordMapper mapper = session.getMapper(ChatrecordMapper.class);
		Map<Integer,Integer> map = new HashMap<Integer,Integer>();
		mapper.findChat(map);
		return null;
	}

	@Override
	public Vector<String> findChat(Chatrecord c) {
		SqlSession session = DBUtil.getSession();
		ChatrecordMapper mapper = session.getMapper(ChatrecordMapper.class);
		List<Chatrecord> cs = mapper.findChats(c);
		Vector<String> v = new Vector<String>();
		for (Chatrecord c1 : cs ){
			//System.out.println(c1);
			v.addElement(c1.getFriendId() +" "+c1.getContent()+" "+new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(c1.getTime()));
		}
		return v;
	}

}
