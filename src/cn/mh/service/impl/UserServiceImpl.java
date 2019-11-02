package cn.mh.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import cn.mh.mapper.UserMapper;
import cn.mh.po.User;
import cn.mh.service.UserService;
import cn.mh.util.Config;
import cn.mh.util.DBUtil;

public class UserServiceImpl implements UserService {
	@Override
	public  User findByNameAndPass(String name, String pass) {
		SqlSession session = DBUtil.getSession();
		UserMapper mapper  = session.getMapper(UserMapper.class);
		Map<String,String> map = new HashMap<String,String>();
		map.put("name", name);
		map.put("pass", pass);
		return mapper.findByNameAndPass(map);
	}

	@Override
	public void insertUser(User user) {
		SqlSession session = DBUtil.getSession();
		UserMapper mapper  = session.getMapper(UserMapper.class);
		mapper.insert(user);
		session.commit();
		session.close();
	}

	@Override
	public User findByName(String name) {
		SqlSession session = DBUtil.getSession();
		UserMapper mapper  = session.getMapper(UserMapper.class);
		return mapper.findByName(name);
	}

	@Override
	public void delete(Integer id) {
	}

	@Override
	public void update(User user) {

	}

}
