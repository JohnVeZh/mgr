package cn.sparke.modules.community.news.service;

import cn.sparke.modules.community.news.entity.CommunityNewsEntity;
import cn.sparke.modules.community.news.mapper.CommunityNewsMapper;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.github.pagehelper.Page;

/**
 * 社区资讯Dao
 *
 * @author spark
 * @Date 2017-07-24 16:51:33
 */
@Service
public class CommunityNewsService{
    @Autowired
    private CommunityNewsMapper communityNewsMapper;

    public void save(CommunityNewsEntity communityNews){
        communityNews.preInsert();
        communityNewsMapper.insertSelective(communityNews);
    }

    public void update(CommunityNewsEntity communityNews){
        communityNews.preUpdate();
        communityNewsMapper.update(communityNews);
    }

    public CommunityNewsEntity get(CommunityNewsEntity communityNews){
        return communityNewsMapper.get(communityNews);
    }

    public CommunityNewsEntity getById(String id){
        return communityNewsMapper.getById(id);
    }
    public Page<CommunityNewsEntity> findList(CommunityNewsEntity communityNews){
       return communityNewsMapper.findList(communityNews);
    }

    public void deleteById(String id){
      communityNewsMapper.deleteById(id);
    }

    public Page findListWithoutContent(CommunityNewsEntity entity) {
        return communityNewsMapper.findListWithoutContent(entity);
    }
}
