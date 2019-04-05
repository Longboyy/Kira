package com.github.maxopoly.Kira.rabbit.session;

import org.json.JSONObject;

public abstract class RequestSession {

	private String sendingKey;

	public RequestSession(String sendingKey) {
		this.sendingKey = sendingKey;
	}

	public abstract JSONObject getRequest();

	public abstract void handleReply(JSONObject json);

	public String getSendingKey() {
		return sendingKey;
	}
}