package cn.mh.service;

import java.util.List;

import cn.mh.po.Groups;

public interface GroupService {
	
	/**查询用户下所有分组*/
	public List<Groups> findAll(Integer userId);
	
	/**删除分组*/
	public void delete(Integer id );

	/**新增分组*/
	public void add(Groups g);
	
	/**修改分组*/
	public void update(Groups g);

	public void delete(Integer id, String val);
	
}
