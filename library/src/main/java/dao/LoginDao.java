package dao;

import java.util.List;

import model.User;

public interface LoginDao {

	public List<User> getUsers();

	public void addUser(User theUser);

}
