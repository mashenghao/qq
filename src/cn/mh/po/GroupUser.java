package cn.mh.po;

public class GroupUser {
	
	private Integer id;
	private Integer friendId;
	private Integer groupchatId;
	private String name;//群名
	
	public GroupUser() {
		super();
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getFriendId() {
		return friendId;
	}
	public void setFriendId(Integer friendId) {
		this.friendId = friendId;
	}
	public Integer getGroupchatId() {
		return groupchatId;
	}
	public void setGroupchatId(Integer groupchatId) {
		this.groupchatId = groupchatId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
