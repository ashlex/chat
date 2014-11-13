package DAO;

import java.math.BigInteger;

import Entity.UserInfo;

public interface IDaoUserInfo {
	/**
	 * @param {@link BigInteger} uid
	 * @return Возращает объект {@link UserInfo} если найден такой пользователь и null если нет 
	 */
	public UserInfo find(BigInteger uid);
	/**
	 * @param {@link UserInfo} userInfo
	 * @return Возращает {@link Integer} количество обновлённых записей
	 */
	public int update(UserInfo userInfo);
	/**
	 * @param {@link UserInfo} userInfo
	 * @return Возращает {@link Integer} идентификатор новой записи или -1 если неудача 
	 */
	public int insert(UserInfo userInfo);
	/**
	 * @param {@link UserInfo} userInfo
	 * @return {@link Integer} Количество удалённых записей
	 */
	public int remove(UserInfo userInfo);
	/**
	 * @param  {@link BigInteger} uid
	 * @return {@link Integer} Количество удалённых записей
	 */
	public int remove(BigInteger uid);

}
