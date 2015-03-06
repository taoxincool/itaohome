package com.itaohome.repository;

import com.itaohome.model.TbAutoReply;

/**
 * Created by Mr tao on 2015/1/15.
 */
public interface AutoReplyRepositoryCustom {

    public TbAutoReply getSubscribeAutoReplyMsg();

    public TbAutoReply getTextAutoReplyMsg(String inputContent);

}
