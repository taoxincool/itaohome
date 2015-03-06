package com.itaohome.web;

import com.itaohome.service.WeService;
import com.itaohome.util.Constants;
import com.itaohome.util.WeUtils;
import com.itaohome.util.XmlUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Mr tao on 2015/1/13.
 */
@Controller
@RequestMapping(value = "/w")
public class WeController {

    @Resource
    private WeService weService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView list() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/w/query");
        return mv;
    }

    @RequestMapping(value = "/checkToken", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String checkToken(String signature, String timestamp, String nonce, String echostr, HttpServletRequest request) {

        //判断是否
        if (request.getMethod().equalsIgnoreCase("get")) {
            //表示只是验证URL的合法性
            return weService.checkToken(signature,timestamp,nonce,echostr);
        } else {
            try {
                //处理消息
                return weService.disposeAllMessage(request.getInputStream());
            } catch (IOException e) {
                weService.insertLog("error", "错误信息:" + WeUtils.getExceptionMessage(e));
                return "";
            }
        }
    }


}
