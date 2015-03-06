package com.itaohome.repository;

import com.itaohome.model.TbLog;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Mr tao on 2015/1/14.
 */
@Repository(value = "logRepository")
public interface LogRepository extends CrudRepository<TbLog,Integer>,LogRepositoryCustom{

}
