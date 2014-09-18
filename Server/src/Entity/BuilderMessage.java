package Entity;

import java.util.GregorianCalendar;

public abstract class BuilderMessage{
	protected int id=0;
	protected String message;
	protected User sender=null;
	protected User recipient=null;
	protected GregorianCalendar date=null;
	
	public Message build(){
		return new Message(id,sender,recipient,date,message);
	}
	public abstract void setId();
	public abstract void setSender();
	public abstract void setRecipient();
	public abstract void setDate();
	public abstract void setMessage();
}
