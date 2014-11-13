import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.math.BigInteger;
import java.util.ArrayList;

import org.junit.Ignore;
import org.junit.Test;

import DAO.DaoFactory;
import DAO.IDaoUserContact;


public class TestMySQLDaoUserContact {
	DaoFactory df=DaoFactory.getDaoFactory(DaoFactory.MYSQL);
	IDaoUserContact d=df.getUserContactDao();
	
	@Ignore
	@Test
	public void testAddFriend() {
		assertTrue(d.addFriend(BigInteger.valueOf(1010), BigInteger.valueOf(1011)));
		assertTrue(d.addFriend(BigInteger.valueOf(1010), BigInteger.valueOf(1001)));
		assertTrue(d.addFriend(BigInteger.valueOf(1010), BigInteger.valueOf(1013)));
		assertTrue(d.addFriend(BigInteger.valueOf(1010), BigInteger.valueOf(1020)));
		assertFalse(d.addFriend(BigInteger.valueOf(1010), BigInteger.valueOf(1)));
		assertFalse(d.addFriend(BigInteger.valueOf(1), BigInteger.valueOf(1010)));
		assertTrue(d.addFriend(BigInteger.valueOf(1011), BigInteger.valueOf(1010)));
		assertTrue(d.addFriend(BigInteger.valueOf(1001), BigInteger.valueOf(1010)));
		assertTrue(d.addFriend(BigInteger.valueOf(1013), BigInteger.valueOf(1010)));
		assertTrue(d.addFriend(BigInteger.valueOf(1013), BigInteger.valueOf(1020)));
		assertTrue(d.addFriend(BigInteger.valueOf(1013), BigInteger.valueOf(1015)));
		assertTrue(d.addFriend(BigInteger.valueOf(1015), BigInteger.valueOf(1013)));
		assertTrue(d.addFriend(BigInteger.valueOf(1015), BigInteger.valueOf(1011)));
		assertTrue(d.addFriend(BigInteger.valueOf(1011), BigInteger.valueOf(1015)));
		assertTrue(d.addFriend(BigInteger.valueOf(1020), BigInteger.valueOf(1015)));
		assertTrue(d.addFriend(BigInteger.valueOf(1015), BigInteger.valueOf(1020)));
	}
	
	@Test
	public void testGetMyFriendRequests(){
		System.out.println("MyFriendRequests");
		ArrayList<BigInteger> test=d.getMyFriendRequests(BigInteger.valueOf(1010));
		for(BigInteger uid:test){
			System.out.println(uid);
		}
	}
	
	@Test
	public void testGetIdFriends(){
		System.out.println("IdFriends");
		ArrayList<BigInteger> test=d.getIdFriends(BigInteger.valueOf(1010));
		for(BigInteger uid:test){
			System.out.println(uid);
		}
	}
	
	@Test
	public void testGetRequestsToMyFriends(){
		System.out.println("RequestsToMyFriends");
		ArrayList<BigInteger> test=d.getRequestsToMyFriends(BigInteger.valueOf(1010));
		for(BigInteger uid:test){
			System.out.println(uid);
		}
	}
	
	@Test
	public void testGetRequestsToMyFriends2(){
		Integer i = 42;
		Integer j = 42;
		java.lang.System.out.println(i + " = " + j + " : " + (i == j));
		i = 142;
		j = 142;
		java.lang.System.out.println(i + " = " + j + " : " + (i == j));

	}

}



