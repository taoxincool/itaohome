package com.itaohome.util;

import org.apache.commons.lang.StringUtils;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringReader;

/**
 * Created by Mr tao on 2015/1/14.
 */
public class XmlUtils {

    /**
     * 获取xml中某一节点内容
     * @param doc
     * @param tagName
     * @return
     */
    public static String getValueByTagName(Document doc, String tagName){
        try {
            if(doc == null || StringUtils.isBlank(tagName)){
                return "";
            }
            NodeList pl = doc.getElementsByTagName(tagName);
            if(pl != null && pl.getLength() > 0){
                return pl.item(0).getTextContent();
            }
            return "";
        } catch (DOMException e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * XML转字符串原样取出
     * @param doc
     * @return
     */
    public static String getXmlString(Document doc){
        TransformerFactory tf = TransformerFactory.newInstance();
        try {
            Transformer t = tf.newTransformer();
            t.setOutputProperty(OutputKeys.ENCODING,"UTF-8");//解决中文问题，试过用GBK不行
            t.setOutputProperty(OutputKeys.METHOD, "html");
            t.setOutputProperty(OutputKeys.VERSION, "4.0");
            t.setOutputProperty(OutputKeys.INDENT, "no");
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            t.transform(new DOMSource(doc), new StreamResult(bos));
            return bos.toString();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * XML字符串转XML对象
     * @param xmlStr
     * @return
     */
    public static Document getXmlByString(String xmlStr){
        try {
            StringReader sr = new StringReader(xmlStr);
            InputSource is = new InputSource(sr);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder=factory.newDocumentBuilder();
            Document doc = builder.parse(is);
            return doc;
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
