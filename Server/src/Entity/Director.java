package Entity;

/**
 * @author Alexej
 *
 * @param <T> Конкретная реализация Builder
 * @param <R> Тип возращаемого значения
 */
public interface Director <T extends Builder,R extends EntityBase> {
	public R create(T builder);
}
