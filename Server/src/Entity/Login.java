package Entity;


public class Login extends Message {
	
	private static final long serialVersionUID = 1L; //��� ���? � �����?

	public Login(String login) {
		super(login, "login");
	}

}
