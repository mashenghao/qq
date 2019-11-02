package cn.mh.po;
/**
 * 
 * 类名：用户类
 * 
 * @author mahao
 * @date 2018年5月22日
 * Description:
 */
public class User {
	
    private Integer id;
   
    /**登录名*/
    private String username;

    private String password;
   
    /**qq昵称*/
    private String nickname;

    private String sex;

    private Integer age;
    
    /**个性签名*/
    private String signature;

    private String content;
   
    /**用户状态： 0.正常 1.注销*/
    private Integer state;
   
    /**用户角色：0.一般用户 2.管理员*/
    private Integer role;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature == null ? null : signature.trim();
    }

   

   

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

	@Override
	public String toString() {
		return "User [username=" + username + ", nickname=" + nickname + ", sex=" + sex + ", age=" + age
				+ ", signature=" + signature + ", content=" + content + "]";
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}