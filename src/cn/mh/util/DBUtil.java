package cn.mh.util;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.net.SyslogAppender;

import cn.mh.mapper.FriendshipMapper;
import cn.mh.mapper.UserMapper;

public class DBUtil {
	
	public static  SqlSessionFactory sessionFactory;  
	
	static{
		String resource = "SqlMapConfig.xml";
		try {
			InputStream in = Resources.getResourceAsStream(resource);
			sessionFactory= new SqlSessionFactoryBuilder().build(in);  
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static SqlSession getSession(){
		return sessionFactory.openSession();
	}
	
	public static void main(String[] args) {
		
	}
}
