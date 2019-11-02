package cn.mh.service.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import cn.mh.mapper.GroupUserMapper;
import cn.mh.mapper.GroupchatMapper;
import cn.mh.po.GroupUser;
import cn.mh.po.Groupchat;
import cn.mh.service.GroupChatService;
import cn.mh.util.DBUtil;

public class GroupChatServiceImpl implements GroupChatService{
	
	public List<Groupchat> findAll(Integer id) {
		SqlSession session = DBUtil.getSession();
		GroupchatMapper mapper = session.getMapper(GroupchatMapper.class);
		return mapper.findById(id);
	}

	public Groupchat findByGid(Integer id) {
		SqlSession session = DBUtil.getSession();
		GroupchatMapper mapper = session.getMapper(GroupchatMapper.class);
		return mapper.findByGId(id);		
	}

	@Override
	public void add(Groupchat gc) {
		SqlSession session = DBUtil.getSession();
		GroupchatMapper mapper = session.getMapper(GroupchatMapper.class);
		mapper.insert(gc);
		session.commit();
		GroupUserMapper mapper1 = session.getMapper(GroupUserMapper.class);
		GroupUser gu = new GroupUser();
		gu.setGroupchatId(gc.getId());
		gu.setFriendId(gc.getUserid());
		mapper1.insert(gu);
		session.commit();
	}
	
}
