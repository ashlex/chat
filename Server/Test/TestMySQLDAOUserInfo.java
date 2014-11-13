import static org.junit.Assert.*;

import java.math.BigInteger;

import org.junit.Test;

import DAO.IDaoUserInfo;
import DAO.MySQLDaoUserInfo;
import Entity.UserInfo;


public class TestMySQLDAOUserInfo {
	IDaoUserInfo d=new MySQLDaoUserInfo();
	@Test
	public void testInsert() {

//		d.find(BigInteger.valueOf(1001));
		UserInfo ui=new UserInfo();
		for(int i=26;i<27;i++){
			ui.setLogin("test1_User"+i);
			ui.setEMail("test1_EMail"+i);
			ui.setPhoneNumber("96052808"+i);
			int uid=d.insert(ui);
			System.out.println(uid);
		}
//		assertEquals(1, (d.remove(BigInteger.valueOf(uid))));
	}


}
