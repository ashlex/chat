
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import DAO.Dao;
import DAO.MemDao;
import Entity.User;
import connection.ThreadSocket;

public class init {
	private static ServerSocket ss;
	
	public static void main(String[] ar) {
		
		log().info("Start Server");
		
		log().info("Load Properties...");
		int port = Config.getInstance().getPort();
		ThreadPoolExecutor pool=new ThreadPoolExecutor(100,10000,10, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());
		
		try {
			ss = new ServerSocket(port);
			log().info("Ожидание клиента...");

			Socket socket = ss.accept(); // заставляем сервер ждать подключений
											// и выводим сообщение когда кто-то
											// связался с сервером
			log().info("Создано соединение:\n"+ socket.getPort());
			ThreadSocket s=new ThreadSocket(ss.,ss);
			pool.execute(arg0);

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
	public static Logger log(){
		Logger log=Logger.getLogger(init.class.getClass().getName());
		return log;
	}
}
