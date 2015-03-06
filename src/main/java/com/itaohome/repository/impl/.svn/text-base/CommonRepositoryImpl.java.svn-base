package com.itaohome.repository.impl;

import com.itaohome.dto.QueryPageModel;
import com.itaohome.repository.CommonRepository;
import com.itaohome.util.Constants;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mr tao on 2015/1/16.
 */
@Repository(value = "commonRepository")
public class CommonRepositoryImpl implements CommonRepository {

    private static final Logger logger = LoggerFactory.getLogger(CommonRepositoryImpl.class);

    @PersistenceContext
    private EntityManager em;

    /**
     * 查询所有数据-带分页
     *
     * @param currPageNum
     * @param cla
     * @return
     */
    @Override
    public QueryPageModel queryAll(Integer currPageNum, Class<?> cla) {
        QueryPageModel queryPageModel = new QueryPageModel();
        try {
            String hql = "select t from " + cla.getSimpleName() + " t order by t.id desc";
            TypedQuery query = em.createQuery(hql, cla);
            queryPageModel.setTotalCount(query.getResultList().size()); //设置总条数
            query = em.createQuery(hql, cla); //分页
            query.setFirstResult(Constants.PAGE_SIZE * (currPageNum - 1)); //表示当前分页页面，显示的数据从哪一条开始。如从第5条开始
            query.setMaxResults(Constants.PAGE_SIZE);//表示当前分页页面，显示的数据显示到哪一条。如显示到第10条
            queryPageModel.setList(query.getResultList()); //设置分页后集合
            queryPageModel.setCurrPageNum(currPageNum);
            queryPageModel.setPageSize(Constants.PAGE_SIZE);
            return queryPageModel;
        } catch (Exception e) {
            logger.error("查询分页数据出错:", e);
            return queryPageModel;
        }
    }

    /**
     * 查询所有数据-自定义SQL|HQL-带分页
     *
     * @param currPageNum
     * @return
     */
    @Override
    public QueryPageModel queryAll(Integer currPageNum, String sql, String hql, Class<?> cla) {
        QueryPageModel queryPageModel = new QueryPageModel();
        try {
            Query query = null;
            if (StringUtils.isNotBlank(sql)) query = em.createNativeQuery(sql, cla);
            else query = em.createQuery(hql, cla);
            queryPageModel.setTotalCount(query.getResultList().size()); //设置总条数
            if (StringUtils.isNotBlank(sql)) query = em.createNativeQuery(sql, cla);
            else query = em.createQuery(hql, cla);//分页
            query.setFirstResult(Constants.PAGE_SIZE * (currPageNum - 1)); //表示当前分页页面，显示的数据从哪一条开始。如从第5条开始
            query.setMaxResults(Constants.PAGE_SIZE);//表示当前分页页面，显示的数据显示到哪一条。如显示到第10条
            queryPageModel.setList(query.getResultList()); //设置分页后集合
            queryPageModel.setCurrPageNum(currPageNum);
            queryPageModel.setPageSize(Constants.PAGE_SIZE);
            return queryPageModel;
        } catch (Exception e) {
            logger.error("查询分页数据出错:", e);
            return queryPageModel;
        }
    }

    /**
     * 查询所有数据-自定义SQL|HQL-不带分页
     * @param sql
     * @param hql
     * @param cla
     * @return
     */
    @Override
    public List queryAll(String sql, String hql, Class<?> cla) {
        QueryPageModel queryPageModel = new QueryPageModel();
        try {
            Query query = null;
            if (StringUtils.isNotBlank(sql)) query = em.createNativeQuery(sql, cla);
            else query = em.createQuery(hql, cla);
            return query.getResultList();
        } catch (Exception e) {
            logger.error("查询数据出错:", e);
            return new ArrayList();
        }
    }

    /**
     * 查询所有数据-不带分页
     *
     * @param cla
     * @return
     */
    @Override
    public List<?> queryAll(Class<?> cla) {
        String hql = "select t from " + cla.getSimpleName() + " t order by t.id desc";
        TypedQuery query = em.createQuery(hql, cla);
        List lst = query.getResultList();
        return lst;
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @Override
    public String delObj(String ids, Class<?> cla) {
        try {
            if (StringUtils.isNotBlank(ids)) {
                String[] idArray = ids.split(",");
                for (String id : idArray) {
                    em.remove(em.getReference(cla, Integer.valueOf(id)));
                    logger.info("删除" + cla.getSimpleName() + "成功,id:" + id);
                }
                return "1";
            } else {
                return "0";
            }
        } catch (Exception e) {
            logger.error("删除出错:", e);
            return "0";
        }
    }

    @Override
    public Object findById(String id, Class<?> cla) {
        try {
            return em.find(cla, Integer.valueOf(id));
        } catch (Exception e) {
            logger.error("获取单个对象出错:", e);
            return null;
        }
    }
}
