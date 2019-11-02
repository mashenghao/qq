package cn.mh.service;

import java.util.List;

import cn.mh.po.GroupUser;
import cn.mh.po.Groupchat;

public interface GroupUserService {

	List<GroupUser> findAll(Integer id);

	List<GroupUser> findBygId(Integer integer);

	void add(GroupUser gu);

}
