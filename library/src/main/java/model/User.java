package model;

public class User {

	private int id;
	private String userLogin;
	private String userPass;
	private long creationDate;
	private long lastAuthentication;
	private String lastSSID;
	private Role role = Role.USER;
	
	public User(){}
	
	public User(int id, String userLogin, String userPass, long creationDate) {
		this.id = id;
		this.userLogin = userLogin;
		this.userPass = userPass;
		this.creationDate = creationDate;
	}
	public User(int id, String userLogin, String userPass) {
		this.id = id;
		this.userLogin = userLogin;
		this.userPass = userPass;
	}

	public User(String userLogin, String userPass, long creationDate) {
		this.userLogin = userLogin;
		this.userPass = userPass;
		this.creationDate = creationDate;
	}
	
	public User(String userLogin, String userPass) {
		this.userLogin = userLogin;
		this.userPass = userPass;
	}

	public User(int id, String userLogin, String userPass, long creationDate, long lastAuthentication,
			String lastSSID, Role role) {
		this.id = id;
		this.userLogin = userLogin;
		this.userPass = userPass;
		this.creationDate = creationDate;
		this.lastAuthentication = lastAuthentication;
		this.lastSSID = lastSSID;
		this.role = role;
	}

	public User(String userLogin, String userPass, long creationDate, long lastAuthentication,
			String lastSSID, Role role) {
		this.userLogin = userLogin;
		this.userPass = userPass;
		this.creationDate = creationDate;
		this.lastAuthentication = lastAuthentication;
		this.lastSSID = lastSSID;
		this.role = role;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getUserLogin() {
		return userLogin;
	}

	public void setUserLogin(String userLogin) {
		this.userLogin = userLogin;
	}

	public String getUserPass() {
		return userPass;
	}

	
	public long getLastAuthentication() {
		return lastAuthentication;
	}

	public void setLast_authentication(long lastAuthentication) {
		this.lastAuthentication = lastAuthentication;
	}

	public String getLastSSID() {
		return lastSSID;
	}

	public void setLast_ssid(String lastSSID) {
		this.lastSSID = lastSSID;
	}

	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}

	public long getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(long creationDate) {
		this.creationDate = creationDate;
	}

	Role getRole() {
		return role;
	}

	void setRole(Role role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", userLogin=" + userLogin + ", userPass=" + userPass + ", creationDate="
				+ creationDate + ", lastAuthentication=" + lastAuthentication + ", lastSSID=" + lastSSID + ", role="
				+ role + "]";
	}

	
}
