package cn.mh.po;

public class FU {
	private Integer userId;
	private Integer friendId;
	private Integer state;
	private Integer groupId;
	private String name;
	
	public FU(Integer userId, Integer name, Integer state) {
		super();
		this.userId = userId;
		this.friendId = name;
		this.state = state;
	}

	public FU() {
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

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
