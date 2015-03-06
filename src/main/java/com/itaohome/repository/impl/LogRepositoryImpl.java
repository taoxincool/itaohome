package com.itaohome.repository.impl;

import com.itaohome.model.TbLog;
import com.itaohome.repository.LogRepositoryCustom;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by Mr tao on 2015/1/14.
 */
public class LogRepositoryImpl implements LogRepositoryCustom {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<TbLog> queryList() {
        TypedQuery query = em.createQuery("select t from TbLog t", TbLog.class);
        List<TbLog> lst = query.getResultList();
        return lst;
    }
}
