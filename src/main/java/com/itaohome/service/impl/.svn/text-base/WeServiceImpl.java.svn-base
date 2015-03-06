package com.itaohome.service.impl;

import com.itaohome.model.*;
import com.itaohome.repository.*;
import com.itaohome.service.WeService;
import com.itaohome.util.Constants;
import com.itaohome.util.WeUtils;
import com.itaohome.util.XmlUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.w3c.dom.Document;

import javax.annotation.Resource;
import javax.servlet.ServletInputStream;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by Mr tao on 2015/1/15.
 */
@Service(value = "weService")
public class WeServiceImpl implements WeService {

    @Resource
    private FansRepository fansRepository;

    @Resource
    private LogRepository logRepository;

    @Resource
    private MessageRepository messageRepository;

    @Resource
    private AutoReplyRepository autoReplyRepository;

    @Resource
    private AutoReplyNewsRepository autoReplyNewsRepository;

    /**
     * 关注获取取消关注公众号
     *
     * @param doc
     * @return
     */
    @Override
    @Transactional
    public String saveFans(Document doc) {
        inserMessage(doc);//记录微信聊天消息
        String openId = XmlUtils.getValueByTagName(doc, "FromUserName");
        String status = XmlUtils.getValueByTagName(doc, "Event").equals("subscribe") ? "1" : "0";
        TbFans tbFans = new TbFans();
        tbFans.setOpenId(openId);
        tbFans.setStatus(status);
        tbFans.setMemo("");
        if (fansRepository.findByOpenId(openId) == null || fansRepository.findByOpenId(openId).size() < 1) {
            tbFans.setCreateDate(new Date());
            tbFans.setCreateUserId("");
        } else {
            tbFans.setId(fansRepository.findByOpenId(openId).get(0).getId());
            tbFans.setUpdateDate(new Date());
            tbFans.setUpdateUserId("");
        }
        tbFans.setIsDelete("0");
        fansRepository.save(tbFans);
        insertLog("info", "TbFans is save or update! id is :" + tbFans.getId()); //记录日志
        //推送自动回复消息
        return this.autoReplyMsg(doc, autoReplyRepository.getSubscribeAutoReplyMsg());
    }

    /**
     * 自动回复微信粉丝消息
     *
     * @param doc
     * @param tbAutoReply
     * @return
     */
    public String autoReplyMsg(Document doc, TbAutoReply tbAutoReply) {
        String replyMsgXml = "";
        List list = new ArrayList();//如果是图文消息则list集合有
        //表示有匹配的回复内容
        if (tbAutoReply != null) {
            //判断回复的消息类型
            if (tbAutoReply.getReplyContentType().equals("news")) {//回复text文本消息
                TbAutoReplyNews parentTbAutoReplyNews = autoReplyNewsRepository.findOne(tbAutoReply.getReplyContentId());
                list.add(parentTbAutoReplyNews);//添加父图文
                List<TbAutoReplyNews> childTbAutoReplyNews = autoReplyNewsRepository.findByParentIdAndIsDelete(parentTbAutoReplyNews.getId(),"0"); //获取子图文
                list.addAll(childTbAutoReplyNews);//添加子图文
            }
            replyMsgXml = generateReplyMesXml(XmlUtils.getValueByTagName(doc, "ToUserName"), XmlUtils.getValueByTagName(doc, "FromUserName"), tbAutoReply.getReplyContentType(), tbAutoReply.getReplyContent(), list);
        }
        if (replyMsgXml != "") inserMessage(XmlUtils.getXmlByString(replyMsgXml));//记录回复聊天消息
        return replyMsgXml;
    }


    /**
     * 生成回复的消息内容XML
     *
     * @param fromUserName
     * @param toUserName
     * @param type         生成回复消息类型,text/news/...
     * @param content
     * @param list         生成回复消息类型使用的集合
     * @return
     */
    public String generateReplyMesXml(String fromUserName, String toUserName, String type, String content, List<TbAutoReplyNews> list) {
        String result = "";
        if (type.equals("text")) {
            //构建文字回复XML
            result = "<xml>" +
                    "<ToUserName><![CDATA[" + toUserName + "]]></ToUserName>" +
                    "<FromUserName><![CDATA[" + fromUserName + "]]></FromUserName>" +
                    "<CreateTime>" + WeUtils.getIntegerDate() + "</CreateTime>" +
                    "<MsgType><![CDATA[" + type + "]]></MsgType>" +
                    "<Content><![CDATA[" + content + "]]></Content>" +
                    "</xml>";
        } else if (type.equals("news")) {
            //构建图文回复XML
            result = "<xml>" +
                    "<ToUserName><![CDATA[" + toUserName + "]]></ToUserName>" +
                    "<FromUserName><![CDATA[" + fromUserName + "]]></FromUserName>" +
                    "<CreateTime>" + WeUtils.getIntegerDate() + "</CreateTime>" +
                    "<MsgType><![CDATA[" + type + "]]></MsgType>" +
                    "<ArticleCount>" + list.size() + "</ArticleCount>" +
                    "<Articles>";
            for (TbAutoReplyNews tbAutoReplyNews : list) {
                result += "<item>" +
                        "<Title><![CDATA[" + tbAutoReplyNews.getTitle() + "]]></Title>" +
                        "<Description><![CDATA[" + tbAutoReplyNews.getDescription() + "]]></Description>" +
                        "<PicUrl><![CDATA[" + tbAutoReplyNews.getPicUrl() + "]]></PicUrl>" +
                        "<Url><![CDATA[" + tbAutoReplyNews.getUrl() + "]]></Url>" +
                        "</item>";
            }
            result += "</Articles>" +
                    "</xml>";

        }

        return result;
    }


