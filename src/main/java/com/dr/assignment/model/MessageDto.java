package com.dr.assignment.model;

public class MessageDto {

	public enum MessageType {
		SUCCESS, INFO, WARNING, ERROR
	}

	private String message;
	private MessageType type;

	public MessageDto() {
		super();
	}

	public MessageDto(MessageType type, String message) {
		super();
		this.message = message;
		this.type = type;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public MessageType getType() {
		return type;
	}

	public void setType(MessageType type) {
		this.type = type;
	}
}
