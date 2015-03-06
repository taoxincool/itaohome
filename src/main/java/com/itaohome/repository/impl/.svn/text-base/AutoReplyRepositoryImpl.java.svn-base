package com.itaohome.repository.impl;

import com.itaohome.model.TbAutoReply;
import com.itaohome.repository.AutoReplyRepositoryCustom;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by Mr tao on 2015/1/15.
 */
public class AutoReplyRepositoryImpl implements AutoReplyRepositoryCustom {


    @PersistenceContext
    private EntityManager em;


    /**
     * 获取关注微信号时，自动回复的消息内容
     *
     * @return
     */
    @Override
    public TbAutoReply getSubscribeAutoReplyMsg() {
        String hql = "FROM TbAutoReply t WHERE t.type = 2 AND t.enable = '1' AND t.isDelete = '0'";
        TypedQuery<TbAutoReply> query = em.createQuery(hql, TbAutoReply.class);
        TbAutoReply tbAutoReply = query.getSingleResult();
        return tbAutoReply;
    }

    /**
     * 获取发送文字消息时，自动回复的消息内容
     *
     * @return
     */
    @Override
    public TbAutoReply getTextAutoReplyMsg(String inputContent) {
        String hql = "FROM TbAutoReply t WHERE t.type = '1' AND t.inputContent LIKE :inputContent AND t.isDelete = '0'";
        TypedQuery<TbAutoReply> query = em.createQuery(hql, TbAutoReply.class);
        query.setParameter("inputContent","%"+inputContent+"%");
        List<TbAutoReply> autoReplies = query.getResultList();
        if(autoReplies != null && autoReplies.size() > 0) {
           return autoReplies.get(0);
        } else {
            return null;
        }
    }
}
