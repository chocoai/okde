/**
 * 文件名：ExcelExportStudentEnrollmentSourceAction.java
 * 包名：net.cedu.action.report
 * 工程：cedu_pss_web
 * 功能： TODO /请自行添加
 *
 * 作者：yangdongdong    
 * 日期：2011-12-28 下午08:27:31
 *
 */
package net.cedu.action.report;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import net.cedu.action.BaseAction;
import net.cedu.biz.basesetting.BaseDictBiz;
import net.cedu.common.Constants;
import net.cedu.common.string.PingYingHanZiUtil;
import net.cedu.entity.basesetting.BaseDict;
import net.cedu.student.report.dao.EnrollmentWayReport;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.hssf.util.HSSFColor;
import org.springframework.beans.factory.annotation.Autowired;

public class ExcelExportStudentEnrollmentWayAction extends BaseAction {

	@Autowired
	private EnrollmentWayReport enrollmentWayReport;
	@Autowired
	private BaseDictBiz baseDictBiz;

	private Map<String, Integer> mapParams = new HashMap<String, Integer>();
	private Map<String, String> strParams = new HashMap<String, String>();
	private Map<String, Date> dateParams = new HashMap<String, Date>();

	@Override
	public String execute() throws Exception {

		List reportList = enrollmentWayReport.statistics(mapParams,strParams,dateParams);

		if (reportList != null && reportList.size() != 0) {
			downLoadFile(createExcel(reportList, "学生市场途径统计表 "), "学生市场途径统计表" + ".xls");

			return null;
		}
		return SUCCESS;

	}

