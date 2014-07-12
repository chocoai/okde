package net.cedu.common.sql;

import java.text.SimpleDateFormat;
import java.util.Date;

import net.cedu.common.Constants;
import net.cedu.model.common.Range;
import net.cedu.model.common.Range.InOutWard;

/**
 * 处理范围的工具类
 * @author Sauntor
 *
 */
public final class RangeUtil {
	private static final String DEFAULT_BOUNDARY = "'"; //默认字符串边界符
//	public static final String DEFAULT_PLACEHOLER = "%s"; //默认占位符
	
	/**
	 * 检查日期时间范围是不是有效的
	 * @param dateRange 日期（时间）范围
	 * @return 有效时返回true，否则返回false
	 */
	public static boolean validateDate(Range<Date> dateRange){
		if(dateRange==null)
			return false;
		
		if(dateRange.getBegin()==null && dateRange.getEnd()==null)
			return false;

		if(dateRange.getBegin()!=null && dateRange.getEnd()!=null
				&& dateRange.getBegin().after(dateRange.getEnd()))
			return false;
		
		if(dateRange.getToward()==null)
			return false;

		return true;
	}
	
	/**
	 * 格式化日期范围为日期范围字符串
	 * @param dateRange 日期范围
	 * @return 返回类似于 <code>'2010-01-23' < {@code placeHolder } < '2010-03-11'</code>
	 */
	public static String formatDate(Range<Date> dateRange, String placeHolder){
		String dateStr = _format_date(dateRange, Constants.DATE_FORMAT, placeHolder);
		
		return dateStr;
	}
	
	/**
	 * 格式化时间范围为时间范围字符串
	 * @param dateRange 时间范围
	 * @return 返回类似于 <code>''2010-01-23 13:23:00' < {@code placeHolder } < '2010-03-11 15:34:08'</code>
	 */
	public static String formatTime(Range<Date> dateRange, String placeHolder){
		String dateStr = _format_date(dateRange, Constants.DATE_TIME_FORMAT, placeHolder);
		
		return dateStr;
	}
	
	/**
	 * 时期时间格式化
	 * @param dateRange
	 * @param format
	 * @return
	 */
	private static String _format_date(Range<Date> dateRange, String format, String placeHolder){
		String range = null;

		SimpleDateFormat sdf = new SimpleDateFormat(format);

		if(dateRange.getToward()==InOutWard.In){
			if(dateRange.getBegin()==null){
				range = placeHolder + " <= " + DEFAULT_BOUNDARY + sdf.format(dateRange.getEnd()) + DEFAULT_BOUNDARY;
			} else if(dateRange.getEnd()==null){
				range = placeHolder +" >= " + DEFAULT_BOUNDARY + sdf.format(dateRange.getBegin()) + DEFAULT_BOUNDARY;
			} else {
				range = placeHolder + " >= " + DEFAULT_BOUNDARY + sdf.format(dateRange.getBegin()) + DEFAULT_BOUNDARY + " and " + placeHolder + " <= " + DEFAULT_BOUNDARY + sdf.format(dateRange.getEnd()) + DEFAULT_BOUNDARY;
			}
		} else if(dateRange.getToward()==InOutWard.Out) {
			if(dateRange.getBegin()==null){
				range = placeHolder + " >= " + DEFAULT_BOUNDARY + sdf.format(dateRange.getEnd()) + DEFAULT_BOUNDARY;
			} else if(dateRange.getEnd()==null){
				range = placeHolder +" <= " + DEFAULT_BOUNDARY + sdf.format(dateRange.getBegin()) + DEFAULT_BOUNDARY;
			} else {
				range = "( " + placeHolder + " <= " + DEFAULT_BOUNDARY + sdf.format(dateRange.getBegin()) + DEFAULT_BOUNDARY + " or " + placeHolder + " >= " + DEFAULT_BOUNDARY + sdf.format(dateRange.getEnd()) + DEFAULT_BOUNDARY + " )";
			}
		} else {
			throw new RuntimeException("The 'toward' property has not been set yet!");
		}
		
		return range;
	}
}
