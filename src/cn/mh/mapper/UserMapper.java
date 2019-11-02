package cn.mh.mapper;

import java.util.Map;

import cn.mh.po.User;

public interface UserMapper {
	
	public User selectById(Integer id);

	public void delete(Integer id);
	
	public void insert(User user);

	public void update(User user);

	public User findByNameAndPass(Map<String, String> map);

	public User findByName(String name);

}