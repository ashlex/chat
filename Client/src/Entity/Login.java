package Entity;

import java.util.Random;


public class Login extends Message {
	
	private static final long serialVersionUID = 1L; //что это? И зачем?

	public Login() {
		super(Math.random()+"", "login");
	}

}
