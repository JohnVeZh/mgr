package cn.sparke.core.modules.mybatis.bean;


import com.github.pagehelper.Page;

import java.util.List;

/**
 * 分页结果的封装(for Bootstrap Table)
 *
 * @author spark
 * @Date 2017年1月22日 下午11:06:41
 */
public class PageInfo<T> {

    // 结果集
    private List<T> rows;

    // 总数
    private long total;

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public PageInfo() {
    }

    public PageInfo(List<T> rows, long total) {
        this.rows = rows;
        this.total = total;
    }

    public PageInfo(Page<T> page) {
        this.rows = page.getResult();
        this.total = page.getTotal();
    }

}
