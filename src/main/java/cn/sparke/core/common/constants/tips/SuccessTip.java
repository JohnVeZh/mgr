package cn.sparke.core.common.constants.tips;

/**
 * 返回给前台的成功提示
 *
 * @author spark
 * @date 2016年11月12日 下午5:05:22
 */
public class SuccessTip extends Tip{
	
	public SuccessTip(){
		super.code = 200;
		super.message = "操作成功";
	}

	public SuccessTip( String message){
		super.code = 200;
		super.message = message;
	}

}
