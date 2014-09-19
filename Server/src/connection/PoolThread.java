package connection;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;

public class PoolThread {
	private ArrayList<ThreadSocket> listFree=new ArrayList<ThreadSocket>(); // свободные потоки
	private ArrayList<ThreadSocket> list=new ArrayList<ThreadSocket>(); //работающие потоки
	private int countThread=0;
	private int countFreeThread=0;
	private static final PoolThread INSTANCE=new PoolThread();
	private PoolThread(){}
	public static PoolThread getInstance(){
		return INSTANCE;
	}
			
	public void createThread(){
		listFree.add(new ThreadSocket());
	}
	
	public ThreadSocket getThread(int id){
		Iterator<ThreadSocket> it=list.iterator();
		while (it.hasNext()) {
			ThreadSocket threadSocket = (ThreadSocket) it.next();
			if(threadSocket.getId()==id){
				return threadSocket;
			}
		}
		return null;
	}
	
	public void acquireThread(int id, Socket s){
		ThreadSocket ts;
		if(listFree.size()>0){
			ts=listFree.get(listFree.size()-1);
			ts.load(id, s);
			listFree.remove(ts);
			list.add(ts);
		}else{
			ts=new ThreadSocket();
			ts.load(id, s);
			list.add(ts);
		}
		ts.start();
	}
	
	public void continueThread(int id){
//		ThreadSocket ts=
	}
	
	
	
	
	
	
}
