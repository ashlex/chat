package connection;

import java.util.HashMap;
import java.util.Map;

public class ListSocket {
	private Map<Integer, ThreadSocket> list=new HashMap<Integer, ThreadSocket>();
	
	private static final ListSocket INSTANCE=new ListSocket();
	private ListSocket(){}
	
	public static ListSocket getInstance() {
		return INSTANCE;
	}
	
	public boolean addThreadSocket(ThreadSocket ts){
		list.put((int)ts.getId(), ts);
		return true;
	}
	
	public ThreadSocket getThreadSocket(int id){
		return list.get(id);
	}
	
	public boolean removeThreadSocket(int id){
		list.remove(id);
		return true;
	}

}
