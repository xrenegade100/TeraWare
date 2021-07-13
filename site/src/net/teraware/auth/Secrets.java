package net.teraware.auth;

import com.google.gson.annotations.SerializedName;

public class Secrets {

	@SerializedName(value = "client_secret")
	private String clientSecret;

	public Secrets(String clientSecret) {
		this.clientSecret = clientSecret;
	}

	public String getClientSecret() {
		return this.clientSecret;
	}
}
