package cn.mh.service;

import java.util.List;

import cn.mh.po.Groupchat;

public interface GroupChatService {

	List<Groupchat> findAll(Integer id);

	Groupchat findByGid(Integer valueOf);

	void add(Groupchat gc);
	
}
