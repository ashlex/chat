package Entity;

public class User extends EntityBase{
	String password=null;
	String login=null;
	ContactList cl=null;
	boolean stat=false;
	
	public User(int id, String pass) {
		super(id);
		password=pass;
		cl=new ContactList();
	}
	
	public boolean setPassword(String newPass){
		password=newPass;
		return true;
	}
	
	public ContactList getContactList(){
		return cl;
	}
	
	public String getLogin(){
		return login;
	}
	
//	public boolean setName(String name){
//		this.login=name;
//		return true;
//	}
	
	public boolean validation(String pass){
		if(password.equals(pass)){
			stat=true;
			return true;
		}
		return false;
		
	}
	
	public boolean getStat(){
		return stat;
	}
	
}
