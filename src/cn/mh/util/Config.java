package cn.mh.util;

import java.util.List;

import org.apache.logging.log4j.core.helpers.UUIDUtil;

import cn.mh.po.Friendship;
import cn.mh.po.Groups;
import cn.mh.service.impl.GroupServiceImpl;

public class Config {

	public static Integer id =null;

	public static String username = null;

	public static String nickname = null;

	public static  Integer getGroupId(String name) {
		List<Groups> gs = new GroupServiceImpl().findAll(Config.id);
		for (Groups g : gs) {
			if (g.getName().equals(name)) {
				return g.getId();
			}
		}
		return null;
	}
	
}
