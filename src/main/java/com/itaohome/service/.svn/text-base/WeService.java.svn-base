package com.itaohome.service;

import org.w3c.dom.Document;

import javax.servlet.ServletInputStream;
import java.io.InputStream;

/**
 * Created by Mr tao on 2015/1/13.
 */
public interface WeService {

    public String saveFans(Document doc);

    public void insertLog(String type, String e);

    public String checkToken(String signature, String timestamp, String nonce, String echostr);

    public String disposeAllMessage(ServletInputStream inputStream);

    public String disposeTextMessage(Document doc);

}
