package Entity;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {
	private Socket socket;
	private ObjectOutputStream oos;
	private ObjectInputStream ois;
	
	public Client(Socket s , ObjectOutputStream oos, ObjectInputStream ois) {
		this.socket=s;
		this.oos=oos;
		this.ois=ois;
	}
	
	public ObjectOutputStream getObjectOutputStream(){
		return this.oos;
	}
	public ObjectInputStream getObjectInputStream() throws IOException {
		return this.ois;
	}

}
