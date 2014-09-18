package DAO;

import java.util.HashMap;
import java.util.Map;

import Entity.User;

public class MemDao implements Dao {

	private int userCount;
	private Map<Integer, User> users=new HashMap<Integer, User>();
	
	private static final MemDao INSTANCE =new MemDao();
	private MemDao(){}
	public static MemDao getInstance(){
		return INSTANCE;
	}
	
	@Override
	public User login(int id, String pass) {
		if(!users.containsKey(id)){
			throw new IllegalArgumentException("Not equals user bi id:"+id);
		}
		if(!users.get(id).validation(pass)){
			throw new IllegalArgumentException("Invalid password");
		}
		return users.get(id);
		
	}

	@Override
	public boolean addUser(User user){
		if(!users.containsKey(user.getId())){
			users.put(user.getId(), user);
			userCount++;
			return true;
		}
		return false;
	}
	@Override
	public User getUser(int id){
		if(users.containsKey(id)){
			return users.get(id);
		}
		return null;
	}

	@Override
	public int getNewId(){
		return userCount+1;
	}
	
	@Override
	public boolean removeUser(int id){
		if(users.containsKey(id)){
			users.remove(id);
			return true;
		}
		return false;
	}
}
