package cn.mh.po;
/**
 * 
 * 类名：好友关系
 * 
 * @author mahao
 * @date 2018年5月22日
 * Description:
 */
public class Friendship {
    private Integer id;

    private String name;

    private Integer userId;

    private Integer friendId;
    /**
     * 0 等待验证
     * 1 好友通过
     * 2 拒绝添加
     * */
    private Integer state;

    private Integer groupId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
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
}