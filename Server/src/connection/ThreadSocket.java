package connection;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import Entity.BuilderMessageXML;
import Entity.DirectorMessage;
import Entity.Message;
import Entity.BuilderMessage;

public class ThreadSocket extends Thread implements IPoolThread{
	
	private static final long serialVersionUID = 1L;

	private Socket socket=null;
	private int id=0;
	private DataOutputStream out=null;
	private DataInputStream in=null;
	private Message message=null;
	private boolean free=true;
	
	
	public void load(Socket s) {
		socket=s;
		this.id=id;
		free=false;
	}
	
	public void run(){
		InputStream is;
		OutputStream os;
		try {
			is = socket.getInputStream();
			os = socket.getOutputStream();
			in=new DataInputStream(is);
			out=new DataOutputStream(os);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while(true){
			BuilderMessage bm;
			try {
				bm = new BuilderMessageXML(in.readUTF());
				DirectorMessage dm=new DirectorMessage();
				message= dm.createMessage(bm);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	public long getId(){
		return this.id;
	}
	
	public void sendMessage(String str) throws IOException{
		out.writeUTF(str);
		out.flush();
	}

	@Override
	public void resset() {
		socket=null;
		id=0;
		message=null;
		in=null;
		out=null;
		free=true;
	}
	
	public boolean isFree(){
		return free;
	}

}
