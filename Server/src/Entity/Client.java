package Entity;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {
	private User user;
	private Socket socket;
	private ObjectOutputStream oos;
	private ObjectInputStream ois;
	
	public Client(Socket s , ObjectOutputStream oos, ObjectInputStream ois, User u) {
		this.socket=s;
		this.oos=oos;
		this.ois=ois;
		this.user=u;
	}
	
	public ObjectOutputStream getObjectOutputStream(){
		return this.oos;
	}
	public ObjectInputStream getObjectInputStream() throws IOException {
		return this.ois;
	}
	public User getUser() {
		return this.user;
	}

}
