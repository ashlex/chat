package connection;

//import init;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.StreamCorruptedException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Logger;

import Entity.BuilderMessageXML;
import Entity.Client;
import Entity.ClientList;
import Entity.DirectorMessage;
import Entity.HistoryMessage;
import Entity.Login;
import Entity.Message;
import Entity.BuilderMessage;

public class ThreadSocket implements Runnable {

	// private static final long serialVersionUID = 1L;

	private Socket socket = null;
	// private int id=0;
	private InputStream is;
	private OutputStream os;
	// private boolean free=true;
	private Message msg;
	private String login;

	public ThreadSocket(Socket s) {
		this.socket = s;
	}

	public void run() {
		try {
			log().info("Получение потока ввода");
			is = socket.getInputStream();
			log().info("Получение потока вывода");
			os = socket.getOutputStream();

			log().info("Создание обекта потока вывода");
			final ObjectOutputStream out = new ObjectOutputStream(os);
			log().info("Создание обекта потока ввода");
			final ObjectInputStream in = new ObjectInputStream(this.socket.getInputStream());

			log().info("чтение первого сообщения");
			msg = (Message) in.readObject();
			if (msg instanceof Login) {
				log().info(msg.toString());
				login=msg.getSender();
				ClientList.getInstance().addClient(login, socket, out, in);
				HistoryMessage.getInstance().addMessage(msg);
				for (Client client : ClientList.getInstance().getClientList()) {
					client.getObjectOutputStream().writeObject(msg);
					client.getObjectOutputStream().flush();
				}
			}

			while (true) {
				// BuilderMessage bm;
				// bm = new BuilderMessageXML(in.readUTF());
				// DirectorMessage dm=new DirectorMessage();
				msg = (Message) in.readObject();
				broadcast(ClientList.getInstance().getClientList(), msg);

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ClientList.getInstance().removeClient(login);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void broadcast(ArrayList<Client> OnLineCliens, Message m)
			throws IOException {
		for (Client client : OnLineCliens) {
			if(client)
			client.getObjectOutputStream().writeObject(m);
			client.getObjectOutputStream().flush();
		}
	}

	public static Logger log() {
		Logger log = Logger.getLogger(ThreadSocket.class.getClass().getName());
		return log;
	}
}
