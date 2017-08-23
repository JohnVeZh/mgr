package cn.sparke.core.modules.mybatis.mapper;

import com.github.pagehelper.Page;

/**
 * Created by zhangbowen on 2017/7/17.
 */
public interface BaseMapper<T> {
    T getById(String id);

    int insert(T t);

    Integer deleteById(String id);

    Integer update(T entity);


    /**
     * 查询列表
     *
     * @param entity
     * @return
     */
    <R, P> Page<R> findList(P entity);

    <R, P> Page<R> queryList(P entity);

    T get(T entity);
}
