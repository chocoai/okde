package net.cedu.common.string;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationUtil {
	

	/**
	 * 
	 * @功能：正则验证方法
	 * 
	 * @作者： 杨栋栋
	 * @作成时间：2011-11-28 下午05:17:00
	 * 
	 * @修改者：
	 * @修改内容：
	 * @修改时间：
	 * 
	 * @param regexstr
	 * @param str
	 * @return
	 */
	public static boolean Match(String regexstr, String str) {
		Pattern regex = Pattern.compile(regexstr, Pattern.CASE_INSENSITIVE
				| Pattern.DOTALL);
		Matcher matcher = regex.matcher(str);
		return matcher.matches();
	}

	/**
	 * 
	 * @功能：邮箱验证
	 * 
	 * @作者： 杨栋栋
	 * @作成时间：2011-11-28 下午05:17:12
	 * 
	 * @修改者：
	 * @修改内容：
	 * @修改时间：
	 * 
	 * @param mail
	 * @return
	 */
	public static boolean MatchMail(String mail) {
		String mailregex = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
		return Match(mailregex, mail);
	}

	/**
	 * 
	 * @功能：手机验证
	 * 
	 * @作者： 杨栋栋
	 * @作成时间：2011-11-28 下午05:17:21
	 * 
	 * @修改者：
	 * @修改内容：
	 * @修改时间：
	 * 
	 * @param mobile
	 * @return
	 */
	public static boolean MatchMobile(String mobile) {
		String mobileregex = "^(13[0,1,2,3,4,5,6,7,8,9]|15[0,1,2,3,4,5,6,7,8,9]|18[0236789]|14[57])\\d{8}$";
		return Match(mobileregex, mobile);
	}

	/**
	 * 
	 * @功能：电话验证
	 * 
	 * @作者： 杨栋栋
	 * @作成时间：2011-11-28 下午05:17:33
	 * 
	 * @修改者：
	 * @修改内容：
	 * @修改时间：
	 * 
	 * @param Tel
	 * @return
	 */
	public static boolean MatchTel(String Tel) {
		//String telregex = "(^[0-9]{3,4}-[0-9]{7,8}-[0-9]{3,4}$)|(^[0-9]{3,4}-[0-9]{7,8}$)|(^[0-9]{7,8}-[0-9]{3,4}$)|(^[0-9]{7,15}$)";
		String telregex="^(([0\\+]\\d{2,3}-)?(0\\d{2,3})-)?(\\d{7,8})(-(\\d{1,5}))?$";
		return Match(telregex, Tel);
	}

	/**
	 * 
	 * @功能：身份证号码验证
	 * 
	 * @作者： 杨栋栋
	 * @作成时间：2011-11-28 下午05:18:24
	 * 
	 * @修改者：
	 * @修改内容：
	 * @修改时间：
	 * 
	 * @param idcard
	 * @return
	 */
	public static boolean IdCardNo(String idcard) {
		HashMap<Integer, String> area = new HashMap<Integer, String>();
		area.put(11, "北京");
		area.put(12, "天津");
		area.put(13, "河北");
		area.put(14, "山西");
		area.put(15, "内蒙古");
		area.put(21, "辽宁");
		area.put(22, "吉林");
		area.put(23, "黑龙江");
		area.put(31, "上海");
		area.put(32, "江苏");
		area.put(33, "浙江");
		area.put(34, "安徽");
		area.put(35, "福建");
		area.put(36, "江西");
		area.put(37, "山东");
		area.put(41, "河南");
		area.put(42, "湖北");
		area.put(43, "湖南");
		area.put(44, "广东");
		area.put(45, "广西");
		area.put(46, "海南");
		area.put(50, "重庆");
		area.put(51, "四川");
		area.put(52, "贵州");
		area.put(53, "云南");
		area.put(54, "西藏");
		area.put(61, "陕西");
		area.put(62, "甘肃");
		area.put(63, "青海");
		area.put(64, "宁夏");
		area.put(65, "新疆");
		area.put(71, "台湾");
		area.put(81, "香港");
		area.put(82, "澳门");
		area.put(91, "国外");
		if (idcard==null||idcard.equals(""))
			return false;

		if (area.get(Integer.parseInt(idcard.substring(0, 2))) == null)
			return false;
		if (!(idcard.length() == 15 || idcard.length() == 18))
			return false;
		if (idcard.length() == 15) {
			// 老身份证
			int year = Integer.parseInt(idcard.substring(2, 6)) + 1900;
			String ereg;
			if (year % 4 == 0 || (year % 100 == 0 && year % 4 == 0)) {
				ereg = "^[1-9][0-9]{5}[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|[1-2][0-9]))[0-9]{3}$";// 测试出生日期的合法性
			} else {
				ereg = "^[1-9][0-9]{5}[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|1[0-9]|2[0-8]))[0-9]{3}$";// 测试出生日期的合法性
			}
			if (Match(ereg, idcard))
				return true;
			else
				return false;

		} else if (idcard.length() == 18) {
			// 新省份证
			// 18位身份号码检测
			// 出生日期的合法性检查
			// 闰年月日:((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|[1-2][0-9]))
			// 平年月日:((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|1[0-9]|2[0-8]))
			int year = Integer.parseInt(idcard.substring(2, 6)) + 1900;
			String ereg;
			if (year % 4 == 0 || (year % 100 == 0 && year % 4 == 0)) {
				ereg = "^[1-9][0-9]{5}19[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|[1-2][0-9]))[0-9]{3}[0-9Xx]$";// 闰年出生日期的合法性正则表达式
			} else {
				ereg = "^[1-9][0-9]{5}19[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|1[0-9]|2[0-8]))[0-9]{3}[0-9Xx]$";// 平年出生日期的合法性正则表达式
			}
			if (Match(ereg, idcard)) {// 测试出生日期的合法性
				// 计算校验位
				String[] idcards = new String[18];
				for (int i = 0; i < idcard.length(); i++) {
					idcards[i] = idcard.substring(i, i + 1);
				}
				int S = (Integer.valueOf(idcards[0]) + Integer.valueOf(idcards[10])) * 7
						+ (Integer.valueOf(idcards[1]) + Integer.valueOf(idcards[11])) * 9
						+ (Integer.valueOf(idcards[2]) + Integer.valueOf(idcards[12])) * 10
						+ (Integer.valueOf(idcards[3]) + Integer.valueOf(idcards[13])) * 5
						+ (Integer.valueOf(idcards[4]) + Integer.valueOf(idcards[14])) * 8
						+ (Integer.valueOf(idcards[5]) + Integer.valueOf(idcards[15])) * 4
						+ (Integer.valueOf(idcards[6]) + Integer.valueOf(idcards[16])) * 2 + Integer.valueOf(idcards[7]) * 1
						+ Integer.valueOf(idcards[8]) * 6 + Integer.valueOf(idcards[9]) * 3;
				int Y = S % 11;
				String M = "F";
				String JYM = "10X98765432";
				M = JYM.substring(Y, Y + 1);// 判断校验位
				if (M.equalsIgnoreCase(String.valueOf(idcards[17])))
					return true; // 检测ID的校验位
				else
					return false;
			} else
				return false;
		}
		return false;
	}

	public static void main(String[] args) {
		//System.out.println(MatchTel("68222868"));
	}
}
