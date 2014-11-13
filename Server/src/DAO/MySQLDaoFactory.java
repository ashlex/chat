package DAO;

public class MySQLDaoFactory extends DaoFactory {

	@Override
	public IDaoUserInfo getUserInfoDao() {
		return new MySQLDaoUserInfo();
	}

	@Override
	public IDaoUserContact getUserContactDao() {
		return new MySQLDaoUserContact();
	}

}
