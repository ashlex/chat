import static org.junit.Assert.*;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.Timer;

import org.junit.Test;

import DAO.IDaoUserInfo;
import DAO.MySQL;
import DAO.MySQLDaoUserInfo;


public class TestConnectionMySQL {
	MySQLDaoUserInfo my=new MySQLDaoUserInfo();
	@Test
	public void test() {
			Connection con;
			try {
				con = my.getConnection();

				Statement st=con.createStatement();
				
				ResultSet rs=st.executeQuery("SELECT * FROM users");
				while(rs.next()){
					System.out.println(rs.getString("login"));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
}



