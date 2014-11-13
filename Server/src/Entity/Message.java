package Entity;

import java.io.Serializable;
import java.util.GregorianCalendar;

public class Message implements Serializable , EntityBase{
	private static final long serialVersionUID = 1L;
	
	private String message=null;
	private String sender=null;  //отправитель
	private String recipient=null; // получатель
	private GregorianCalendar date;
	
	Message(int id,String sender, String recipient, GregorianCalendar date, String message) {
		this.sender=sender;
		this.recipient=recipient;
		this.date=date;
		this.message=message;
	}
	/**
	 * Этот конструктор используется в первом сообщении при соединении с сервером
	 * @param sender Логин отправителя
	 * @param message Сообщение (login)
	 */
	public Message(String sender, String message) {
		this.sender=sender;
		this.message=message;
	}
	
	public String getSender(){
		return sender;
	}
	
	public String getRecipient(){
		return recipient;
	}
	
	public GregorianCalendar getDate(){
		return date;
	}
	
	@Override
	public String toString(){
		return sender+":"+message;
	}
	

}
