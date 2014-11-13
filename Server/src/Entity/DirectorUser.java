package Entity;

public class DirectorUser implements Director<BuilderUser, User> {
	public User create(BuilderUser bu) {
		return bu.build();
	}
}
