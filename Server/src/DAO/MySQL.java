package DAO;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

import config.Config;

/**
 * Класс реализующий соединение с базой данных
 * 
 * @author Alexej
 * 
 */
public class MySQL {
	Logger log = Logger.getLogger(MySQL.class.getName());
	Config conf = Config.getInstance();
	final private String DATA_BASE_NAME = conf.get("DATA_BASE_NAME");
	final private String DATA_BASE_PORT = conf.get("DATA_BASE_PORT");
	final private String DATA_BASE_HOST = conf.get("DATA_BASE_HOST");
	final private String DATA_BASE_LOGIN = conf.get("DATA_BASE_LOGIN");
	final private String DATA_BASE_PASS = conf.get("DATA_BASE_PASS");
	final private String DATA_BASE_DRIVER = conf.get("DATA_BASE_DRIVER");
	// final private int
	// DATA_BASE_CONNECT_TIME_OUT=Integer.valueOf(conf.get("DATA_BASE_CONNECT_TIME_OUT")==null?"0":conf.get("DATA_BASE_CONNECT_TIME_OUT"));

	public MySQL() {
		try {
			Class.forName(DATA_BASE_DRIVER);
		} catch (ClassNotFoundException e) {
			log.severe(e.getMessage());
		}
	}

	/**
	 * Соединяется с базой данных
	 * 
	 * @return {@link Connection}
	 * @throws SQLException
	 */
	public Connection getConnection() {
		Connection connect = null;

		String url = "jdbc:mysql://" + DATA_BASE_HOST + ":" + DATA_BASE_PORT
				+ "/" + DATA_BASE_NAME;
		try {
			connect = DriverManager.getConnection(url, DATA_BASE_LOGIN,
					DATA_BASE_PASS);
		} catch (SQLException e) {
			log.severe(e.getMessage());
		}

		return connect;
	}

	public ResultSet select(String sql) {
		try {
			Connection con = getConnection();
			// log.info(con.getSchema());
			Statement st = con.createStatement();
			// log.info(st.executeQuery("SELECT 1").getRow()+"");
			String[] parseSql = sql.split(" ");
			ResultSet rs;
			if (parseSql[0].equalsIgnoreCase("insert")) {
				st.executeUpdate(sql);
				return null;
			} else {
				rs = st.executeQuery(sql);
			}
			// System.out.println(rs.getRow());
			return rs;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			String er = "";
			StackTraceElement[] str = e.getStackTrace();
			for (int i = 0; i < str.length; i++) {
				er += str[i] + "\n";
			}
			log.severe(e.getMessage() + "\n" + er);
			return null;
		}
	}
//	public int insert(String tableName, Map<String,String> values){
//		
//	}

	protected BigInteger returnGenerateKeys() {
		log.info(Statement.RETURN_GENERATED_KEYS+"");
		return BigInteger.valueOf(Statement.RETURN_GENERATED_KEYS);
	}
}
