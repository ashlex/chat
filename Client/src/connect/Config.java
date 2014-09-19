package connect;

import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import config.XMLParse;


/**
 * @author Alexej
 * @version 1.0
 * Отвечает за загрузку настроек
 */
public class Config {
	private static XMLParse properties;
	private static final Config INSTANCE =new Config();
	private Config() {
		try {
			properties=new XMLParse(new File("config/properties.xml"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static String get(String key){
		return properties.get(key);
	}

}
