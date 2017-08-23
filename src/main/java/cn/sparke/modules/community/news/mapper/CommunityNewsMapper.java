package cn.sparke.modules.community.news.mapper;

import cn.sparke.core.modules.mybatis.mapper.BaseMapper;
import cn.sparke.modules.community.news.entity.CommunityNewsEntity;
import com.github.pagehelper.Page;

/**
 * 社区资讯Dao
 *
 * @author spark
 * @Date 2017-07-24 16:51:33
 */
public interface CommunityNewsMapper extends BaseMapper<CommunityNewsEntity>{
    int insertSelective(CommunityNewsEntity record);

    Page findListWithoutContent(CommunityNewsEntity entity);
}
