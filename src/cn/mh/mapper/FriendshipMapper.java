package cn.mh.mapper;

import java.util.List;

import cn.mh.po.FU;
import cn.mh.po.Friendship;

public interface FriendshipMapper {
	
	public Friendship selectById(Integer id);

	public List<Friendship> selectAll(Integer userId);

	public List<Friendship> findByGroupId(Integer groupsId);

	public Integer insert(Friendship c);
	
	public List<Friendship> findAllApply(Integer userId);

	public void UpdateFriend(FU fu);

	public void delete();

	
}