	/**
	 * 
	 * @功能：创建excel标题
	 * 
	 * @作者： 杨栋栋
	 * @作成时间：2011-12-27 下午11:58:50
	 * 
	 * @修改者：
	 * @修改内容：
	 * @修改时间：
	 * 
	 * @param title
	 * @return
	 * @throws Exception
	 */
	public HSSFWorkbook createExcel(List reportList, String title)
			throws Exception {

		try {
			LinkedHashMap<String, List<BaseDict>> enrollmentWaysMap = new LinkedHashMap<String, List<BaseDict>>();

			LinkedHashMap<String, List<BaseDict>> enrollmentWaysMap_ = new LinkedHashMap<String, List<BaseDict>>();

			LinkedHashMap<BaseDict, List<BaseDict>> map = new LinkedHashMap<BaseDict, List<BaseDict>>();

			List<BaseDict> enrollmentWays = baseDictBiz
					.findBaseDictsByType(Constants.BASEDICT_STYLE_ENROLLMENTWAY);

			for (int i = 0; i < enrollmentWays.size(); i++) {
				BaseDict baseDict = enrollmentWays.get(i);
				if (baseDict.getParentNode() == 0) {
					map.put(baseDict, new ArrayList<BaseDict>());
				}
			}
			Set<BaseDict> key = map.keySet();
			for (Iterator it = key.iterator(); it.hasNext();) {
				BaseDict baseDictKey = (BaseDict) it.next();
				List<BaseDict> baseDictList = map.get(baseDictKey);
				for (int i = 0; i < enrollmentWays.size(); i++) {
					BaseDict baseDict = enrollmentWays.get(i);
					if (baseDict.getParentNode() == baseDictKey.getId()) {
						baseDictList.add(baseDict);
					}
				}
			}
			Set<BaseDict> key1 = map.keySet();
			for (Iterator it = key1.iterator(); it.hasNext();) {
				BaseDict baseDictKey = (BaseDict) it.next();
				List<BaseDict> baseDictList = map.get(baseDictKey);
				if (baseDictList.size() == 0) {
					baseDictList.add(baseDictKey);
				}
				enrollmentWaysMap.put(baseDictKey.getName(), baseDictList);

				// 市场途径名称转换拼音
				List<BaseDict> baseDictList_ = new ArrayList<BaseDict>();
				if (baseDictList != null) {
					BaseDict baseDict_ = null;
					for (BaseDict baseDict : baseDictList) {
						baseDict_ = new BaseDict();
						baseDict_.setName(PingYingHanZiUtil
								.getPingYingNameToLowerNew(baseDict.getName()));
						baseDictList_.add(baseDict_);
					}
				}
				enrollmentWaysMap_.put(PingYingHanZiUtil
						.getPingYingNameToLowerNew(baseDictKey.getName()),
						baseDictList_);
			}
			// request.setAttribute("enrollmentWaysMap", enrollmentWaysMap);
			// request.setAttribute("enrollmentWaysMapPingYin",
			// enrollmentWaysMap_);

			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet sheet = wb.createSheet();
			wb.setSheetName(0, title);
			HSSFRow titleRow = sheet.createRow(0);
			HSSFRow head = sheet.createRow(1);
			HSSFRow head2 = sheet.createRow(2);
			HSSFRow head3 = sheet.createRow(3);

			// 创建标题
			createTitle(wb, titleRow, title);
			createCell(wb, head, 0, "区域经理", HSSFColor.GREY_25_PERCENT.index);
			createCell(wb, head, 1, "学习中心", HSSFColor.GREY_25_PERCENT.index);
			createCell(wb, head, 2, "服务站", HSSFColor.GREY_25_PERCENT.index);
			createCell(wb, head, 3, "人员", HSSFColor.GREY_25_PERCENT.index);
			int h = 4;
			if (enrollmentWaysMap != null) {
				for (Entry<String, List<BaseDict>> entry : enrollmentWaysMap
						.entrySet()) {

					List<BaseDict> list = entry.getValue();
//					sheet.addMergedRegion(getCellRangeAddress(2, 2, h + 1,
//							(list.size() * 2) + h));
					sheet.addMergedRegion(getCellRangeAddress(2, 2, h + 1,
							(list.size() * 1) + h));
					for (int i = 0; i < list.size(); i++) {
						if (i == 0) {
							createCell(wb, head, h++, entry.getKey(),
									HSSFColor.GREY_25_PERCENT.index);
						} else {
							createCell(wb, head, h++, "",
									HSSFColor.GREY_25_PERCENT.index);
						}
//						createCell(wb, head, h++, "",
//								HSSFColor.GREY_25_PERCENT.index);
					}
				}
			}
			createCell(wb, head, h++, "合计", HSSFColor.GREY_25_PERCENT.index);
//			createCell(wb, head, h++, "", HSSFColor.GREY_25_PERCENT.index);

//			sheet.addMergedRegion(getCellRangeAddress(2, 3, h - 1, h));
			sheet.addMergedRegion(getCellRangeAddress(2, 3, h , h));

			createCell(wb, head2, 0, "", HSSFColor.GREY_25_PERCENT.index);
			createCell(wb, head2, 1, "", HSSFColor.GREY_25_PERCENT.index);

			createCell(wb, head2, 2, "", HSSFColor.GREY_25_PERCENT.index);
			createCell(wb, head2, 3, "", HSSFColor.GREY_25_PERCENT.index);

			int g = 4;
			if (enrollmentWaysMap != null) {
				for (Entry<String, List<BaseDict>> entry : enrollmentWaysMap
						.entrySet()) {
					List<BaseDict> list = entry.getValue();
					for (BaseDict baseDict : list) {
						createCell(wb, head2, g++, baseDict.getName(),
								HSSFColor.GREY_25_PERCENT.index);
//						sheet.addMergedRegion(getCellRangeAddress(3, 3, g,
//								g + 1));
//						createCell(wb, head2, g++, "",
//								HSSFColor.GREY_25_PERCENT.index);

					}

				}
			}
			createCell(wb, head2, g++, "", HSSFColor.GREY_25_PERCENT.index);
//			createCell(wb, head2, g++, "", HSSFColor.GREY_25_PERCENT.index);

			createCell(wb, head3, 0, "", HSSFColor.GREY_25_PERCENT.index);
			createCell(wb, head3, 1, "", HSSFColor.GREY_25_PERCENT.index);

			createCell(wb, head3, 2, "", HSSFColor.GREY_25_PERCENT.index);
			createCell(wb, head3, 3, "", HSSFColor.GREY_25_PERCENT.index);

			int d = 4;
			if (enrollmentWaysMap != null) {
				for (Entry<String, List<BaseDict>> entry : enrollmentWaysMap
						.entrySet()) {
					List<BaseDict> list = entry.getValue();
					for (BaseDict baseDict : list) {
						createCell(wb, head3, d++, "人数",
								HSSFColor.GREY_25_PERCENT.index);
//						createCell(wb, head3, d++, "比例",
//								HSSFColor.GREY_25_PERCENT.index);
					}
				}
			}
			createCell(wb, head3, d++, "人数", HSSFColor.GREY_25_PERCENT.index);
//			createCell(wb, head3, d++, "比例", HSSFColor.GREY_25_PERCENT.index);

			// 合并第一行
			sheet.addMergedRegion(getCellRangeAddress(1, 1, 1, d));
			sheet.addMergedRegion(getCellRangeAddress(2, 4, 1, 1));
			sheet.addMergedRegion(getCellRangeAddress(2, 4, 2, 2));
			sheet.addMergedRegion(getCellRangeAddress(2, 4, 3, 3));
			sheet.addMergedRegion(getCellRangeAddress(2, 4, 4, 4));
			
			
			//动态数据
			int row=4;//行的记录数
			for (int i = 0; i < reportList.size(); i++) {
				//区域经理对象
				Map quyuObject=getMap(reportList.get(i));
				
				//学习中心List
				List xuexizhongxinList=getList(quyuObject.get("xuexiList"));
				for (int j = 0; j < xuexizhongxinList.size(); j++) {
					//学习中心对象
					Map xuexizhongxinObject=getMap(xuexizhongxinList.get(j));
					//服务站list
					List fuwuzhanList=getList(xuexizhongxinObject.get("fuwuList"));
					for (int k = 0; k < fuwuzhanList.size(); k++) {
						//服务站对象
						Map fuwuzhanObject=getMap(fuwuzhanList.get(k));
						//用户列表
						List userList=getList(fuwuzhanObject.get("userList"));
						
						for (int l = 0; l < userList.size(); l++) {
							//用户对象
							Map userObject=getMap(userList.get(l));
							
							HSSFRow bodyRow = sheet.createRow(row++);
							createCell(wb, bodyRow, 0, quyuObject.get("quyuName").toString(),HSSFColor.WHITE.index);
							createCell(wb, bodyRow, 1, xuexizhongxinObject.get("xuexiName").toString(),HSSFColor.WHITE.index);
							createCell(wb, bodyRow, 2, fuwuzhanObject.get("fuwuName").toString(),HSSFColor.WHITE.index);
							createCell(wb, bodyRow, 3, userObject.get("name").toString(),HSSFColor.WHITE.index);
							
							int m = 4;
							if (enrollmentWaysMap_ != null) {
								for (Entry<String, List<BaseDict>> entry : enrollmentWaysMap_.entrySet()) {
									List<BaseDict> list = entry.getValue();
									for (BaseDict baseDict : list) {
										createCell(wb, bodyRow, m++, userObject.get(baseDict.getName()+"_count").toString(),HSSFColor.WHITE.index);
//										createCell(wb, bodyRow, m++, userObject.get(baseDict.getName()+"_percentage").toString(),HSSFColor.WHITE.index);
									}
								}
							}
							createCell(wb, bodyRow, m++, userObject.get("count").toString(),HSSFColor.YELLOW.index);
//							createCell(wb, bodyRow, m, userObject.get("countP").toString(),HSSFColor.YELLOW.index);
							
						}
						HSSFRow bodyRow = sheet.createRow(row++);
						createCell(wb, bodyRow, 0, quyuObject.get("quyuName").toString(),HSSFColor.GREY_25_PERCENT.index);
						createCell(wb, bodyRow, 1, xuexizhongxinObject.get("xuexiName").toString(),HSSFColor.GREY_25_PERCENT.index);
						createCell(wb, bodyRow, 2, fuwuzhanObject.get("fuwuName").toString(),HSSFColor.GREY_25_PERCENT.index);
						createCell(wb, bodyRow, 3, "小计",HSSFColor.GREY_25_PERCENT.index);
						int m = 4;
						if (enrollmentWaysMap_ != null) {
							for (Entry<String, List<BaseDict>> entry : enrollmentWaysMap_.entrySet()) {
								List<BaseDict> list = entry.getValue();
								for (BaseDict baseDict : list) {
									createCell(wb, bodyRow, m++, fuwuzhanObject.get(baseDict.getName()+"_fuwu_count").toString(),HSSFColor.GREY_25_PERCENT.index);
//									createCell(wb, bodyRow, m++, fuwuzhanObject.get(baseDict.getName()+"_fuwu_percentage").toString(),HSSFColor.GREY_25_PERCENT.index);
								}
							}
						}
						createCell(wb, bodyRow, m++, fuwuzhanObject.get("count").toString(),HSSFColor.GREY_25_PERCENT.index);
//						createCell(wb, bodyRow, m, fuwuzhanObject.get("countP").toString(),HSSFColor.GREY_25_PERCENT.index);
						
					}
					
					HSSFRow bodyRow = sheet.createRow(row++);
					createCell(wb, bodyRow, 0, quyuObject.get("quyuName").toString(),HSSFColor.SKY_BLUE.index);
					createCell(wb, bodyRow, 1, "合计",HSSFColor.SKY_BLUE.index);
					createCell(wb, bodyRow, 2, "",HSSFColor.SKY_BLUE.index);
					createCell(wb, bodyRow, 3, "",HSSFColor.SKY_BLUE.index);
					int m = 4;
					if (enrollmentWaysMap_ != null) {
						for (Entry<String, List<BaseDict>> entry : enrollmentWaysMap_.entrySet()) {
							List<BaseDict> list = entry.getValue();
							for (BaseDict baseDict : list) {
								createCell(wb, bodyRow, m++, xuexizhongxinObject.get(baseDict.getName()+"_branch_count").toString(),HSSFColor.SKY_BLUE.index);
//								createCell(wb, bodyRow, m++, xuexizhongxinObject.get(baseDict.getName()+"_branch_percentage").toString(),HSSFColor.SKY_BLUE.index);
							}
						}
					}
					createCell(wb, bodyRow, m++, xuexizhongxinObject.get("count").toString(),HSSFColor.SKY_BLUE.index);
//					createCell(wb, bodyRow, m, xuexizhongxinObject.get("countP").toString(),HSSFColor.SKY_BLUE.index);
					
					
					//合并单元格
					sheet.addMergedRegion(getCellRangeAddress(row, row, 2, 4));
					
				}
				
				HSSFRow bodyRow = sheet.createRow(row++);
				createCell(wb, bodyRow, 0, "总合计",HSSFColor.YELLOW.index);
				createCell(wb, bodyRow, 1, "",HSSFColor.YELLOW.index);
				createCell(wb, bodyRow, 2, "",HSSFColor.YELLOW.index);
				createCell(wb, bodyRow, 3, "",HSSFColor.YELLOW.index);
				int m = 4;
				if (enrollmentWaysMap_ != null) {
					for (Entry<String, List<BaseDict>> entry : enrollmentWaysMap_.entrySet()) {
						List<BaseDict> list = entry.getValue();
						for (BaseDict baseDict : list) {
							createCell(wb, bodyRow, m++, quyuObject.get(baseDict.getName()+"_zong_count").toString(),HSSFColor.YELLOW.index);
//							createCell(wb, bodyRow, m++, quyuObject.get(baseDict.getName()+"_zong_percentage").toString(),HSSFColor.YELLOW.index);
						}
					}
				}
				createCell(wb, bodyRow, m++, quyuObject.get("count").toString(),HSSFColor.YELLOW.index);
//				createCell(wb, bodyRow, m, quyuObject.get("countP").toString(),HSSFColor.YELLOW.index);
				
				//合并单元格
				sheet.addMergedRegion(getCellRangeAddress(row, row, 1, 4));
			}

			// 设置列宽
			sheet.setColumnWidth(1, 5000);// 单位
			sheet.setColumnWidth(2, 5000);// 单位
			sheet.setColumnWidth(3, 2000);// 单位

			return wb;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}

	}

