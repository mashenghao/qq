package cn.mh.po;

import java.io.File;
import java.io.Serializable;

public class Message implements Serializable{
	
	public static final Integer LOGIN  =1;
	public static final Integer LOGOUT =2;
	public static final Integer CHAT   =3;
	public static final Integer GROUP_CHAT=4;
	public static final Integer FILE   =5;
	public static final Integer FILE_DOWN  =6;
	private Integer typeNo;
	
	public Message() {
		super();
	}
	
	public Message(Integer typeNo, String sendId, String content, String receId) {
		super();
		this.typeNo = typeNo;
		this.sendId = sendId;
		this.content = content;
		this.receId = receId;
	}
	
	private String sendId;
	private String content;
	private String receId;
	
	private String fileName;
	private File file;
	private long len;
	
	public long getLen() {
		return len;
	}

	public void setLen(long len) {
		this.len = len;
	}

	public String getSendId() {
		return sendId;
	}
	public void setSendId(String sendId) {
		this.sendId = sendId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getReceId() {
		return receId;
	}
	public void setReceId(String receId) {
		this.receId = receId;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}
	public Integer getTypeNo() {
		return typeNo;
	}
	public void setTypeNo(Integer typeNo) {
		this.typeNo = typeNo;
	}
	@Override
	public String toString() {
		return "Message [typeNo=" + typeNo + ", sendId=" + sendId + ", content=" + content + ", receId=" + receId
				+ ", fileName=" + fileName + ", file=" + file + "]";
	}
	
}
