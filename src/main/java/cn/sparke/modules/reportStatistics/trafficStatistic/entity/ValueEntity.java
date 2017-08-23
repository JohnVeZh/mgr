package cn.sparke.modules.reportStatistics.trafficStatistic.entity;

/**
 * Created by Administrator on 2017/8/1.
 */
public class ValueEntity {
    private String province;
    private int fromType;
    private Long pv;
    private Long uv;

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public int getFromType() {
        return fromType;
    }

    public void setFromType(int fromType) {
        this.fromType = fromType;
    }

    public Long getPv() {
        return pv;
    }

    public void setPv(Long pv) {
        this.pv = pv;
    }

    public Long getUv() {
        return uv;
    }

    public void setUv(Long uv) {
        this.uv = uv;
    }
}
