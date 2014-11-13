package Entity;

import java.math.BigInteger;

public abstract class BuilderUser implements Builder {
	protected BigInteger id;
	protected String login;
	protected ClientList clientList;
	protected Friend friend;
	
	@Override
	public User build() {
		return new User(this.id,this.login,this.clientList,this.friend);
	}
	
	public abstract void setId();
	public abstract void setLogin();
	public abstract void setClientList();
	public abstract void setFriend();
}
