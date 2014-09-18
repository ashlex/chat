package DAO;

import Entity.User;

public interface Dao {
	User login(int id, String pass);
	boolean addUser(User user);
	User getUser(int id);
	int getNewId();
	boolean removeUser(int id);
}
