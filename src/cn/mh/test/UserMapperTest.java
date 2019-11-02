package cn.mh.test;

import static org.junit.Assert.fail;

import java.io.File;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import cn.mh.mapper.FriendshipMapper;
import cn.mh.mapper.GroupchatMapper;
import cn.mh.mapper.GroupsMapper;
import cn.mh.po.Friendship;
import cn.mh.po.Groupchat;
import cn.mh.service.GroupChatService;
import cn.mh.service.impl.GroupChatServiceImpl;

public class UserMapperTest {

	private SqlSessionFactory sqlSessionFactory;
	
	@Before
	public void setUp() throws Exception {
		String resource = "SqlMapConfig.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
	}

	@Test
	public void testSelectById() {
		SqlSession session = sqlSessionFactory.openSession();
		GroupchatMapper mapper = (GroupchatMapper) session.getMapper(GroupchatMapper.class);
		Groupchat g = mapper.findByGId(1000);
		System.out.println(g);
	}

	@Test
	public void testDelete() {
		GroupChatService gChatService  = new GroupChatServiceImpl();
		Groupchat gchat = gChatService.findByGid(Integer.valueOf(2018001));
	}

	@Test
	public void testInsert() {
		String path="E:/JAVA/workspaces/qq/upload/";
		File dir = new File(path);
		if (!dir.exists())
			dir.mkdirs();
	}

	@Test
	public void testUpdate() {
	}
	
	public static void main(String[] args) {
		UserMapperTest u = new UserMapperTest();
		String path = u.getClass().getClassLoader().getResource("javaBasic/upload").getPath();
		System.out.println(path);
	}

}