	/**
	 * 
	 * @功能：创建标题
	 * 
	 * @作者： 杨栋栋
	 * @作成时间：2011-12-27 下午11:43:06
	 * 
	 * @修改者：
	 * @修改内容：
	 * @修改时间：
	 * 
	 * @param wb
	 * @param titleRow
	 * @param title
	 */
	private void createTitle(HSSFWorkbook wb, HSSFRow titleRow, String title) {
		titleRow.setHeight((short) 600);
		HSSFCell cell = titleRow.createCell(0);
		HSSFRichTextString h = new HSSFRichTextString(title);
		// 字体样式
		HSSFFont font = wb.createFont();
		font.setColor(HSSFColor.BLACK.index);
		font.setFontHeightInPoints((short) 18);
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		h.applyFont(font);
		cell.setCellValue(h);
		cell.setCellStyle(getCellDefaultStyle(wb, HSSFColor.WHITE.index));
	}

	/**
	 * 
	 * @功能：获取单元格默认样式
	 * 
	 * @作者： 杨栋栋
	 * @作成时间：2011-12-27 下午11:42:39
	 * 
	 * @修改者：
	 * @修改内容：
	 * @修改时间：
	 * 
	 * @param wb
	 * @return
	 */
	private HSSFCellStyle getCellDefaultStyle(HSSFWorkbook wb, short color) {
		// 设置单元格样式
		HSSFCellStyle cellstyle = wb.createCellStyle();
		cellstyle.setAlignment(HSSFCellStyle.ALIGN_CENTER_SELECTION);// 设置水平对齐方式
		cellstyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 设置垂直对齐方式
		cellstyle.setFillForegroundColor(color);

		cellstyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		cellstyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		cellstyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		cellstyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		cellstyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		cellstyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);

