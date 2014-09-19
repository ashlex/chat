import static org.junit.Assert.*;

import java.io.File;


import org.junit.Test;

import config.XMLParse;


public class TestXMLParse {
	File f=new File("config/prop.xml");
	
	@Test
	public void get() {
		try{
		XMLParse p=new XMLParse(f);
		System.out.print(p.get("PATCh"));
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

}
