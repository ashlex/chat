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
			System.out.println("Ожидание клиента...");

			Socket socket = ss.accept(); // заставляем сервер ждать подключений
											// и выводим сообщение когда кто-то
											// связался с сервером
			System.out.println("Создано соединение");
			System.out.println();

			// Берем входной и выходной потоки сокета, теперь можем получать и
			// отсылать данные клиенту.
			InputStream sin = socket.getInputStream();
			OutputStream sout = socket.getOutputStream();

			// Конвертируем потоки в другой тип, чтоб легче обрабатывать
			// текстовые сообщения.
			DataInputStream in = new DataInputStream(sin);
			DataOutputStream out = new DataOutputStream(sout);

			out.writeUTF("Input ID end Password:");
			out.flush();
			String line = null;
			while (true) {

				System.out.println("Ожидание сообщения");
				System.out.println();
				
				line = in.readUTF(); // ожидаем пока клиент пришлет строку
										// текста.

				String arg[] = line.split(" ");
				System.out.println(line);
				System.out.println();
				

				Dao d = MemDao.getInstance();
				try {
					d.login(Integer.valueOf(arg[0]), arg[1]);
					out.writeUTF("Вы успешно прошли авторизацию");
				} catch (IllegalArgumentException e) {
					User u = new User(d.getNewId(), arg[1]);
					u.setName("User_" + u.getId());
					d.addUser(u);
					out.writeUTF("Вы зарегистрированы. Ваш id:" + u.getId()
							+ " пароль:" + arg[1]);
				}
				out.flush();
			}
		} catch (SocketException e) {
			System.out.println("Соединение прервано");
			System.out.println();
		} catch (Exception x) {
			x.printStackTrace();
		}
	}
}