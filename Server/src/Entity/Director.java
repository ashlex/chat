package Entity;

/**
 * @author Alexej
 *
 * @param <T> ���������� ���������� Builder
 * @param <R> ��� ������������ ��������
 */
public interface Director <T extends Builder,R extends EntityBase> {
	public R create(T builder);
}