    /**
     * 记录聊天消息
     *
     * @param doc
     */
    public void inserMessage(Document doc) {
        if (doc != null) {
            TbMessage tbMessage = new TbMessage();
            tbMessage.setFromUserName(XmlUtils.getValueByTagName(doc, "FromUserName"));
            tbMessage.setToUserName(XmlUtils.getValueByTagName(doc, "ToUserName"));
            tbMessage.setMsgType(XmlUtils.getValueByTagName(doc, "MsgType"));
            tbMessage.setEvent(XmlUtils.getValueByTagName(doc, "Event"));
            tbMessage.setEventKey(XmlUtils.getValueByTagName(doc, "EventKey"));
            tbMessage.setTicket(XmlUtils.getValueByTagName(doc, "Ticket"));
            tbMessage.setContent(XmlUtils.getValueByTagName(doc, "Content"));
            tbMessage.setMsgId(XmlUtils.getValueByTagName(doc, "1234567890123456"));
            tbMessage.setMemo(XmlUtils.getXmlString(doc));
            tbMessage.setCreateDate(new Date());
            tbMessage.setCreateDateWx(XmlUtils.getValueByTagName(doc, "CreateTime"));
            tbMessage.setIsDelete("0");
            tbMessage.setPicUrl(XmlUtils.getValueByTagName(doc, "PicUrl"));
            tbMessage.setMediaId(XmlUtils.getValueByTagName(doc, "MediaId"));
            tbMessage.setFormat(XmlUtils.getValueByTagName(doc, "Format"));
            tbMessage.setThumbMediaId(XmlUtils.getValueByTagName(doc, "ThumbMediaId"));
            tbMessage.setLocationX(XmlUtils.getValueByTagName(doc, "Location_X "));
            tbMessage.setLocationY(XmlUtils.getValueByTagName(doc, "Location_Y "));
            tbMessage.setScale(XmlUtils.getValueByTagName(doc, "Scale"));
            tbMessage.setLabel(XmlUtils.getValueByTagName(doc, "Label"));
            tbMessage.setTitle(XmlUtils.getValueByTagName(doc, "Title"));
            tbMessage.setDescription(XmlUtils.getValueByTagName(doc, "Description"));
            tbMessage.setUrl(XmlUtils.getValueByTagName(doc, "Url"));
            messageRepository.save(tbMessage);
            //记录日志
            insertLog("info", "TbMessage is save! id is :" + tbMessage.getId());
        }
    }

    /**
     * 记录日志
     *
     * @param type error/info
     * @param e    error时e为错误信息,info时e为日志信息
     */
    public void insertLog(String type, String e) {
        TbLog log = new TbLog();
        log.setType(type);
        log.setMemo(e);
        log.setCreateDate(new Date());
        log.setCreateUserId("");
        log.setIsDelete("0");
        logRepository.save(log);
    }

    /**
     * 验证微信Token连接是否成功
     *
     * @return
     */
    @Override
    public String checkToken(String signature, String timestamp, String nonce, String echostr) {
        try {
            //表示只是验证URL的合法性
            String[] array = {Constants.TOKEN, timestamp, nonce};
            Arrays.sort(array);
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; i++) {
                sb.append(array[i]);
            }
            String str = sb.toString();
            str = WeUtils.SHA1(str);//加密
            if (str == signature) {
                //成功
                return echostr;
            } else {
                //失败
                return echostr;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return echostr;
        }
    }

    /**
     * 处理所有类型消息
     *
     * @param inputStream
     * @return
     */
    @Override
    public String disposeAllMessage(ServletInputStream inputStream) {
        Document doc = null;
        try {
            //表示微信POS消息
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            doc = db.parse(inputStream);

            //判断消息的类型
            String msgType = XmlUtils.getValueByTagName(doc, "MsgType");
            if (msgType.equals("event")) {
                //表示关注、取消关注、扫描二维码操作
                return this.disposeEventMessage(doc);
            } else if (msgType.equals("text")) {
                //文本消息,自动回复
                return disposeTextMessage(doc);
            } else if (msgType.equals("image")) {
                return "image";
                //图片消息
            } else if (msgType.equals("voice")) {
                return "voice";
                //语音消息
            } else if (msgType.equals("location")) {
                return "location";
                //定位消息
            } else if (msgType.equals("link")) {
                return "link";
                //链接消息
            } else {
                //其他
                return "";
            }
        } catch (Exception e) {
            this.insertLog("error", "错误信息:" + WeUtils.getExceptionMessage(e));
            return "error";
        }
    }

    /**
     * 处理'事件'类型消息
     *
     * @param doc
     * @return
     */
    public String disposeEventMessage(Document doc) {
        String eventType = XmlUtils.getValueByTagName(doc, "Event");
        String returnMsg = "";//返回消息
        if (eventType.equals("subscribe")) {
            //关注
            returnMsg = this.saveFans(doc);
        } else if (eventType.toUpperCase().equals("unsubscribe")) {
            //取消关注
            returnMsg = this.saveFans(doc);
        } else if (eventType.toUpperCase().equals("SCAN")) {
            return "SCAN";
            //扫描二维码
        } else if (eventType.toUpperCase().equals("LOCATION")) {
            return "LOCATION";
            //定位
        } else if (eventType.toUpperCase().equals("CLICK")) {
            return "CLICK";
            //点击菜单
        }
        return returnMsg;
    }

    /**
     * 处理用户发送的'文字'类型消息
     *
     * @param doc
     * @return
     */
    public String disposeTextMessage(Document doc) {
        inserMessage(doc);//记录微信聊天消息
        //推送自动回复消息
        return this.autoReplyMsg(doc, autoReplyRepository.getTextAutoReplyMsg(XmlUtils.getValueByTagName(doc, "Content")));
    }
}
