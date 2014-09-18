package connection;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;


public class Config {
	final int PORT;
	final String PATCH;
	private final File fXml;
	
	private static final Config INSTANCE =new Config();
	private Config(){
		fXml=new File("xml/config.xml");
		
		Document document = null;
		Element root=null;
		try {
			document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(fXml);
			root=document.getDocumentElement();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
		PORT=Integer.valueOf(root.getElementsByTagName("port").item(0).getTextContent());
		PATCH="D:\\Pro";
	}
	public static Config getInstance(){
		return INSTANCE;
	}

}
