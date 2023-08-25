package com.crypto.project.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Enc_data")
public class EncData {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
	private String encryptedData;
	private String time;
	private long userNumId;
	private String senderName;
	private String type;
	private String reciverLocation;
	private String filePath;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getEncryptedData() {
		return encryptedData;
	}
	public void setEncryptedData(String encryptedData) {
		this.encryptedData = encryptedData;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public long getUserNumId() {
		return userNumId;
	}
	public void setUserNumId(long userNumId) {
		this.userNumId = userNumId;
	}
	public String getSenderName() {
		return senderName;
	}
	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getReciverLocation() {
		return reciverLocation;
	}
	public void setReciverLocation(String reciverLocation) {
		this.reciverLocation = reciverLocation;
	}
	
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	@Override
	public String toString() {
		return "EncData [id=" + id + ", encryptedData=" + encryptedData + ", time=" + time + ", userNumId=" + userNumId
				+ ", senderName=" + senderName + ", type=" + type + ", reciverLocation=" + reciverLocation
				+ ", filePath=" + filePath + "]";
	}
	public EncData(long id, String encryptedData, String time, long userNumId, String senderName, String type,
			String reciverLocation, String filePath) {
		super();
		this.id = id;
		this.encryptedData = encryptedData;
		this.time = time;
		this.userNumId = userNumId;
		this.senderName = senderName;
		this.type = type;
		this.reciverLocation = reciverLocation;
		this.filePath = filePath;
	}
	
	public EncData() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
