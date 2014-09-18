package Entity;

import java.util.GregorianCalendar;

public class Message extends EntityBase{
	private String message=null;
	private User sender=null; 
	private User recipient=null;
	private GregorianCalendar date;
	
	Message(int id,User sender, User recipient, GregorianCalendar date, String message) {
		super(id);
		this.sender=sender;
		this.recipient=recipient;
		this.date=date;
		this.message=message;
	}
	
	public User getSender(){
		return sender;
	}
	
	public User getRecipient(){
		return recipient;
	}
	
	public GregorianCalendar getDate(){
		return date;
	}
	
	@Override
	public String toString(){
		return message;
	}
	

}
