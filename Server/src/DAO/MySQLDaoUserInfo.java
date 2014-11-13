package DAO;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Entity.UserInfo;

public class MySQLDaoUserInfo extends MySQL implements IDaoUserInfo {
	@Override
	public UserInfo find(BigInteger uid) {
		try {
			String sql = "SELECT * FROM java.`users` WHERE id=" + uid;
			Connection con=getConnection();
			ResultSet res = con.createStatement().executeQuery(sql);
			if (res.getRow() == 0)
				return null;
			UserInfo ui = new UserInfo(uid);
			ui.setLogin(res.getString("login"));
			ui.setLogin(res.getString("phoneNumber"));
			ui.setLogin(res.getString("eMail"));
			if(!con.isClosed())con.close();
			return ui;
		} catch (SQLException e) {
			String er = "";
			// StackTraceElement [] str=e.getStackTrace();
			// for(int i=0;i<str.length;i++){
			// er+=str[i]+"\n";
			// }
			log.info(e.getMessage() + "\n" + er);
			;
			return null;
		}
	}

	@Override
	public int update(UserInfo userInfo) {
		try {
			Connection con=getConnection();
			int res=con.createStatement().executeUpdate(
					"UPDATE LOW_PRIORITY users SET login='"
							+ userInfo.getLogin() + "' , eMail='"
							+ userInfo.getEMail() + "' , phoneNumber='"
							+ userInfo.getPhoneNumber() + "'");
			if(!con.isClosed())con.close();
			return res;
		} catch (SQLException e) {
			log.severe(e.getMessage());
			return 0;
		}
	}

	@Override
	public int insert(UserInfo userInfo) {
		int i=-1,j=0;
		try {
			String query="INSERT INTO users (login,password,eMail,phoneNumber) VALUES ('"
					+ userInfo.getLogin() + "',PASSWORD('pass'),'"
					+ userInfo.getEMail() + "','"
					+ userInfo.getPhoneNumber() + "')";
			Connection con=getConnection();
			PreparedStatement pstmt = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);  
			i=pstmt.executeUpdate();  
			ResultSet keys = pstmt.getGeneratedKeys();    
			keys.next();  
			j = keys.getInt(1);
//			log.info(j+"");
			if(!con.isClosed())con.close();
		} catch (SQLException e) {
			log.severe(e.getMessage());
		}
		if(i>0){
			return j;
		}
		return -1;

	}

	@Override
	public int remove(UserInfo userInfo) {
		return remove(userInfo.getUid());
	}

	@Override
	public int remove(BigInteger uid) {
		try {
			return getConnection().createStatement().executeUpdate(
					"DELETE LOW_PRIORITY FROM users WHERE id=" + uid);
		} catch (SQLException e) {
			log.severe(e.getMessage());
			return 0;
		}
	}

}
