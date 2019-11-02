package cn.mh.service.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import cn.mh.mapper.FriendshipMapper;
import cn.mh.mapper.GroupsMapper;
import cn.mh.po.Groups;
import cn.mh.service.GroupService;
import cn.mh.util.DBUtil;

public class GroupServiceImpl implements GroupService{

	@Override
	public List<Groups> findAll(Integer userId) {
		SqlSession session = DBUtil.getSession();
		GroupsMapper mapper = session.getMapper(GroupsMapper.class);
		List<Groups> list =  mapper.findAll(userId);
		session.close();
		return list;
	}

	@Override
	public void delete(Integer id) {
		SqlSession session = DBUtil.getSession();
		GroupsMapper mapper = session.getMapper(GroupsMapper.class);
		mapper.delete(id);
		session.commit();
	}

	@Override
	public void delete(Integer id, String val) {
		SqlSession session = DBUtil.getSession();
		GroupsMapper mapper = session.getMapper(GroupsMapper.class);
		Groups g= new Groups();
		g.setUserId(id);
		g.setName(val);
		mapper.deletes(g);
		session.commit();
		
	}
	
	@Override
	public void add(Groups g) {
		SqlSession session = DBUtil.getSession();
		GroupsMapper mapper = session.getMapper(GroupsMapper.class);
		mapper.insert(g);
		session.commit();
	}

	@Override
	public void update(Groups g) {
		// TODO Auto-generated method stub
		
	}

	

}
