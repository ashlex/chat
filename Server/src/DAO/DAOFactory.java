package DAO;
import DAO.MemDao;

public class DAOFactory {
	public static Dao getDAO() {
		return MemDao.getInstance();
	}
}
