package cn.sparke.modules.goods.product.service;

import cn.sparke.modules.goods.product.entity.ProductEntity;
import cn.sparke.modules.goods.product.mapper.ProductMapper;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.github.pagehelper.Page;


/**
 * 商品Dao
 *
 * @author spark
 * @Date 2017-07-19 15:58:00
 */
@Service
public class ProductService{
    @Autowired
    private ProductMapper productMapper;

    public void save(ProductEntity product){
        product.preInsert();
        productMapper.insert(product);
    }

    public void update(ProductEntity product){
        product.preUpdate();
        productMapper.update(product);
    }

    public ProductEntity get(ProductEntity product){
        return productMapper.get(product);
    }

    public ProductEntity getById(String id){
        return productMapper.getById(id);
    }
    public Page<ProductEntity> findList(ProductEntity product){
       return productMapper.findList(product);
    }

    public void deleteById(String id){
      productMapper.deleteById(id);
    }

}
