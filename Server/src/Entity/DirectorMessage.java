package Entity;

public class DirectorMessage {
	
	public Message createMessage(BuilderMessage bm){
		bm.setId();
		bm.setSender();
		bm.setRecipient();
		bm.setDate();
		bm.setMessage();
		return bm.build();
	}

}
