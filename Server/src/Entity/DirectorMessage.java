package Entity;

public class DirectorMessage implements Director<BuilderMessage, Message> {
	
	public Message create(BuilderMessage bm){
		bm.setId();
		bm.setSender();
		bm.setRecipient();
		bm.setDate();
		bm.setMessage();
		return bm.build();
	}


}
