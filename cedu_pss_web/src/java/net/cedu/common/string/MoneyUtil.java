package net.cedu.common.string;

import java.text.DecimalFormat;
/**
 * 人民币转换工具类
 * @author yangdongdong
 *
 */
public class MoneyUtil {

	//保留小数点位数
	private static final int DECIMAL_POINT_COUNT=3;
	private final static DecimalFormat format_=new DecimalFormat();
	/**
	 * 转换钱的格式
	 * @param money
	 * @return
	 */
	public static String formatMoney(Double money){
		format_.setMinimumFractionDigits(DECIMAL_POINT_COUNT);
		return format_.format(money);
	}
	public static String formatMoney(Long money){
		format_.setMinimumFractionDigits(DECIMAL_POINT_COUNT);
		return format_.format(money);
	}
	public static String formatMoney(String money){
		try{
			Double money_=Double.valueOf(money);
			return formatMoney(money_);
		}catch(Exception e){
			return -999+".00";
		}
	}
	
}
