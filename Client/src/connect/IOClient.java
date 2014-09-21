package connect;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import Entity.Login;
import Entity.Message;
import config.Config;

public class IOClient implements Runnable {
    private Socket socket;
    private int serverPort = Integer.parseInt(Config.getInstance().get("port")); // ����� ����������� ����� ������� ���� � �������� ������������� ������.
    private String host = Config.getInstance().get("host"); // ��� host, ��� ����������� ���� ��������� ���������. 
    private InetAddress ipAddress;
    private ObjectOutputStream out;
    private ObjectInputStream in;

	@Override
	public void run() {
		try {
		    this.ipAddress = InetAddress.getByName(host);
		    this.socket = new Socket(ipAddress, serverPort);
	        System.out.println("���������� ������������");
		    
		    in = new ObjectInputStream(socket.getInputStream());
		    out = new ObjectOutputStream(socket.getOutputStream());
	        System.out.println("������ ���� ������ ��������");
		    out.writeObject(new Login());
	        System.out.println("����������� ��������� �����");
		    BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
		    String line = null;
		    new Thread(new AsynchronousInputThread()).start();
	        System.out.println("������� ����� ������������ ������");
		    while (true) {

		        
		        line = keyboard.readLine(); // ���� ���� ������������ ������ ���-�� � ������ ������ Enter.
		        System.out.println("Sending this line to the server...");
		        out.writeObject(new Message("u1", line)); // �������� ��������� ������ ������ �������.
		        out.flush(); // ���������� ����� ��������� �������� ������.
//		        line = in.readUTF(); // ���� ���� ������ ������� ������ ������.
//		        System.out.println("The server was very polite. It sent me this : " + line);
//		        System.out.println("Looks like the server is pleased with us. Go ahead and enter more lines.");
//		        System.out.println();
		    }
		    }catch (UnknownHostException e) {
				// TODO Auto-generated catch block
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("Server not found...");
				System.exit(0);
			}
		
	}
	
    /**
     * ����� ����������� ����� 
     */
    public synchronized void close(String s) {
        if (!socket.isClosed()) { 
            try {
                socket.close();
                System.out.println(s);
                System.exit(0);
            } catch (IOException ignored) {
                ignored.printStackTrace();
            }
        }
    }
    public synchronized void close(){
    	close("Unknown error. Connection is closed.");
    }
    
	
	/**
	 * ����� ������������ ��� ������������ ������ ������ �� ������� 
	 * � ����� ��� �� �������
	 * @author Alexej
	 *
	 */
	private class AsynchronousInputThread implements Runnable{
		Message msg;
		@Override
		public void run() {
			while(!socket.isClosed()){
				msg=null;
				try {
					msg=(Message) in.readObject();
					if(msg!=null){
						System.out.println(msg);
					}
				} catch (ClassNotFoundException e) {
					System.out.println("Transmission error messages");
				} catch (IOException e) {
					close();
				}
			}
		}

	}
}
