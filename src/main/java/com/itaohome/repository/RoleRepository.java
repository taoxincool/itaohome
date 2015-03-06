package com.itaohome.repository;

import com.itaohome.model.TbRole;
import com.itaohome.model.TbUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Mr tao on 2015/1/14.
 */
@Repository(value = "roleRepository")
public interface RoleRepository extends CrudRepository<TbRole,Integer> {
    public TbRole findById(Integer id);
}
