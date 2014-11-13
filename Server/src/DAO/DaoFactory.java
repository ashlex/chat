package DAO;

public abstract class DaoFactory {
	public final static int MYSQL=1;
	public final static int FILE=2;
	
	public static DaoFactory getDaoFactory(int typeDao) {
		DaoFactory df=null;
		switch (typeDao) {
		case 1:
			df=new MySQLDaoFactory();
			break;
		case 2:
			df=new FileDaoFactory();
			break;
		default:
			df=new MySQLDaoFactory();
			break;
		}
		return df;
	}
	
	public abstract IDaoUserInfo getUserInfoDao();
	public abstract IDaoUserContact getUserContactDao();
}
