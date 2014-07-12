package net.cedu.model.common;

import java.text.SimpleDateFormat;
import java.util.Date;

import net.cedu.common.Constants;

/**
 * 日期时间范围
 * 
 * @author Sauntor
 *
 */
public class DateTimeRange extends Range<Date> {
	public static final String DEFAULT_BOUNDARY = "'";
	
	/**
	 * 使用自定界定符和站位符格式化为日期时间范围字符串
	 * @param DEFAULT_BOUNDARY 日期时间界定字符
	 * @param placeHolder 两时间之间的站位符
	 * @return 返回类似于 <code>'2010-01-23 13:23:00' < today < '2010-03-11 15:34:08'</code>
	 */
	public String toTimeString(String placeHolder)
	{
		String range = null;
		
		SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_TIME_FORMAT);

		if(toward==InOutWard.In){
			if(begin==null){
				range = placeHolder + " <= " + DEFAULT_BOUNDARY + sdf.format(end) + DEFAULT_BOUNDARY;
			} else if(end==null){
				range = placeHolder +" >= " + DEFAULT_BOUNDARY + sdf.format(begin) + DEFAULT_BOUNDARY;
			} else {
				range = placeHolder + " >= " + DEFAULT_BOUNDARY + sdf.format(begin) + DEFAULT_BOUNDARY + " and " + placeHolder + " <= " + DEFAULT_BOUNDARY + sdf.format(end) + DEFAULT_BOUNDARY;
			}
		} else if(toward==InOutWard.Out) {
			if(begin==null){
				range = placeHolder + " >= " + DEFAULT_BOUNDARY + sdf.format(end) + DEFAULT_BOUNDARY;
			} else if(end==null){
				range = placeHolder +" <= " + DEFAULT_BOUNDARY + sdf.format(begin) + DEFAULT_BOUNDARY;
			} else {
				range = "( " + placeHolder + " <= " + DEFAULT_BOUNDARY + sdf.format(begin) + DEFAULT_BOUNDARY + " or " + placeHolder + " >= " + DEFAULT_BOUNDARY + sdf.format(end) + DEFAULT_BOUNDARY + " )";
			}
		} else {
			throw new RuntimeException("The 'toward' property was not set yet!");
		}
		
		return range;
	}
	
	/**
	 * 使用自定界定符和站位符格式化为日期范围字符串
	 * @param DEFAULT_BOUNDARY 日期界定字符
	 * @param placeHolder 两时期间站位符
	 * @return 返回类似于 <code>'2010-01-23' < today < '2010-03-11'</code>
	 */
	public String toDateString(String placeHolder)
	{
		String range = null;
		SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_FORMAT);

		if(toward==InOutWard.In){
			if(begin==null){
				range = placeHolder + " <= " + DEFAULT_BOUNDARY + sdf.format(end) + DEFAULT_BOUNDARY;
			} else if(end==null){
				range = placeHolder +" >= " + DEFAULT_BOUNDARY + sdf.format(begin) + DEFAULT_BOUNDARY;
			} else {
				range = placeHolder + " >= " + DEFAULT_BOUNDARY + sdf.format(begin) + DEFAULT_BOUNDARY + " and " + placeHolder + " <= " + DEFAULT_BOUNDARY + sdf.format(end) + DEFAULT_BOUNDARY;
			}
		} else if(toward==InOutWard.Out) {
			if(begin==null){
				range = placeHolder + " >= " + DEFAULT_BOUNDARY + sdf.format(end) + DEFAULT_BOUNDARY;
			} else if(end==null){
				range = placeHolder +" <= " + DEFAULT_BOUNDARY + sdf.format(begin) + DEFAULT_BOUNDARY;
			} else {
				range = "( " + placeHolder + " <= " + DEFAULT_BOUNDARY + sdf.format(begin) + DEFAULT_BOUNDARY + " or " + placeHolder + " >= " + DEFAULT_BOUNDARY + sdf.format(end) + DEFAULT_BOUNDARY + " )";
			}
		} else {
			throw new RuntimeException("The 'toward' property was not set yet!");
		}

		return range;
	}
	
	/**
	 * 检查此日期时间范围实体是不是有效的
	 * @return 有效时返回true，否则返回false
	 */
	public boolean isValid(){
		
		if(begin==null && end==null)
			return false;

		if(begin!=null && end!=null && begin.after(end))
			return false;
		
		if(toward==null)
			return false;

		return true;
	}
}
