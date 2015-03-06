package com.itaohome.repository;

import com.itaohome.model.TbAutoReplyNews;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Mr tao on 2015/1/16.
 */
@Repository(value = "autoReplyNewsRepository")
public interface AutoReplyNewsRepository extends CrudRepository<TbAutoReplyNews, Integer> {
    List<TbAutoReplyNews> findByParentIdAndIsDelete(Integer parentId,String isDelete);
}
