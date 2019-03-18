package com.aladen.dao.base;

import java.util.List;
import java.util.Map;

public interface BaseDao<T> {

    T queryByParams(Map<String, Object> paramsMap);

    List<T> queryList(Map<String, Object> paramsMap);

    int saveEntity(T t);

    int updateEntity(T t);

    int deleteEntity(Long id);
}
