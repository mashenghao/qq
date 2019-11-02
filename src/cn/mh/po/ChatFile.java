package cn.mh.po;

public class ChatFile {
	private Integer id;
	private String path;
	private String fileName;
	private Integer groupchatId;
	
	public ChatFile() {
		super();
	}
	public ChatFile(String path2, String fileName2, String receId) {
		path=path2;
		fileName=fileName2;
		groupchatId=Integer.valueOf(receId);
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public Integer getGroupchatId() {
		return groupchatId;
	}
	public void setGroupchatId(Integer groupchatId) {
		this.groupchatId = groupchatId;
	}
	@Override
	public String toString() {
		return "ChatFile [id=" + id + ", path=" + path + ", fileName=" + fileName + ", groupchatId=" + groupchatId
				+ "]";
	}
}
