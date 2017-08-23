package cn.sparke.modules.user.entity;

import cn.sparke.core.common.entity.BaseEntity;
import java.util.*;
import java.math.*;

/**
 * 收货地址Entity
 *
 * @author spark
 * @Date 2017-07-22 10:49:11
 */
public class UserAddressEntity extends BaseEntity{
    //用户ID
    private String userId;
    //省id
    private Integer provinceId;
    //市id
    private Integer cityId;
    //区县id
    private Integer countyId;
    //省市区全名
    private String districtCn;
    //详细地址
    private String detailAddress;
    //收货人
    private String receiver;
    //邮政编码
    private String zipcode;
    //手机号码
    private String mobile;
    //是否默认1:是;0::否
    private Integer isDefault;

    public void setUserId(String userId){
        this.userId = userId;
    }
    public String getUserId(){
        return this.userId;
    }
    public void setProvinceId(Integer provinceId){
        this.provinceId = provinceId;
    }
    public Integer getProvinceId(){
        return this.provinceId;
    }
    public void setCityId(Integer cityId){
        this.cityId = cityId;
    }
    public Integer getCityId(){
        return this.cityId;
    }
    public void setCountyId(Integer countyId){
        this.countyId = countyId;
    }
    public Integer getCountyId(){
        return this.countyId;
    }
    public void setDistrictCn(String districtCn){
        this.districtCn = districtCn;
    }
    public String getDistrictCn(){
        return this.districtCn;
    }
    public void setDetailAddress(String detailAddress){
        this.detailAddress = detailAddress;
    }
    public String getDetailAddress(){
        return this.detailAddress;
    }
    public void setReceiver(String receiver){
        this.receiver = receiver;
    }
    public String getReceiver(){
        return this.receiver;
    }
    public void setZipcode(String zipcode){
        this.zipcode = zipcode;
    }
    public String getZipcode(){
        return this.zipcode;
    }
    public void setMobile(String mobile){
        this.mobile = mobile;
    }
    public String getMobile(){
        return this.mobile;
    }
    public void setIsDefault(Integer isDefault){
        this.isDefault = isDefault;
    }
    public Integer getIsDefault(){
        return this.isDefault;
    }

}
