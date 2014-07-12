package net.cedu.action.report;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.cedu.action.BaseAction;
import net.cedu.common.date.DateUtil;
import net.cedu.student.report.dao.BranchRebateReport;

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
public class ExcelExportBranchRebateAction extends BaseAction {

	@Autowired
	private BranchRebateReport branchRebateReport;
	
	private Map<String, Integer> mapParams = new HashMap<String, Integer>();
	private Map<String, Date> dateParams = new HashMap<String, Date>();

	@Override
	public String execute() throws Exception {

		Map map = branchRebateReport.statistics(mapParams,dateParams);
		List reportList = (List)map.get("reportList");
		if (reportList != null && reportList.size() != 0) {
			downLoadFile(createExcel(reportList, "招生返款情况表",map), ""+DateUtil.dateToString(dateParams.get("startDate"),"yyyy-MM-dd")+"_"+DateUtil.dateToString(dateParams.get("endDate"),"yyyy-MM-dd")+"招生返款情况表"+ ".xls");
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

			// 创建标题
			createTitle(wb, titleRow, title);
			
			createCell(wb, head, 0, "序号",HSSFColor.GREY_25_PERCENT.index);
			createCell(wb, head, 1, "学习中心",HSSFColor.GREY_25_PERCENT.index);
			createCell(wb, head, 2, "返款开始日期",HSSFColor.GREY_25_PERCENT.index);
			createCell(wb, head, 3, "返款结束日期",HSSFColor.GREY_25_PERCENT.index);
			createCell(wb, head, 4, "渠道(已返/应返)",HSSFColor.GREY_25_PERCENT.index);
			createCell(wb, head, 5, "老带新(已返/应返)",HSSFColor.GREY_25_PERCENT.index);
			createCell(wb, head, 6, "大客户(已返/应返)",HSSFColor.GREY_25_PERCENT.index);
			createCell(wb, head, 7, "加盟(已返/应返)",HSSFColor.GREY_25_PERCENT.index);
			createCell(wb, head, 8, "共建(已返/应返)",HSSFColor.GREY_25_PERCENT.index);
			createCell(wb, head, 9, "其他(已返/应返)",HSSFColor.GREY_25_PERCENT.index);
			createCell(wb, head, 10, "调整金额(已返/应返)",HSSFColor.GREY_25_PERCENT.index);
			createCell(wb, head, 11, "应返款额合计",HSSFColor.GREY_25_PERCENT.index);
			createCell(wb, head, 12, "已返款额合计",HSSFColor.GREY_25_PERCENT.index);
			createCell(wb, head, 13, "未返款额合计",HSSFColor.GREY_25_PERCENT.index);
			
			sheet.addMergedRegion(getCellRangeAddress(1, 1, 1, 14));
			
			//动态数据
			
			int row=2;//行的记录数

			for (int i = 0; i < reportList.size(); i++) {
				Map object=getMap(reportList.get(i));
				
				HSSFRow bodyRow = sheet.createRow(row++);
				createCell(wb, bodyRow, 0, object.get("number").toString(),HSSFColor.WHITE.index);
				createCell(wb, bodyRow, 1, object.get("branchName").toString(),HSSFColor.WHITE.index);
				createCell(wb, bodyRow, 2, object.get("startDate").toString(),HSSFColor.WHITE.index);
				createCell(wb, bodyRow, 3, object.get("endDate").toString(),HSSFColor.WHITE.index);
				createCell(wb, bodyRow, 4, object.get("qudaoMoney").toString(),HSSFColor.WHITE.index);
				createCell(wb, bodyRow, 5, object.get("laodaixinMoney").toString(),HSSFColor.WHITE.index);
				createCell(wb, bodyRow, 6, object.get("dakehuMoney").toString(),HSSFColor.WHITE.index);
				createCell(wb, bodyRow, 7, object.get("jiamengMoney").toString(),HSSFColor.WHITE.index);
				createCell(wb, bodyRow, 8, object.get("gongjianMoney").toString(),HSSFColor.WHITE.index);
				createCell(wb, bodyRow, 9, object.get("otherMoney").toString(),HSSFColor.WHITE.index);
				createCell(wb, bodyRow, 10, object.get("tiaozhengMoney").toString(),HSSFColor.WHITE.index);
				createCell(wb, bodyRow, 11, object.get("yingHejiMoney").toString(),HSSFColor.WHITE.index);
				createCell(wb, bodyRow, 12, object.get("yiHejiMoney").toString(),HSSFColor.WHITE.index);
				createCell(wb, bodyRow, 13, object.get("weiHejiMoney").toString(),HSSFColor.WHITE.index);
			}
			HSSFRow bodyRow = sheet.createRow(row++);
			createCell(wb, bodyRow, 0, "",HSSFColor.YELLOW.index);
			createCell(wb, bodyRow, 1, "",HSSFColor.YELLOW.index);
			createCell(wb, bodyRow, 2,"",HSSFColor.YELLOW.index);
			createCell(wb, bodyRow, 3,"",HSSFColor.YELLOW.index);
			createCell(wb, bodyRow, 4, result.get("qudaoMoneySum").toString(),HSSFColor.YELLOW.index);
			createCell(wb, bodyRow, 5, result.get("laodaixinMoneySum").toString(),HSSFColor.YELLOW.index);
			createCell(wb, bodyRow, 6, result.get("dakehuMoneySum").toString(),HSSFColor.YELLOW.index);
			createCell(wb, bodyRow, 7, result.get("jiamengMoneySum").toString(),HSSFColor.YELLOW.index);
			createCell(wb, bodyRow, 8, result.get("gongjianMoneySum").toString(),HSSFColor.YELLOW.index);
			createCell(wb, bodyRow, 9, result.get("otherMoneySum").toString(),HSSFColor.YELLOW.index);
			createCell(wb, bodyRow, 10, result.get("tiaozhengMoneySum").toString(),HSSFColor.YELLOW.index);
			createCell(wb, bodyRow, 11, result.get("yingHejiMoneySum").toString(),HSSFColor.YELLOW.index);
			createCell(wb, bodyRow, 12, result.get("yiHejiMoneySum").toString(),HSSFColor.YELLOW.index);
			createCell(wb, bodyRow, 13, result.get("weiHejiMoneySum").toString(),HSSFColor.YELLOW.index);
			// 设置列宽
			sheet.setColumnWidth(0, 3000);// 单位
			sheet.setColumnWidth(1, 5000);// 单位
			sheet.setColumnWidth(2, 5000);// 单位
			sheet.setColumnWidth(3, 5000);// 单位
			sheet.setColumnWidth(4, 5000);// 单位
			sheet.setColumnWidth(5, 5000);// 单位
			sheet.setColumnWidth(6, 5000);// 单位
			sheet.setColumnWidth(7, 5000);// 单位
			sheet.setColumnWidth(8, 5000);// 单位
			sheet.setColumnWidth(9, 5000);// 单位
			sheet.setColumnWidth(10, 5000);// 单位
			sheet.setColumnWidth(11, 5000);// 单位
			sheet.setColumnWidth(12, 5000);// 单位
			sheet.setColumnWidth(13, 5000);// 单位
			
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
//			System.out.println(val);
			if(!val.endsWith(".00")&&!val.endsWith(".000")){
				cell.setCellStyle(getCellDefaultStyle(wb,color));
			}else{
				cell.setCellStyle(getCellMoneyStyle(wb,color));
			}
		}else{
			cell.setCellStyle(getCellDefaultStyle(wb,color));
		}
		
		

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
}
