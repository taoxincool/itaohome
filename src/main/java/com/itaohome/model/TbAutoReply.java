package com.itaohome.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Mr tao on 2015/1/14.
 */
@Entity
@Table(name = "tb_auto_reply")
public class TbAutoReply {

    @Id
    @GeneratedValue
    private Integer id;

    @Column
    private String type;

    @Column(name = "input_content")
    private String inputContent;

    @Column(name = "reply_content")
    private String replyContent;

    @Column(name = "reply_content_type")
    private String replyContentType;

    @Column(name = "reply_content_id")
    private Integer replyContentId;

    @Column
    private String memo;

    @Column(name = "create_date")
    private Date createDate;

    @Column(name = "create_user_id")
    private String createUserId;

    @Column(name = "update_date")
    private Date updateDate;

    @Column(name = "update_user_id")
    private String updateUserId;

    @Column(name = "is_delete")
    private String isDelete;

    @Column
    private String enable;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getInputContent() {
        return inputContent;
    }

    public void setInputContent(String inputContent) {
        this.inputContent = inputContent;
    }

    public String getReplyContent() {
        return replyContent;
    }

    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(String updateUserId) {
        this.updateUserId = updateUserId;
    }

    public String getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete;
    }

    public String getReplyContentType() {
        return replyContentType;
    }

    public void setReplyContentType(String replyContentType) {
        this.replyContentType = replyContentType;
    }

    public String getEnable() {
        return enable;
    }

    public void setEnable(String enable) {
        this.enable = enable;
    }

    public Integer getReplyContentId() {
        return replyContentId;
    }

    public void setReplyContentId(Integer replyContentId) {
        this.replyContentId = replyContentId;
    }
}
