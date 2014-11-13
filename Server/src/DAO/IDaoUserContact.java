package DAO;

import java.math.BigInteger;
import java.util.ArrayList;

public interface IDaoUserContact {
	public ArrayList<BigInteger> getIdFriends(BigInteger uid);
//	public ArrayList<BigInteger> getRequestsForFriendship(BigInteger uid);
	public boolean addFriend(BigInteger uid, BigInteger uid_friend);
	/**
	 * ���� ������������ �������� ������ � ������, �� �� ������� ��� ������
	 * @param uid
	 * @return
	 */
	public ArrayList<BigInteger> getMyFriendRequests(BigInteger uid);
	/**
	 * ��� �������� ������������ ������ � ������, �� ������������ ��� �� �������
	 * @param uid
	 * @return
	 */
	ArrayList<BigInteger> getRequestsToMyFriends(BigInteger uid);
}
