package cn.sparke.core.common.constants.factory;

import cn.sparke.core.common.constants.cache.Cache;
import cn.sparke.core.common.constants.cache.CacheKey;
import org.springframework.cache.annotation.Cacheable;

/**
 * 常量生产工厂的接口
 *
 * @author spark
 * @date 2017-06-14 21:12
 */
public interface IConstantFactory {


    /**
     * 通过角色ids获取角色名称
     */
    @Cacheable(value = Cache.CONSTANT, key = "'" + CacheKey.ROLES_NAME + "'+#roleIds")
    String getRoleName(String roleIds);

    /**
     * 通过角色id获取角色名称
     */
    @Cacheable(value = Cache.CONSTANT, key = "'" + CacheKey.SINGLE_ROLE_NAME + "'+#roleId")
    String getSingleRoleName(String roleId);

    /**
     * 通过角色id获取角色英文名称
     */
    @Cacheable(value = Cache.CONSTANT, key = "'" + CacheKey.SINGLE_ROLE_TIP + "'+#roleId")
    String getSingleRoleTip(String roleId);

    /**
     * 获取部门名称
     */
    @Cacheable(value = Cache.CONSTANT, key = "'" + CacheKey.DEPT_NAME + "'+#deptId")
    String getDeptName(String deptId);



    /**
     * 获取菜单名称
     */
    String getMenuName(String menuId);


    /**
     * 获取被缓存的对象(用户删除业务)
     */
    String getCacheObject(String para);
}
