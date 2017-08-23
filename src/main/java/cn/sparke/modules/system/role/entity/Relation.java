package cn.sparke.modules.system.role.entity;


import cn.sparke.core.common.entity.BaseEntity;

/**
 * <p>
 * 角色和菜单关联表
 * </p>
 *
 * @author sparke
 * @since 2017-07-11
 */
public class Relation extends BaseEntity{


    /**
     * 菜单id
     */
	private String menuid;
    /**
     * 角色id
     */
	private String roleid;


	public String getMenuid() {
		return menuid;
	}

	public void setMenuid(String menuid) {
		this.menuid = menuid;
	}

	public String getRoleid() {
		return roleid;
	}

	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}
}
