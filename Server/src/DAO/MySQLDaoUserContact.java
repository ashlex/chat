package DAO;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class MySQLDaoUserContact extends MySQL implements IDaoUserContact {

	@Override
	public ArrayList<BigInteger> getIdFriends(BigInteger uid) {
		Connection con = getConnection();
		String sql = "SELECT  t1.uid AS uid_friend  FROM "
				+ "(SELECT  *  FROM `friends` WHERE `uid_friend`="+uid+") AS t1 "
				+ "LEFT JOIN friends AS t2 ON(t1.uid_friend=t2.uid) "
				+ "WHERE t1.uid=t2.`uid_friend`";
		ArrayList<BigInteger> result = new ArrayList<BigInteger>();
		try {
			ResultSet res = con.createStatement().executeQuery(sql);
			while(res.next()){
				result.add(new BigInteger(res.getString("uid_friend")));
			}
		} catch (SQLException e) {
			log.info(e.getMessage()+"\nSQL:"+sql);
		}
		try {
			con.close();
		} catch (SQLException e) {
			log.info(e.getMessage());
		}

		return result;
	}

	@Override
	public ArrayList<BigInteger> getMyFriendRequests(BigInteger uid) {
		Connection con = getConnection();
		String sql = "SELECT  t1.uid_friend FROM friends AS t1 "
				+ "LEFT JOIN ("
				+ "SELECT  *  FROM `friends` WHERE `uid_friend`="+uid
				+ ") AS t2   ON(t1.uid_friend=t2.uid)  "
				+ "WHERE t1.uid="+uid+" AND t2.id IS NULL";
		ArrayList<BigInteger> result = new ArrayList<BigInteger>();
		try {
			ResultSet res = con.createStatement().executeQuery(sql);
			while(res.next()){
				result.add(new BigInteger(res.getString("uid_friend")));
			}
		} catch (SQLException e) {
			log.info(e.getMessage()+"\nSQL:"+sql);
		}
		try {
			con.close();
		} catch (SQLException e) {
			log.info(e.getMessage());
		}

		return result;
	}
	
	@Override
	public ArrayList<BigInteger> getRequestsToMyFriends(BigInteger uid) {
		Connection con = getConnection();
		String sql = "SELECT  t1.uid AS uid FROM friends AS t1 "
				+ "LEFT JOIN ("
				+ "SELECT  *  FROM `friends` WHERE `uid`="+uid
				+ ") AS t2  ON(t1.uid=t2.uid_friend) "
				+ "WHERE t1.uid_friend="+uid+" AND t2.id IS NULL";
		ArrayList<BigInteger> result = new ArrayList<BigInteger>();
		try {
			ResultSet res = con.createStatement().executeQuery(sql);
//			log.info(sql);
			while(res.next()){
				result.add(new BigInteger(res.getString("uid")));
			}
		} catch (SQLException e) {
			log.info(e.getMessage()+"\nSQL:"+sql);
		}
		try {
			con.close();
		} catch (SQLException e) {
			log.info(e.getMessage());
		}
		
		return result;
	}
	
	@Override
	public boolean addFriend(BigInteger uid, BigInteger uid_friend) {
		boolean result = false;
		String sql=null;
		Connection con = getConnection();
		Statement st;
		ResultSet resultSet=null;
		try {
			st = con.createStatement();
			sql="SELECT COUNT(id) FROM friends WHERE uid=" + uid + " AND uid_friend="+ uid_friend;
			resultSet=st.executeQuery(sql);
			resultSet.first();
			if (resultSet.getInt(1) > 0){
				throw new SQLException("This record exist");
			}
			sql="SELECT COUNT(id) FROM users WHERE id=" + uid + " OR id="+ uid_friend;
			resultSet=st.executeQuery(sql);
			resultSet.first();
			if (resultSet.getInt(1) < 2){
				throw new SQLException("One or both of the users not exists");
			}
			sql = "INSERT INTO friends (uid,uid_friend) VALUES('"+uid+"','"+uid_friend+"')";
			int res = con.createStatement().executeUpdate(sql);
			if(res==1) result=true;
		} catch (SQLException e) {
			log.info(e.getMessage()+"\nSQL:"+sql);
		}
		return result;
	}


}
