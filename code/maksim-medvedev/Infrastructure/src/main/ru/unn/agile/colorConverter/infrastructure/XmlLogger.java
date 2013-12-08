package ru.unn.agile.colorConverter.infrastructure;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import ru.unn.agile.colorConverter.viewmodel.ILogger;

import javax.xml.parsers.*;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class XmlLogger implements ILogger {
    private static final String DATE_FORMAT_NOW = "yyyy-MM-dd HH:mm:ss";

    private String filename;

    private Document xmlDoc;
    Transformer xmlTransformer;
    private Element messagesRoot;

    public XmlLogger(String filename) throws Exception {
        this.filename = filename;

        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder;
        try {
            docBuilder = docFactory.newDocumentBuilder();
            xmlDoc = docBuilder.newDocument();

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            xmlTransformer = transformerFactory.newTransformer();
            xmlTransformer.setOutputProperty(OutputKeys.INDENT, "yes");
            xmlTransformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

        } catch (ParserConfigurationException e) {
            throw new Exception("Cannot initialize log file");
        }

        Element rootElement = xmlDoc.createElement("log");
        xmlDoc.appendChild(rootElement);

        messagesRoot = xmlDoc.createElement("messages");
        rootElement.appendChild(messagesRoot);

        flushLog();
    }

    private static String getTimeStamp() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT_NOW);
        return dateFormat.format(cal.getTime());
    }

    private void flushLog() {
        DOMSource source = new DOMSource(xmlDoc);
        StreamResult result = new StreamResult(new File(filename));

        try {
            xmlTransformer.transform(source, result);
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void log(String message) {
        if (messagesRoot == null)
            return;

        Element messageElement = xmlDoc.createElement("message");
        messageElement.setAttribute("timestamp", getTimeStamp());
        messageElement.setTextContent(message);

        messagesRoot.appendChild(messageElement);
        flushLog();
    }

    @Override
    public List<String> getContent() {
        File fXmlFile = new File(filename);

        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = null;
        Document doc = null;
        try {
            dBuilder = dbFactory.newDocumentBuilder();
            doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();
        } catch (Exception e) {
            e.printStackTrace();
        }

        List<String> content = new ArrayList<String>();
        NodeList nList = doc.getElementsByTagName("message");

        for (int i = 0; i < nList.getLength(); i++) {
            content.add(nList.item(i).getTextContent());
        }

        return content;
    }
}
