package cn.mh.service.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import cn.mh.mapper.GroupUserMapper;
import cn.mh.mapper.GroupchatMapper;
import cn.mh.po.GroupUser;
import cn.mh.service.GroupUserService;
import cn.mh.util.DBUtil;

public class GroupUserServiceImpl implements GroupUserService{

	public List<GroupUser> findAll(Integer id) {
		SqlSession session = DBUtil.getSession();
		GroupUserMapper mapper = session.getMapper(GroupUserMapper.class);
		return mapper.findById(id);
	}

	@Override
	public List<GroupUser> findBygId(Integer gid) {
		SqlSession session = DBUtil.getSession();
		GroupUserMapper mapper = session.getMapper(GroupUserMapper.class);
		return mapper.findByGid(gid);
	}

	@Override
	public void add(GroupUser gu) {
		SqlSession session = DBUtil.getSession();
		GroupUserMapper mapper = session.getMapper(GroupUserMapper.class);
		mapper.insert(gu);
		session.commit();
	}

	
	
}
