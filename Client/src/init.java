import connect.IOClient;


public class init {
	
	public static void main(String[] ar) {
		new Thread(new IOClient()).start();
        
    }

}
