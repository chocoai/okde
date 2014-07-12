package net.cedu.action.report;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.cedu.action.BaseAction;
import net.cedu.biz.basesetting.FeeSubjectBiz;
import net.cedu.common.date.DateUtil;
import net.cedu.entity.basesetting.FeeSubject;
import net.cedu.student.report.dao.CeduAnnualIncomeReport;

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
/**
 * 导出弘成学苑收入情况表 
 * @author yangdongdong
 *
 */
public class ExcelExportCeduAnnualIncomeAction extends BaseAction {

	@Autowired
	private CeduAnnualIncomeReport ceduAnnualIncomeReport;
	@Autowired
	private FeeSubjectBiz feeSubjectBiz;
	
	private Map<String, Integer> mapParams = new HashMap<String, Integer>();
	private Map<String, Date> dateParams = new HashMap<String, Date>();
	private Map<String, String> strParams = new HashMap<String, String>();

	@Override
	public String execute() throws Exception {

		Map map = ceduAnnualIncomeReport.statistics(mapParams,strParams,dateParams);
		List reportList = (List)map.get("branchList");
		// edited by dongminghao 取消reportList.size()!=0判断，即使无数据也可导出一份空报表
		if (reportList != null) {
			downLoadFile(createExcel(reportList, "中心收入情况表",map), ""+DateUtil.dateToString(dateParams.get("startDate"),"yyyy-MM-dd")+"_"+DateUtil.dateToString(dateParams.get("endDate"),"yyyy-MM-dd")+"中心收入情况表"+ ".xls");
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
	public HSSFWorkbook createExcel(List reportList, String title,Map result)
			throws Exception {
		try {

			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet sheet = wb.createSheet();
			wb.setSheetName(0, title);
			HSSFRow titleRow = sheet.createRow(0);
			HSSFRow head = sheet.createRow(1);

			List<Integer> feeSubjectIdsList = new ArrayList<Integer>();
			
			// 创建标题
			createTitle(wb, titleRow, title);
			int colInt=0;
			createCell(wb, head, colInt++, "返款开始时间",HSSFColor.GREY_25_PERCENT.index);
			createCell(wb, head, colInt++, "返款结束时间",HSSFColor.GREY_25_PERCENT.index);
			createCell(wb, head, colInt++, "学习中心",HSSFColor.GREY_25_PERCENT.index);
			createCell(wb, head, colInt++, "院校",HSSFColor.GREY_25_PERCENT.index);
			createCell(wb, head, colInt++, "缴费人数",HSSFColor.GREY_25_PERCENT.index);
			if(strParams.get("feeSubjectIds")!=null && !strParams.get("feeSubjectIds").equals("")){
				String[] idStrs = strParams.get("feeSubjectIds").split(",");
				if(idStrs!=null && idStrs.length>0){
					for(String idStr : idStrs){
						if(idStr!=null && !idStr.equals("")){
							feeSubjectIdsList.add(getIntByString(idStr));
							FeeSubject feeSubject = feeSubjectBiz.findFeeSubjectById(getIntByString(idStr));
							createCell(wb, head, colInt++, feeSubject==null?"--(已收/应收)":feeSubject.getName()+"(已收/应收)",HSSFColor.GREY_25_PERCENT.index);
						}
					}
				}
			}
			createCell(wb, head, colInt++, "其他(已收/应收)",HSSFColor.GREY_25_PERCENT.index);
			createCell(wb, head, colInt++, "调整金额(已收/应收)",HSSFColor.GREY_25_PERCENT.index);
			createCell(wb, head, colInt++, "应收院校款(已收/应收)",HSSFColor.GREY_25_PERCENT.index);
			createCell(wb, head, colInt++, "应收合计",HSSFColor.GREY_25_PERCENT.index);
			createCell(wb, head, colInt++, "已收合计",HSSFColor.GREY_25_PERCENT.index);
			createCell(wb, head, colInt++, "未收合计",HSSFColor.GREY_25_PERCENT.index);
			
			sheet.addMergedRegion(getCellRangeAddress(1, 1, 1, colInt));
			
			//动态数据
			
			int row=2;//行的记录数

			List branchList = (List)result.get("branchList");
			if(branchList!=null && branchList.size()>0){
				for (int i = 0; i < branchList.size(); i++) {
					Map branch=getMap(branchList.get(i));
					int branchBeginRowIndex = row+1;
					if(branch!=null){
						List academyList = (List)branch.get("branch_academy_list");
						if(academyList!=null && academyList.size()>0){
							for(int j = 0; j<academyList.size(); j++){
								Map academy = getMap(academyList.get(j));
								if(academy!=null){
									//academy
									HSSFRow bodyRow = sheet.createRow(row++);
									int colInt2 = 0;
									createCell(wb, bodyRow, colInt2++, result.get("start_date").toString(),HSSFColor.WHITE.index);
									createCell(wb, bodyRow, colInt2++, result.get("end_date").toString(),HSSFColor.WHITE.index);
									createCell(wb, bodyRow, colInt2++, branch.get("branch_name").toString(),HSSFColor.WHITE.index);
									createCell(wb, bodyRow, colInt2++, academy.get("academy_name").toString(),HSSFColor.WHITE.index);
									createCell(wb, bodyRow, colInt2++, academy.get("jiaofeiCount").toString(),HSSFColor.WHITE.index);
									for(Integer temp : feeSubjectIdsList){
										createCell(wb, bodyRow, colInt2++, academy.get("feeSubject_"+temp).toString(),HSSFColor.WHITE.index);
									}
									createCell(wb, bodyRow, colInt2++, academy.get("otherMoney").toString(),HSSFColor.WHITE.index);
									createCell(wb, bodyRow, colInt2++, academy.get("tiaozhengSum").toString(),HSSFColor.WHITE.index);
									createCell(wb, bodyRow, colInt2++, academy.get("yingshouyuanxiaoSum").toString(),HSSFColor.WHITE.index);
									createCell(wb, bodyRow, colInt2++, academy.get("yingfan_sum").toString(),HSSFColor.WHITE.index);
									createCell(wb, bodyRow, colInt2++, academy.get("shifan_sum").toString(),HSSFColor.WHITE.index);
									createCell(wb, bodyRow, colInt2++, academy.get("weifan_sum").toString(),HSSFColor.WHITE.index);
								}
							}
						}
						//branch
						sheet.addMergedRegion(getCellRangeAddress(branchBeginRowIndex, row, 3, 3));
						HSSFRow bodyRow = sheet.createRow(row++);
						int colInt3 = 0;
						createCell(wb, bodyRow, colInt3++,"中心合计",HSSFColor.SKY_BLUE.index);
						createCell(wb, bodyRow, colInt3++,"",HSSFColor.SKY_BLUE.index);
						createCell(wb, bodyRow, colInt3++,"",HSSFColor.SKY_BLUE.index);
						createCell(wb, bodyRow, colInt3++,"",HSSFColor.SKY_BLUE.index);
						createCell(wb, bodyRow, colInt3++, branch.get("branch_jiaofeiCount").toString(),HSSFColor.SKY_BLUE.index);
						for(Integer temp : feeSubjectIdsList){
							createCell(wb, bodyRow, colInt3++, branch.get("branch_feeSubject_"+temp).toString(),HSSFColor.SKY_BLUE.index);
						}
						createCell(wb, bodyRow, colInt3++, branch.get("branch_otherMoney").toString(),HSSFColor.SKY_BLUE.index);
						createCell(wb, bodyRow, colInt3++, branch.get("branch_tiaozhengSum").toString(),HSSFColor.SKY_BLUE.index);
						createCell(wb, bodyRow, colInt3++, branch.get("branch_yingshouyuanxiaoSum").toString(),HSSFColor.SKY_BLUE.index);
						createCell(wb, bodyRow, colInt3++, branch.get("branch_yingfanSum").toString(),HSSFColor.SKY_BLUE.index);
						createCell(wb, bodyRow, colInt3++, branch.get("branch_shifanSum").toString(),HSSFColor.SKY_BLUE.index);
						createCell(wb, bodyRow, colInt3++, branch.get("branch_weifanSum").toString(),HSSFColor.SKY_BLUE.index);
						
						sheet.addMergedRegion(getCellRangeAddress(row, row, 1, 4));
						
					}
				}
			}
			//all
			HSSFRow bodyRow = sheet.createRow(row++);
			int colInt4 = 0;
			createCell(wb, bodyRow, colInt4++,"总合计",HSSFColor.YELLOW.index);
			createCell(wb, bodyRow, colInt4++,"",HSSFColor.YELLOW.index);
			createCell(wb, bodyRow, colInt4++,"",HSSFColor.YELLOW.index);
			createCell(wb, bodyRow, colInt4++,"",HSSFColor.YELLOW.index);
			createCell(wb, bodyRow, colInt4++, result.get("all_jiaofeiCount").toString(),HSSFColor.YELLOW.index);
			for(Integer temp : feeSubjectIdsList){
				createCell(wb, bodyRow, colInt4++, result.get("all_feeSubject_"+temp).toString(),HSSFColor.YELLOW.index);
			}
			createCell(wb, bodyRow, colInt4++, result.get("all_otherMoney").toString(),HSSFColor.YELLOW.index);
			createCell(wb, bodyRow, colInt4++, result.get("all_tiaozhengSum").toString(),HSSFColor.YELLOW.index);
			createCell(wb, bodyRow, colInt4++, result.get("all_yingshouyuanxiaoSum").toString(),HSSFColor.YELLOW.index);
			createCell(wb, bodyRow, colInt4++, result.get("all_yingfanSum").toString(),HSSFColor.YELLOW.index);
			createCell(wb, bodyRow, colInt4++, result.get("all_shifanSum").toString(),HSSFColor.YELLOW.index);
			createCell(wb, bodyRow, colInt4++, result.get("all_weifanSum").toString(),HSSFColor.YELLOW.index);
			
			sheet.addMergedRegion(getCellRangeAddress(row, row, 1, 4));
			
			// 设置列宽
//			sheet.setColumnWidth(0, 3000);// 单位
//			sheet.setColumnWidth(1, 5000);// 单位
//			sheet.setColumnWidth(2, 5000);// 单位
//			sheet.setColumnWidth(3, 5000);// 单位
//			sheet.setColumnWidth(6, 5000);// 单位
//			sheet.setColumnWidth(7, 5000);// 单位
//			sheet.setColumnWidth(8, 5000);// 单位
//			sheet.setColumnWidth(9, 5000);// 单位
//			sheet.setColumnWidth(10, 5000);// 单位
//			sheet.setColumnWidth(11, 5000);// 单位
//			sheet.setColumnWidth(12, 5000);// 单位
//			sheet.setColumnWidth(13, 5000);// 单位			
			
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
		cell.setCellStyle(getCellDefaultStyle(wb,HSSFColor.WHITE.index));
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
	private HSSFCellStyle getCellDefaultStyle(HSSFWorkbook wb,short color) {
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
	
	private HSSFCellStyle getCellMoneyStyle(HSSFWorkbook wb,short color) {
		// 设置单元格样式
		HSSFCellStyle cellstyle = wb.createCellStyle();
		cellstyle.setAlignment(HSSFCellStyle.ALIGN_RIGHT);// 设置水平对齐方式
		cellstyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 设置垂直对齐方式
		cellstyle.setFillForegroundColor(color);

		cellstyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		cellstyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		cellstyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		cellstyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		cellstyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		cellstyle.setAlignment(HSSFCellStyle.ALIGN_RIGHT);

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
	private void createCell(HSSFWorkbook wb, HSSFRow row, int col, String val,short color) {

		HSSFCell cell = row.createCell(col);
		cell.setCellValue(val);
		if(val!=null){
			System.out.println(val);
			if(!val.endsWith(".00")&&!val.endsWith(".000")){
				cell.setCellStyle(getCellDefaultStyle(wb,color));
			}else{
				cell.setCellStyle(getCellMoneyStyle(wb,color));
			}
		}else{
			cell.setCellStyle(getCellDefaultStyle(wb,color));
		}
		
		

	}
	
	// String转int
	private int getIntByString(String i){
		if(i!=null){
			try {
				return Integer.parseInt(i);
			} catch (NumberFormatException e) {
				return 0;
			}
		}
		return 0;
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

	public Map<String, Date> getDateParams() {
		return dateParams;
	}

	public void setDateParams(Map<String, Date> dateParams) {
		this.dateParams = dateParams;
	}

	public Map<String, String> getStrParams() {
		return strParams;
	}

	public void setStrParams(Map<String, String> strParams) {
		this.strParams = strParams;
	}
}
