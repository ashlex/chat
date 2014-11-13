package Entity;

import java.math.BigInteger;

public class UserInfo {
	private BigInteger uid;
	private String login;
	private String phoneNumber;
	private String eMail;
	
	public UserInfo(){
		
	}
	
	public UserInfo(BigInteger uid){
		
	}
	public BigInteger getUid() {
		return uid;
	}
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getEMail() {
		return eMail;
	}
	public void setEMail(String eMail) {
		this.eMail = eMail;
	}
}
