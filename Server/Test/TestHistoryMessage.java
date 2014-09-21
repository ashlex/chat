import static org.junit.Assert.*;

import org.junit.Test;

import Entity.HistoryMessage;
import Entity.Message;


public class TestHistoryMessage {
	String MESSAGE="Hello ";
	HistoryMessage hm;
	
	@Test
	public void testGetHistoryMessage() {
		hm=HistoryMessage.getInstance();
		Message [] msg = new Message[4];
		msg[0]=new Message("root", MESSAGE+"root");
		msg[1]=new Message("al", MESSAGE+"al");
		msg[2]=new Message("x", MESSAGE+"x");
		msg[3]=new Message("ftp", MESSAGE+"ftp");
		assertTrue(hm.addMessage(msg[0]));
		assertTrue(hm.addMessage(msg[1]));
		assertTrue(hm.addMessage(msg[2]));
		assertTrue(hm.addMessage(msg[3]));
		hm.clearHistory();
		assertEquals(0, hm.getHistory().size());
	}
	@Test
	public void testAddMessage() {
		hm=HistoryMessage.getInstance();
		Message [] msg = new Message[4];
		msg[0]=new Message("root", MESSAGE+"root");
		msg[1]=new Message("al", MESSAGE+"al");
		msg[2]=new Message("x", MESSAGE+"x");
		msg[3]=new Message("ftp", MESSAGE+"ftp");
		assertTrue(hm.addMessage(msg[0]));
		assertTrue(hm.addMessage(msg[1]));
		assertTrue(hm.addMessage(msg[2]));
		assertTrue(hm.addMessage(msg[3]));
//		System.out.println(hm.getHistory().size());
		assertEquals(3, hm.getHistory().size());
		assertTrue(hm.getHistory().contains(msg[1]));
		assertTrue(hm.getHistory().contains(msg[3]));
		assertEquals(3, hm.getHistory().size());
	}

}
