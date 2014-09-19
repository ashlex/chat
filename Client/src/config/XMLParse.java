package config;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Node;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

public class XMLParse {
	private DocumentBuilderFactory factory;
	private DocumentBuilder builder;
	private Document doc;
	private String parent = "";
	private String value = "";
	private Map<String, String> param=new HashMap<String, String>();

	public XMLParse(File file) throws Exception {
		factory = DocumentBuilderFactory.newInstance();
		builder = factory.newDocumentBuilder();
		doc = builder.parse(file);
		if (doc == null) {
			throw new NullPointerException();
		}
		getNodeList(doc, 0);
	}

	private void getNodeList(Node node, int item) {
		NodeList nl = node.getChildNodes();
		for (int i = 0; i < nl.getLength(); i++) {
			if (nl.item(i).getNodeType() == Node.TEXT_NODE
					&& node.getFirstChild().getNodeValue() != null && node.getFirstChild().getNodeValue().matches("[a-zA-Z\\/.0-9_:]+")) { // if1
				parent = nl.item(i).getParentNode().getNodeName().toUpperCase();
				value = nl.item(i).getNodeValue();
				param.put(parent, value);
			}
			getNodeList(nl.item(i), item++);

		}
	}
	public String get(String key){
		return param.get(key.toUpperCase());
	}

}
