package net.teraware.auth;

import com.google.gson.annotations.SerializedName;

public class GoogleOAuthCodeResponse {

	@SerializedName(value = "access_token")
	private String accessToken;

	@SerializedName(value = "expires_in")
	private int expiresIn;

	@SerializedName(value = "id_token")
	private String idToken;

	public GoogleOAuthCodeResponse(String accessToken, int expiresIn, String idToken) {
		this.accessToken = accessToken;
		this.expiresIn = expiresIn;
		this.idToken = idToken;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public int getExpiresIn() {
		return expiresIn;
	}

	public String getIdToken() {
		return idToken;
	}

	public String toString() {
		return (
			getClass().getName() +
			", accessToken= " +
			accessToken +
			", expiresIn= " +
			expiresIn +
			", idToken= "
		);
	}
}
