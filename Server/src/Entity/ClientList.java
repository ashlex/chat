package Entity;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * @author Alexej
 * @version 1.0
 * Этот класс предназначен для хранения всех клиентов которые онлайн
 */
public class ClientList {
	private Map<String, Client> list = new HashMap<String, Client>();

	private static ClientList INSTANCE=new ClientList();
	private ClientList(){}
	public synchronized static ClientList getInstance(){
		return INSTANCE;
	}
	/**
	 * Возращает список клиентов
	 * @return {@link ArrayList} список клиентов
	 */
	public ArrayList<Client> getClientList() {
		ArrayList<Client> tmp = new ArrayList<>(list.size());
		for (Entry<String, Client> cl : list.entrySet()) {
			tmp.add(cl.getValue());
		}
		return tmp;
	}

	/**
	 * Добавляет клиента в список клиентов онлайн
	 * @param login Логин клиента
	 * @param s Сокет клиента
	 * @return {@link Boolean} результат выполнения
	 */
	public boolean addClient(String login, Socket s, ObjectOutputStream oos, ObjectInputStream ois) {
		if (!this.list.containsKey(login)) {
			this.list.put(login, new Client(s,oos,ois));
			return true;
		}else{
			return false;
		}
	}

	/**
	 * Удаляет клиента из списка онлайн
	 * @param login Логин клиента
	 * @return {@link Boolean} результат выполнения
	 */
	public boolean removeClient(String login) {
		if(this.list.containsKey(login)){
			this.list.remove(login);
			return true;
		}else{
			return false;
		}
		
	}

}
