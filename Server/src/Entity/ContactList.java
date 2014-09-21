package Entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class ContactList {
	private Collection<User> contacts=new ArrayList<User>();
	
	public boolean addContact(User user){
			contacts.add(user);
			return true;
	}
	
	public Collection<User> getAllContact(){
		return contacts;
	}
	
	public User getContactById(int id){
		Iterator<User> it=contacts.iterator();
		while (it.hasNext()) {
			User user = (User) it.next();
			if(user.getId()==id){
				return user;
			}
		}
		return null;
	}

}
