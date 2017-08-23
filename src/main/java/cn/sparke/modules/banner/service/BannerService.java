package cn.sparke.modules.banner.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.github.pagehelper.Page;
import cn.sparke.modules.banner.mapper.BannerMapper;
import cn.sparke.modules.banner.entity.BannerEntity;

/**
 * bannerDao
 *
 * @author spark
 * @Date 2017-07-27 11:04:48
 */
@Service
public class BannerService{
    @Autowired
    private BannerMapper bannerMapper;

    public void save(BannerEntity banner){
        banner.preInsert();
        bannerMapper.insert(banner);
    }

    public void update(BannerEntity banner){
        banner.preUpdate();
        bannerMapper.update(banner);
    }

    public BannerEntity get(BannerEntity banner){
        return bannerMapper.get(banner);
    }

    public BannerEntity getById(String id){
        return bannerMapper.getById(id);
    }
    public Page<BannerEntity> findList(BannerEntity banner){
       return bannerMapper.findList(banner);
    }

    public void deleteById(String id){
      bannerMapper.deleteById(id);
    }

}
