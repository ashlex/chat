import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import DAO.Dao;
import DAO.MemDao;
import Entity.User;


public class TestDao {
	Dao d=MemDao.getInstance();
	
	@Test
	public void testAddUser() {
		User u1=new User(1, "pass");
		assertTrue(d.addUser(u1));
		assertEquals(u1, d.getUser(1));
		assertTrue(d.removeUser(1));
	}
	
	@Test
	public void testLogin(){
		User u1=new User(1, "pass");
		u1.setName("Bob");
		assertTrue(d.addUser(u1));
		assertFalse(d.getUser(1).getStat());
		assertEquals(u1, d.login(1, "pass"));
		assertTrue(d.getUser(1).getStat());
		assertTrue(d.removeUser(1));
	}

	@Test
	public void testGetNewId(){
		User u1=new User(1, "pass");
		u1.setName("Bob");
		assertTrue(d.addUser(u1));
		assertEquals(2, d.getNewId());
		assertTrue(d.removeUser(1));
	}

}
