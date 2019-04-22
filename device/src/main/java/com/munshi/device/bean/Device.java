package com.munshi.device.bean;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Device {
	

	@Id
	private String id;
	private String type;
	private String userId;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	

}
