package cn.sparke.modules.goods.networkCourse.service;

import cn.sparke.core.common.utils.ToolUtil;
import cn.sparke.modules.goods.book.constants.ProductConstants;
import cn.sparke.modules.goods.networkCourse.constants.NetworkCourseStatusConstants;
import cn.sparke.modules.goods.networkCourse.entity.NetworkCourseEntity;
import cn.sparke.modules.goods.networkCourse.mapper.NetworkCourseMapper;
import cn.sparke.modules.goods.product.mapper.ProductMapper;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.github.pagehelper.Page;
import org.springframework.transaction.annotation.Transactional;


/**
 * 网课Dao
 *
 * @author spark
 * @Date 2017-07-22 16:13:33
 */
@Service
public class NetworkCourseService{
    @Autowired
    private NetworkCourseMapper networkCourseMapper;
    @Autowired
    private ProductMapper productMapper;

    @Transactional
    public void save(NetworkCourseEntity networkCourse){
        networkCourse.preInsert();
        networkCourse.setType(ProductConstants.PRODUCT_NETWORK_TYPE);
        productMapper.insert(networkCourse);

        networkCourse.setProductId(networkCourse.getId());
        networkCourse.setNetworkCourseId(ToolUtil.uuid());
        networkCourse.setStatus(NetworkCourseStatusConstants.NOT_PUBLISH);
        networkCourseMapper.insert(networkCourse);
    }

    public void update(NetworkCourseEntity networkCourse){
        networkCourse.preUpdate();
        productMapper.update(networkCourse);
        networkCourseMapper.update(networkCourse);
    }

    public NetworkCourseEntity get(NetworkCourseEntity networkCourse){
        return networkCourseMapper.get(networkCourse);
    }

    public NetworkCourseEntity getById(String id){
        return networkCourseMapper.getById(id);
    }
    public Page<NetworkCourseEntity> queryAllList(NetworkCourseEntity networkCourse){
       return networkCourseMapper.queryAllList(networkCourse);
    }
    public void updateStatus(NetworkCourseEntity networkCourse){
        networkCourseMapper.updateStatus(networkCourse);
    }

    public void deleteById(String id){
      productMapper.deleteById(id);
    }

}
