package cn.sparke.core.common.constants.tips;


/**
 * 返回给前台的操作失败
 *
 * @author spark
 * @date 2016年11月12日 下午5:05:22
 */
public class FailureTip extends Tip {

    public FailureTip() {
        super();
        this.code = 501;
        this.message = "操作失败";
    }

    public FailureTip(String message) {
        super();
        this.code = 501;
        this.message = message;
    }
}
