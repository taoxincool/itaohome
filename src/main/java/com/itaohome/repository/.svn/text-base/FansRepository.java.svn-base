package com.itaohome.repository;

import com.itaohome.model.TbFans;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Mr tao on 2015/1/14.
 */
@Repository(value = "fansRepository")
public interface FansRepository extends CrudRepository<TbFans,Integer>{
    public List<TbFans> findByOpenId(String openId);
}