		cellstyle.setWrapText(true);
		return cellstyle;
	}

	/**
	 * 
	 * @功能：创建cell
	 * 
	 * @作者： 杨栋栋
	 * @作成时间：2011-12-27 下午11:46:12
	 * 
	 * @修改者：
	 * @修改内容：
	 * @修改时间：
	 * 
	 * @param wb
	 * @param row
	 * @param col
	 * @param val
	 */
	private void createCell(HSSFWorkbook wb, HSSFRow row, int col, String val,
			short color) {

		HSSFCell cell = row.createCell(col);
		cell.setCellValue(val);
		//cell.setCellStyle(getCellDefaultStyle(wb, color));

	}

	private Map getMap(Object o) {
		return (Map) o;
	}

	private List getList(Object o) {
		return (List) o;
	}

	private CellRangeAddress getCellRangeAddress(int startRow, int endRow,
			int startColumn, int endColumn) {
		return new CellRangeAddress(startRow - 1, endRow - 1, startColumn - 1,
				endColumn - 1);
	}

	public void setMapParams(Map<String, Integer> mapParams) {
		this.mapParams = mapParams;
	}

	public Map<String, Integer> getMapParams() {
		return mapParams;
	}

	public Map<String, String> getStrParams() {
		return strParams;
	}

	public void setStrParams(Map<String, String> strParams) {
		this.strParams = strParams;
	}

	public Map<String, Date> getDateParams() {
		return dateParams;
	}

	public void setDateParams(Map<String, Date> dateParams) {
		this.dateParams = dateParams;
	}
	
}
