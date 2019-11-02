package cn.mh.mapper;

import java.util.List;

import cn.mh.po.Groupchat;

public interface GroupchatMapper {

	List<Groupchat> findById(Integer id);

	Groupchat findByGId(Integer id);

	void insert(Groupchat gc);

}