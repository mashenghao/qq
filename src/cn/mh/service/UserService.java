package cn.mh.service;

import cn.mh.po.User;

public interface UserService {
	
	/** 登录 ，通过用户名密码查找用户*/
	public User findByNameAndPass(String name,String pass);
	
	/** 注册用户*/
	public void insertUser(User user);
	
	/** 查询用户，通过用户名 */
	public User findByName(String name);
	
	/** 注销用户*/
	public void delete(Integer id);
	
	/**修改用户*/
	public void update(User user);
		
}
