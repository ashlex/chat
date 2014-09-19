package connection;

import java.math.BigInteger;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ListSocket {
	private BigInteger count=BigInteger.valueOf(0);
	private Collection<Socket> list=new ArrayList<Socket>();
	
	private static final ListSocket INSTANCE=new ListSocket();
	private ListSocket(){}
	
	public static ListSocket getInstance() {
		return INSTANCE;
	}
	
	public boolean addThreadSocket(Socket s){
		list.add(s);
		return true;
	}
	
	public ThreadSocket getThreadSocket(Socket s){
		Iterator<Socket> it=list.iterator();
		while (it.hasNext()) {
			Socket socket = (Socket) it.next();
			if(socket.equals(s)){
				
			}
		}
	}
	
	public boolean removeThreadSocket(int id){
		list.remove(id);
		return true;
	}

}
