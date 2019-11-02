package cn.mh.service;

import java.util.List;

import cn.mh.po.FU;
import cn.mh.po.Friendship;
import cn.mh.po.User;

public interface FriendshipService {
	
	/**发送好友申请*/
	public void sendFriend(Friendship f);
	
	/**根据分组查看好友*/
	public List<Friendship> findByGroupId(Integer groupsId);
	
	/**查看好友信息*/
	public Friendship findFriend(Integer friendId);
	
	/**更新好友信息*/
	public void updateFriend(Friendship f);
	
	/**查找所有的好友*/
	public List<Friendship> findAll(Integer userId);
	
	/**查找所有的好友申请*/
	public List<Friendship> findAllApply(Integer userId);

	/**好友关系更新*/
	void UpdateFriend(FU fu);
	
}
