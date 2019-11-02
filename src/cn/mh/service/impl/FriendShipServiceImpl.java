package cn.mh.service.impl;

import java.util.List;
import java.util.Vector;

import org.apache.ibatis.session.SqlSession;

import cn.mh.mapper.FriendshipMapper;
import cn.mh.po.FU;
import cn.mh.po.Friendship;
import cn.mh.service.FriendshipService;
import cn.mh.util.DBUtil;

public class FriendShipServiceImpl implements FriendshipService {

	@Override
	public void sendFriend(Friendship f) {
		SqlSession session = DBUtil.getSession();
		FriendshipMapper mapper = session.getMapper(FriendshipMapper.class);
		mapper.insert(f);
		Friendship f1 = new Friendship();
		f1.setFriendId(f.getUserId());
		f1.setState(0);
		f1.setUserId(f.getFriendId());
		mapper.insert(f1);
		session.commit();
		session.close();
	}

	

	@Override
	public List<Friendship> findByGroupId(Integer groupsId) {
		SqlSession session = DBUtil.getSession();
		FriendshipMapper mapper = session.getMapper(FriendshipMapper.class);
		List<Friendship> list = mapper.findByGroupId(groupsId);
		session.close();
		return list;
	}

	@Override
	public Friendship findFriend(Integer friendId) {
		return null;
	}

	@Override
	public List<Friendship> findAll(Integer userId) {
		SqlSession session = DBUtil.getSession();
		FriendshipMapper mapper = session.getMapper(FriendshipMapper.class);
		List<Friendship> list = mapper.selectAll(userId);
		session.close();
		return list;
	}

	@Override
	public List<Friendship> findAllApply(Integer userId) {
		SqlSession session = DBUtil.getSession();
		FriendshipMapper mapper = session.getMapper(FriendshipMapper.class);
		List<Friendship> list = mapper.findAllApply(userId);
		session.close();
		return list;
	}

	@Override
	public void UpdateFriend(FU fu ) {
		SqlSession session = DBUtil.getSession();
		FriendshipMapper mapper = session.getMapper(FriendshipMapper.class);
		mapper.UpdateFriend(fu);
		mapper.UpdateFriend(new FU(fu.getFriendId(),fu.getUserId(),fu.getState()));
		if (fu.getState()==2){
			mapper.delete();
		}
		session.commit();
		session.close();
	}



	@Override
	public void updateFriend(Friendship f) {
		// TODO Auto-generated method stub
		
	}

}
