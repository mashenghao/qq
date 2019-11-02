package cn.mh.po;

import java.util.Date;
/**
 * 
 * 类名：实体 聊天记录
 * 
 * @author mahao
 * @date 2018年5月22日
 * Description:
 */
public class Chatrecord {
   
	private Integer id;

    private String content;

    private Date time;

    private Integer userId;
    
    private User user;
    
    private User friend;
    
    private Integer friendId;
    
    private String se;
    
    public String getSe() {
		return se;
	}

	public void setSe(String se) {
		this.se = se;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getFriendId() {
        return friendId;
    }

    public void setFriendId(Integer friendId) {
        this.friendId = friendId;
    }

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public User getFriend() {
		return friend;
	}

	public void setFriend(User friend) {
		this.friend = friend;
	}
}