package Entity;

import java.io.IOException;
import java.util.GregorianCalendar;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import DAO.*;

public class BuilderMessageXML extends BuilderMessage {

	private SAXHandler handler;
	Dao d= MemDao.getInstance();
	
	public BuilderMessageXML(String is) {
		SAXParserFactory factory = SAXParserFactory.newInstance();
	    SAXParser parser = null;
		try {
			parser = factory.newSAXParser();
		} catch (ParserConfigurationException e1) {
			e1.printStackTrace();
		} catch (SAXException e1) {
			e1.printStackTrace();
		}

		handler= new SAXHandler();
	    try {
			parser.parse(is, handler);
		} catch (SAXException | IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void setId() {
		id=0;
	}

	@Override
	public void setSender() {
		sender=d.getUser(handler.getSender());
	}

	@Override
	public void setRecipient() {
		recipient=d.getUser(handler.getRecipient());
	}

	@Override
	public void setDate() {
		date=new GregorianCalendar();
	}

	@Override
	public void setMessage() {
		message=handler.getMessage();
	}

	public class SAXHandler extends DefaultHandler {
		private String message;
		private int sender = 0;
		private int recipient = 0;
		private String var=null;

		public String getMessage(){
			return message;
		}
		public int getSender(){
			return sender;
		}
		public int getRecipient(){
			return recipient;
		}
		
		@Override
		public void startDocument() throws SAXException {
		}

		@Override
		public void startElement(String uri, String localName, String qName,
				Attributes attributes) throws SAXException {
			var=qName;
		}

		@Override
		public void characters(char[] ch, int start, int length)
				throws SAXException {
			switch (var) {
			case "sender":
				sender=Integer.valueOf(new String(ch, start, length));
				break;
			case "recipient":
				recipient=Integer.valueOf(new String(ch, start, length));
				break;
			case "message":
				message=new String(ch, start, length);
				break;
			default:
				break;
			}

		}

		@Override
		public void endElement(String uri, String localName, String qName)
				throws SAXException {
		}

		@Override
		public void endDocument() throws SAXException {
		}
	}
}
