package DAO;

import java.math.BigInteger;

import Entity.UserInfo;

public interface IDaoUserInfo {
	/**
	 * @param {@link BigInteger} uid
	 * @return ��������� ������ {@link UserInfo} ���� ������ ����� ������������ � null ���� ��� 
	 */
	public UserInfo find(BigInteger uid);
	/**
	 * @param {@link UserInfo} userInfo
	 * @return ��������� {@link Integer} ���������� ���������� �������
	 */
	public int update(UserInfo userInfo);
	/**
	 * @param {@link UserInfo} userInfo
	 * @return ��������� {@link Integer} ������������� ����� ������ ��� -1 ���� ������� 
	 */
	public int insert(UserInfo userInfo);
	/**
	 * @param {@link UserInfo} userInfo
	 * @return {@link Integer} ���������� �������� �������
	 */
	public int remove(UserInfo userInfo);
	/**
	 * @param  {@link BigInteger} uid
	 * @return {@link Integer} ���������� �������� �������
	 */
	public int remove(BigInteger uid);

}
