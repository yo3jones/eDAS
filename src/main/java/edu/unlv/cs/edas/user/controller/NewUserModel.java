package edu.unlv.cs.edas.user.controller;

import org.apache.commons.lang3.StringUtils;

public class NewUserModel {

	private String message = "";
	private boolean showCreate = true;
	
	public String getMessage() {
		return message;
	}
	
	public NewUserModel setMessage(String message) {
		this.message = message;
		return this;
	}
	
	public boolean getShowMessage() {
		return StringUtils.isNotBlank(message);
	}
	
	public boolean getShowCreate() {
		return showCreate;
	}
	
	public NewUserModel setShowCreate(boolean showCreate) {
		this.showCreate = showCreate;
		return this;
	}
	
}
