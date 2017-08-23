package cn.sparke.modules.goods.networkCourse.mapper;

import cn.sparke.core.modules.mybatis.mapper.BaseMapper;
import cn.sparke.modules.goods.networkCourse.entity.NetworkCourseEntity;
import com.github.pagehelper.Page;


/**
 * 网课Dao
 *
 * @author spark
 * @Date 2017-07-22 16:13:33
 */
public interface NetworkCourseMapper extends BaseMapper<NetworkCourseEntity>{
    Page<NetworkCourseEntity> queryAllList(NetworkCourseEntity networkCourseEntity);
    void updateStatus(NetworkCourseEntity networkCourseEntity);

}
