package Entity;

import java.math.BigInteger;
import java.util.ArrayList;

public class User implements EntityBase{
	private BigInteger id;
	private String login = null;
	private ClientList clientList;
	private Friend friend = null;

	public User(BigInteger id, String login, ClientList clientList, Friend friend) {
		this.id=id;
		this.login=login;
		this.clientList=clientList;
		this.friend=friend;
	}

	public Friend getFriend() {
		return friend;
	}

	public String getLogin() {
		return login;
	}

	public void addClient() {

	}

	public void removeClient() {

	}

	public ArrayList<Client> getClients(ArrayList<BigInteger> id) {
		ArrayList<Client> res=new ArrayList<Client>(id.size());
		for (BigInteger i : id) {
			for (Client cl : this.clientList.getClientList()) {
				if(cl.getUser().getId().equals(i)){
					res.add(cl);
					break;
				}
			}
		}
		return res;
	}

	public ClientList getClientList() {
		return this.clientList;
	}
	public BigInteger getId(){
		return this.id;
	}
}
