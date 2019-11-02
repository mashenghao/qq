package cn.mh.mapper;

import java.util.List;

import cn.mh.po.GroupUser;

public interface GroupUserMapper {

	List<GroupUser> findById(Integer id);

	void insert(GroupUser gu);

	List<GroupUser> findByGid(Integer gid);
	
}