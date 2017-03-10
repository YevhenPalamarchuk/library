package model;

public class User {

	private int id;
	private String userLogin;
	private String userPass;
	private long creationDate;
	
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

	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}

	public long getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(long creationDate) {
		this.creationDate = creationDate;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", userLogin=" + userLogin + ", userPass=" + userPass + ", creationDate="
				+ creationDate + "]";
	}
	
}
