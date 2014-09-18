package Entity;

import java.util.HashMap;
import java.util.Map;

public class ContactList {
	private Map<Integer, User> contacts=new HashMap<Integer,User>();
	
	public boolean addContact(User user){
			contacts.put(Integer.valueOf(user.getId()), user);
			return true;
	}
	
	public Map<Integer, User> getAllContact(){
		return contacts;
	}
	
	public User getContactById(int id){
		return contacts.get(Integer.valueOf(id));
	}

}
