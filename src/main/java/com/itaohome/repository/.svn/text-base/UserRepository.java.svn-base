package com.itaohome.repository;

import com.itaohome.model.TbRole;
import com.itaohome.model.TbUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Mr tao on 2015/1/14.
 */
@Repository(value = "userRepository")
public interface UserRepository  extends CrudRepository<TbUser,Integer> {
    public TbUser findByUsername(String username);
}
