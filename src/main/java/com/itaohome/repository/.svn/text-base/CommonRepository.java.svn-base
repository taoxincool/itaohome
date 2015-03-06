package com.itaohome.repository;

import com.itaohome.dto.QueryPageModel;

import java.util.List;

/**
 * Created by Mr tao on 2015/1/16.
 */
public interface CommonRepository {
    public QueryPageModel queryAll(Integer currPageNum, Class<?> cla);

    public List<?> queryAll(Class<?> cla);

    public String delObj(String ids, Class<?> cla);

    public Object findById(String id, Class<?> cla);

    public QueryPageModel queryAll(Integer currPageNum, String sql, String hql, Class<?> cla);

    public List queryAll(String sql, String hql, Class<?> cla);
}
