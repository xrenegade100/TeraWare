package net.teraware.auth;

import com.google.gson.annotations.SerializedName;

public class GoogleOAuthUser {

	@SerializedName(value = "sub")
	private String id;

	private String email;

	@SerializedName(value = "email_verified")
	private boolean isEmailVerified;

	private String name;

	@SerializedName(value = "given_name")
	private String givenName;

	@SerializedName(value = "family_name")
	private String familyName;

	private String picture;
	private String locale;

	public GoogleOAuthUser(
		String id,
		String email,
		boolean verified_email,
		String name,
		String given_name,
		String family_name,
		String picture,
		String locale
	) {
		this.id = id;
		this.email = email;
		this.isEmailVerified = verified_email;
		this.name = name;
		this.givenName = given_name;
		this.familyName = family_name;
		this.picture = picture;
		this.locale = locale;
	}

	public String getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	public boolean isVerifiedEmail() {
		return isEmailVerified;
	}

	public String getName() {
		return name;
	}

	public String getGiven_name() {
		return givenName;
	}

	public String getFamily_name() {
		return familyName;
	}

	public String getPicture() {
		return picture;
	}

	public String getLocale() {
		return locale;
	}

	@Override
	public String toString() {
		return (
			"User [id=" +
			id +
			", email=" +
			email +
			", isEmailVerified=" +
			isEmailVerified +
			", name=" +
			name +
			", givenName=" +
			givenName +
			", familyName=" +
			familyName +
			", picture=" +
			picture +
			", locale=" +
			locale +
			"]"
		);
	}
}
