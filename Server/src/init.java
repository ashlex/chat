
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Collection;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import DAO.Dao;
import DAO.MemDao;
import Entity.Client;
import Entity.ClientList;
import Entity.HistoryMessage;
import Entity.Message;
import Entity.User;
import config.Config;
import connection.ThreadSocket;

public class init{
	private static ServerSocket ss;
	
	public static void main(String args[]) {
		
		log().info("Start Server");
		
		log().info("Load Properties...");
		
		int port = Integer.valueOf(Config.getInstance().get("port")); //�������� ���� ����� ���������� NumberFormatException
		
		ThreadPoolExecutor pool=new ThreadPoolExecutor(100,10000,10, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());
		
		try {
			ss = new ServerSocket(port);
			
//			log().info("������� ����������:\n"+ socket.getPort());
////			pool.execute(arg0);
//
//			// ����� ������� � �������� ������ ������, ������ ����� �������� �
//			// �������� ������ �������.
//			InputStream sin = socket.getInputStream();
//			OutputStream sout = socket.getOutputStream();
//
//			// ������������ ������ � ������ ���, ���� ����� ������������
//			// ��������� ���������.
//			DataInputStream in = new DataInputStream(sin);
//			DataOutputStream out = new DataOutputStream(sout);
//
//			out.writeUTF("Input ID end Password:");
//			out.flush();
//			String line = null;
			while (true) {
				log().info("�������� �������...");

				Socket socket = ss.accept(); // ���������� ������ ����� �����������
												// � ������� ��������� ����� ���-��
												// �������� � ��������
				log().info("�������� ������ ��� �������");
				ThreadSocket ts=new ThreadSocket(socket);
				pool.execute(ts);
			}
		} catch (SocketException e) {
			System.out.println("���������� ��������");
			System.out.println();
		} catch (Exception x) {
			x.printStackTrace();
		}
	}
	public static Logger log(){
		Logger log=Logger.getLogger(init.class.getClass().getName());
		return log;
	}
}
