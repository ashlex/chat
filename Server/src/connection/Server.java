package connection;

import java.net.*;
import java.io.*;

import DAO.Dao;
import DAO.MemDao;
import Entity.User;

public class Server {
	private static ServerSocket ss;

	public static void main(String[] ar) {
		int port=Config.getInstance().PORT;
		
		try {
			ss = new ServerSocket(port);
			System.out.println("�������� �������...");

			Socket socket = ss.accept(); // ���������� ������ ����� �����������
											// � ������� ��������� ����� ���-��
											// �������� � ��������
			System.out.println("������� ����������");
			System.out.println();

			// ����� ������� � �������� ������ ������, ������ ����� �������� �
			// �������� ������ �������.
			InputStream sin = socket.getInputStream();
			OutputStream sout = socket.getOutputStream();

			// ������������ ������ � ������ ���, ���� ����� ������������
			// ��������� ���������.
			DataInputStream in = new DataInputStream(sin);
			DataOutputStream out = new DataOutputStream(sout);

			out.writeUTF("Input ID end Password:");
			out.flush();
			String line = null;
			while (true) {

				System.out.println("�������� ���������");
				System.out.println();
				
				line = in.readUTF(); // ������� ���� ������ ������� ������
										// ������.

				String arg[] = line.split(" ");
				System.out.println(line);
				System.out.println();
				

				Dao d = MemDao.getInstance();
				try {
					d.login(Integer.valueOf(arg[0]), arg[1]);
					out.writeUTF("�� ������� ������ �����������");
				} catch (IllegalArgumentException e) {
					User u = new User(d.getNewId(), arg[1]);
					u.setName("User_" + u.getId());
					d.addUser(u);
					out.writeUTF("�� ����������������. ��� id:" + u.getId()
							+ " ������:" + arg[1]);
				}
				out.flush();
			}
		} catch (SocketException e) {
			System.out.println("���������� ��������");
			System.out.println();
		} catch (Exception x) {
			x.printStackTrace();
		}
	}
}