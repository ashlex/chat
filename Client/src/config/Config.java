package config;

import java.io.File;

import core.XMLParse;


/**
 * @author Alexej
 * @version 1.0
 * Отвечает за загрузку настроек
 */
public class Config {
	private  XMLParse properties;
	private static final Config INSTANCE =new Config();
	private Config() {
		try {
			properties=new XMLParse(new File("config/properties.xml"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static Config getInstance(){
		return INSTANCE;
	}
	
	public String get(String key){
		return properties.get(key);
	}

}
