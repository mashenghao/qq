package cn.mh.mapper;

import java.util.List;

import cn.mh.po.Groups;

public interface GroupsMapper {
	
	public Groups selectById(Integer id);

	public void delete(Integer id);

	public void insert(Groups g);

	public void update(Groups g);

	public List<Groups> findAll(Integer userId);

	public void deletes(Groups g);

}