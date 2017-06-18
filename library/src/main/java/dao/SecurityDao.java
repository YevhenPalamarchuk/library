package dao;

import java.util.List;

import model.User;

public interface SecurityDao {

	public List<User> getUsers();

	public void addUser(User theUser);
	
	public int doAuthentication(String login, String password, String ssid, long authenticationTime);
	
	

}