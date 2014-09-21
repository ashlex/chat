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
 * ���� ����� ������������ ��� �������� ���� �������� ������� ������
 */
public class ClientList {
	private Map<String, Client> list = new HashMap<String, Client>();

	private static ClientList INSTANCE=new ClientList();
	private ClientList(){}
	public synchronized static ClientList getInstance(){
		return INSTANCE;
	}
	/**
	 * ��������� ������ ��������
	 * @return {@link ArrayList} ������ ��������
	 */
	public ArrayList<Client> getClientList() {
		ArrayList<Client> tmp = new ArrayList<>(list.size());
		for (Entry<String, Client> cl : list.entrySet()) {
			tmp.add(cl.getValue());
		}
		return tmp;
	}

	/**
	 * ��������� ������� � ������ �������� ������
	 * @param login ����� �������
	 * @param s ����� �������
	 * @return {@link Boolean} ��������� ����������
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
	 * ������� ������� �� ������ ������
	 * @param login ����� �������
	 * @return {@link Boolean} ��������� ����������
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
