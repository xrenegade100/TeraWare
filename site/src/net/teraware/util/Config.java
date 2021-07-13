package net.teraware.util;

public class Config {

	private String username;
	private String password;
	private String host;
	private int port;

	public Config(String username, String password, String host, int port) {
		this.username = username;
		this.password = password;
		this.host = host;
		this.port = port;
	}

	public Config(String username, String password, String host) {
		this.username = username;
		this.password = password;
		this.host = host;
		this.port = 3306;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getHost() {
		return host;
	}

	public int getPort() {
		return port;
	}

	public String toString() {
		return (
			getClass().getName() +
			", username= " +
			username +
			", password= " +
			password +
			", host= " +
			host +
			", port= " +
			port
		);
	}
}
