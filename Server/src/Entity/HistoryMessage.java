package Entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

import config.Config;

public class HistoryMessage {
	Logger log=Logger.getLogger("HistoryMessage");
	private List<Message> history = new ArrayList<Message>();
//	private User user;
//	private Dao d;
	private static HistoryMessage INSTANCE=new HistoryMessage();
	private HistoryMessage() {}
	public synchronized static HistoryMessage getInstance(){
		
		return INSTANCE;
	}

	/**
	 * @param m
	 * @return {@link Boolean}
	 */
	public boolean addMessage(Message m) {
		if (m!= null) {
			if(Integer.valueOf(Config.getInstance().get("max_histori_length"))==this.history.size()){
				this.history.remove(0);
			}
			this.history.add(m);
			return true;
		} else {
			return false;
		}
	}
	public Collection<Message> getHistory(){
		return history;
	}
	public void clearHistory(){
		history.clear();
	}
	
	@Override
	protected void finalize() throws Throwable {
//		d.
		super.finalize();
	}
}
