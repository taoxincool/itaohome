package com.itaohome.repository;

import com.itaohome.model.TbAutoReply;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Mr tao on 2015/1/14.
 */
@Repository(value = "autoReplyRepository")
public interface AutoReplyRepository extends CrudRepository<TbAutoReply,Integer>,AutoReplyRepositoryCustom {
    public List<TbAutoReply> findByEnable(String enable);
}
