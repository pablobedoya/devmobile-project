package com.java.main;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import android.util.Log;

public class StateParser {
	private static final String tag = "StateParser";
	private static final String FILE_EXTENSION = ".png";

	private DocumentBuilderFactory factory;
	private DocumentBuilder builder;
	private final HashMap<String, String> map;
	private final List<State> list;

	public StateParser() {
		this.list = new ArrayList<State>();
		this.map = new HashMap<String, String>();
	}

	private String getNodeValue(NamedNodeMap map, String key) {
		String nodeValue = null;
		Node node = map.getNamedItem(key);
		if (node != null) {
			nodeValue = node.getNodeValue();
		}
		return nodeValue;
	}

	public List<State> getList() {
		return this.list;
	}

	public String getAbbreviation(String state) {
		return (String) this.map.get(state);
	}

	/**
	 * Parse XML file containing body part X/Y/Description
	 * 
	 * @param inStream
	 */
	public void parse(InputStream inStream) {
		try {
			// TODO: after we must do a cache of this XML!!!!
			this.factory = DocumentBuilderFactory.newInstance();
			this.builder = this.factory.newDocumentBuilder();
			this.builder.isValidating();
			Document doc = this.builder.parse(inStream, null);

			doc.getDocumentElement().normalize();

			NodeList stateList = doc.getElementsByTagName("state");
			final int length = stateList.getLength();

			for (int i = 0; i < length; i++) {
				final NamedNodeMap attr = stateList.item(i).getAttributes();
				final String stateName = getNodeValue(attr, "name");
				final String stateAbbr = getNodeValue(attr, "abbreviation");
				final String stateRegion = getNodeValue(attr, "region");

				// Construct State object
				State state = new State(stateName, stateAbbr, stateRegion,
						stateAbbr.toLowerCase() + FILE_EXTENSION);

				// Add to list
				this.list.add(state);

				// Creat Map statename-abbrev
				this.map.put(stateName, stateAbbr);
				Log.d(tag, state.toString());
			}
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
	}
}
