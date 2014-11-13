package Entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class Friend {
	private Collection<User> friends=new ArrayList<User>();
	
	public Friend(User u) {
		
	}
	
	public boolean addFriend(User user){
			friends.add(user);
			return true;
	}
	public boolean removeFriend(User user){
		Iterator<User> it=friends.iterator();
		while (it.hasNext()) {
			User user2 = (User) it.next();
			if(user2.equals(user)){
				it.remove();
				return true;
			}
		}
		return false;
	}
	
	public ArrayList<User> getAllFriends(){
		return (ArrayList<User>) friends;
	}
	
	public ArrayList<User> getOnLineUser(ClientList cl) {
		ArrayList<User> list=new ArrayList<User>(cl.getClientList().size());
		for (Client client:cl.getClientList()) {
			for(User u:friends){
				if(u.equals(client.getUser())){
					list.add(u);
					break;
				}
			}
		}
		return list;
	}

}
