/**
 * 文件名：ExcelExportStudentNewEnrollmentAction.java
 * 包名：net.cedu.action.report
 * 工程：cedu_pss_web
 * 功能： TODO /请自行添加
 *
 * 作者：yangdongdong    
 * 日期：2011-12-30 上午11:00:40
 *
*/
package net.cedu.action.report;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.cedu.action.BaseAction;
import net.cedu.student.report.dao.NewEnrollmentReport;

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
 * 
 * @功能：服务站招生统计表
 *
 * @作者： 杨栋栋
 * @作成时间：2011-12-30 上午11:00:44
 *
 * @修改者：
 * @修改内容：
 * @修改时间：
 *
 */
public class ExcelExportServiceStationAction extends BaseAction {

	@Autowired
	private NewEnrollmentReport newEnrollmentReport;

	private Map<String, Integer> mapParams = new HashMap<String, Integer>();
	
	private Map<String, Date> dateParams = new HashMap<String, Date>();

	@Override
	public String execute() throws Exception {

		Map map=newEnrollmentReport.statistics(mapParams,dateParams);
		List reportList = (List)map.get("quyuList");
		if (reportList != null && reportList.size() != 0) {
			downLoadFile(createExcel(reportList, "周例会服务站招生统计",map), "周例会服务站招生统计"+ ".xls");
			
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
	public HSSFWorkbook createExcel(List reportList, String title,Map resultMap)
			throws Exception {
		try {

			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet sheet = wb.createSheet();
			wb.setSheetName(0, title);
			HSSFRow titleRow = sheet.createRow(0);
			HSSFRow head = sheet.createRow(1);
			HSSFRow head2 = sheet.createRow(2);

			// 创建标题
			createTitle(wb, titleRow, title);
			
			createCell(wb, head, 0, "显示内容",HSSFColor.DARK_YELLOW.index);
			createCell(wb, head, 1, "",HSSFColor.DARK_YELLOW.index);
			createCell(wb, head, 2, "",HSSFColor.DARK_YELLOW.index);
			createCell(wb, head, 3, "",HSSFColor.DARK_YELLOW.index);
			
			createCell(wb, head, 4, "招生指标",HSSFColor.GREY_25_PERCENT.index);
			
			createCell(wb, head, 5, "新生报名情况 ",HSSFColor.DARK_YELLOW.index);
			createCell(wb, head, 6, "",HSSFColor.DARK_YELLOW.index);
			createCell(wb, head, 7, "",HSSFColor.DARK_YELLOW.index);
			createCell(wb, head, 8, "",HSSFColor.DARK_YELLOW.index);
			createCell(wb, head, 9, "",HSSFColor.DARK_YELLOW.index);
			createCell(wb, head, 10, "",HSSFColor.DARK_YELLOW.index);
			
			createCell(wb, head, 11, "新生录取情况",HSSFColor.GREY_25_PERCENT.index);
			createCell(wb, head, 12, "",HSSFColor.GREY_25_PERCENT.index);
			createCell(wb, head, 13, "",HSSFColor.GREY_25_PERCENT.index);
			createCell(wb, head, 14, "",HSSFColor.GREY_25_PERCENT.index);
			createCell(wb, head, 15, "",HSSFColor.GREY_25_PERCENT.index);
			createCell(wb, head, 16, "",HSSFColor.GREY_25_PERCENT.index);
			
			createCell(wb, head, 17, "新生缴费情况",HSSFColor.DARK_YELLOW.index);
			createCell(wb, head, 18, "",HSSFColor.DARK_YELLOW.index);
			createCell(wb, head, 19, "",HSSFColor.DARK_YELLOW.index);
			createCell(wb, head, 20, "",HSSFColor.DARK_YELLOW.index);
			createCell(wb, head, 21, "",HSSFColor.DARK_YELLOW.index);
			createCell(wb, head, 22, "",HSSFColor.DARK_YELLOW.index);
			
			
			
			createCell(wb, head2, 0, "区域经理",HSSFColor.DARK_YELLOW.index);
			createCell(wb, head2, 1, "学习中心",HSSFColor.DARK_YELLOW.index);
			createCell(wb, head2, 2, "服务站",HSSFColor.DARK_YELLOW.index);
			createCell(wb, head2, 3, "中心主任",HSSFColor.DARK_YELLOW.index);
			
			createCell(wb, head2, 4, "招生人数指标",HSSFColor.GREY_25_PERCENT.index);
			
			createCell(wb, head2, 5, "时间段内报名人数",HSSFColor.DARK_YELLOW.index);
			createCell(wb, head2, 6, "时间段内报名排名",HSSFColor.DARK_YELLOW.index);
			createCell(wb, head2, 7, "累计报名人数",HSSFColor.DARK_YELLOW.index);
			createCell(wb, head2, 8, "累计报名人数排名",HSSFColor.DARK_YELLOW.index);
			createCell(wb, head2, 9, "累计完成率",HSSFColor.DARK_YELLOW.index);
			createCell(wb, head2, 10, "累计完成率排名",HSSFColor.DARK_YELLOW.index);
			
			createCell(wb, head2, 11, "时间段内录取人数",HSSFColor.GREY_25_PERCENT.index);
			createCell(wb, head2, 12, "时间段内录取排名 ",HSSFColor.GREY_25_PERCENT.index);
			createCell(wb, head2, 13, "累计录取人数",HSSFColor.GREY_25_PERCENT.index);
			createCell(wb, head2, 14, "累计录取人数排名",HSSFColor.GREY_25_PERCENT.index);
			createCell(wb, head2, 15, "累计完成率",HSSFColor.GREY_25_PERCENT.index);
			createCell(wb, head2, 16, "累计完成率排名 ",HSSFColor.GREY_25_PERCENT.index);
			
			createCell(wb, head2, 17, "时间段内缴费人数",HSSFColor.DARK_YELLOW.index);
			createCell(wb, head2, 18, "时间段内缴费排名",HSSFColor.DARK_YELLOW.index);
			createCell(wb, head2, 19, "累计缴费人数",HSSFColor.DARK_YELLOW.index);
			createCell(wb, head2, 20, "累计缴费人数排名",HSSFColor.DARK_YELLOW.index);
			createCell(wb, head2, 21, "累计完成率",HSSFColor.DARK_YELLOW.index);
			createCell(wb, head2, 22, "累计完成率排名 ",HSSFColor.DARK_YELLOW.index);
			
			
			
			
			
			//标题合并
			sheet.addMergedRegion(getCellRangeAddress(1, 1, 1, 23));
			
			sheet.addMergedRegion(getCellRangeAddress(2, 2, 1, 4));
			sheet.addMergedRegion(getCellRangeAddress(2, 2, 6, 11));
			sheet.addMergedRegion(getCellRangeAddress(2, 2, 12, 17));
			sheet.addMergedRegion(getCellRangeAddress(2, 2, 18, 23));
			
			//动态数据
			
			int row=3;//行的记录数
			int q=4;
			int x=4;
			int f=4;
			
			
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
						
						HSSFRow bodyRow = sheet.createRow(row++);
						Map fuwuzhanHeJiMap=getMap(fuwuzhanObject.get("fuwuzhanHeJiMap"));
						createCell(wb, bodyRow, 0, quyuObject.get("quyuName").toString(),HSSFColor.WHITE.index);
						createCell(wb, bodyRow, 1, xuexizhongxinObject.get("xuexiName").toString(),HSSFColor.WHITE.index);
						createCell(wb, bodyRow, 2, fuwuzhanObject.get("fuwuName").toString(),HSSFColor.WHITE.index);
						createCell(wb, bodyRow, 3, fuwuzhanObject.get("zhurenName").toString(),HSSFColor.WHITE.index);
						
						createCell(wb, bodyRow, 4, fuwuzhanHeJiMap.get("userZhaoShengZhiBiaoSum").toString(),HSSFColor.WHITE.index);
						
						createCell(wb, bodyRow, 5, fuwuzhanHeJiMap.get("dateBaoMingCountSum").toString(),HSSFColor.WHITE.index);
						createCell(wb, bodyRow, 6, fuwuzhanHeJiMap.get("dateBaoMingCountSumSort").toString(),HSSFColor.WHITE.index);
						createCell(wb, bodyRow, 7, fuwuzhanHeJiMap.get("leijiBaoMingCountSum").toString(),HSSFColor.WHITE.index);
						createCell(wb, bodyRow, 8, fuwuzhanHeJiMap.get("leijiBaoMingCountSumSort").toString(),HSSFColor.WHITE.index);
						createCell(wb, bodyRow, 9, fuwuzhanHeJiMap.get("leijiBaoMingCountPSum").toString(),HSSFColor.WHITE.index);
						createCell(wb, bodyRow, 10, fuwuzhanHeJiMap.get("leijiBaoMingCountPSumSort").toString(),HSSFColor.WHITE.index);
						
						createCell(wb, bodyRow, 11, fuwuzhanHeJiMap.get("leijiLuQuCountSum").toString(),HSSFColor.WHITE.index);
						createCell(wb, bodyRow, 12, fuwuzhanHeJiMap.get("dateLuQuCountSumSort").toString(),HSSFColor.WHITE.index);
						createCell(wb, bodyRow, 13, fuwuzhanHeJiMap.get("leijiLuQuCountSum").toString(),HSSFColor.WHITE.index);
						createCell(wb, bodyRow, 14, fuwuzhanHeJiMap.get("leijiLuQuCountSumSort").toString(),HSSFColor.WHITE.index);
						createCell(wb, bodyRow, 15, fuwuzhanHeJiMap.get("leijiLuQuCountPSum").toString(),HSSFColor.WHITE.index);
						createCell(wb, bodyRow, 16, fuwuzhanHeJiMap.get("leijiLuQuCountPSumSort").toString(),HSSFColor.WHITE.index);
						
						createCell(wb, bodyRow, 17, fuwuzhanHeJiMap.get("dateJiaoFeiCountSum").toString(),HSSFColor.WHITE.index);
						createCell(wb, bodyRow, 18, fuwuzhanHeJiMap.get("dateJiaoFeiCountSumSort").toString(),HSSFColor.WHITE.index);
						createCell(wb, bodyRow, 19, fuwuzhanHeJiMap.get("leijiJiaoFeiCountSum").toString(),HSSFColor.WHITE.index);
						createCell(wb, bodyRow, 20, fuwuzhanHeJiMap.get("leijiJiaoFeiCountSumSort").toString(),HSSFColor.WHITE.index);
						createCell(wb, bodyRow, 21, fuwuzhanHeJiMap.get("leijiJiaoFeiCountPSum").toString(),HSSFColor.WHITE.index);
						createCell(wb, bodyRow, 22, fuwuzhanHeJiMap.get("leijiJiaoFeiCountPSumSort").toString(),HSSFColor.WHITE.index);

					}
					
				}
				
			}
			
			
			HSSFRow bodyRow = sheet.createRow(row++);
			createCell(wb, bodyRow, 0, "总合计",HSSFColor.YELLOW.index);
			createCell(wb, bodyRow, 1, "",HSSFColor.YELLOW.index);
			createCell(wb, bodyRow, 2, "",HSSFColor.YELLOW.index);
			createCell(wb, bodyRow, 3, "",HSSFColor.YELLOW.index);
			
			createCell(wb, bodyRow, 4, resultMap.get("zhibiaoSum").toString(),HSSFColor.YELLOW.index);
			createCell(wb, bodyRow, 5, resultMap.get("dateBaomingSum").toString(),HSSFColor.YELLOW.index);
			createCell(wb, bodyRow, 6, "",HSSFColor.YELLOW.index);
			createCell(wb, bodyRow, 7, resultMap.get("leijiBaomingSum").toString(),HSSFColor.YELLOW.index);
			createCell(wb, bodyRow, 8, "",HSSFColor.YELLOW.index);
			createCell(wb, bodyRow, 9,resultMap.get("leijiBaomingSumP").toString(),HSSFColor.YELLOW.index);
			createCell(wb, bodyRow, 10, "",HSSFColor.YELLOW.index);
			
			createCell(wb, bodyRow, 11, resultMap.get("dateLuquSum").toString(),HSSFColor.YELLOW.index);
			createCell(wb, bodyRow, 12, "",HSSFColor.YELLOW.index);
			createCell(wb, bodyRow, 13, resultMap.get("leijiLuquSum").toString(),HSSFColor.YELLOW.index);
			createCell(wb, bodyRow, 14, "",HSSFColor.YELLOW.index);
			createCell(wb, bodyRow, 15, resultMap.get("leijiLuquSumP").toString(),HSSFColor.YELLOW.index);
			
			createCell(wb, bodyRow, 16, "",HSSFColor.YELLOW.index);
			createCell(wb, bodyRow, 17,	resultMap.get("dateJiaofeiSum").toString(),HSSFColor.YELLOW.index);
			createCell(wb, bodyRow, 18, "",HSSFColor.YELLOW.index);
			createCell(wb, bodyRow, 19, resultMap.get("leijiJiaofeiSum").toString(),HSSFColor.YELLOW.index);
			createCell(wb, bodyRow, 20, "",HSSFColor.YELLOW.index);
			createCell(wb, bodyRow, 21, resultMap.get("leijiJiaofeiSumP").toString(),HSSFColor.YELLOW.index);
			createCell(wb, bodyRow, 22, "",HSSFColor.YELLOW.index);
			
			
			//合并单元格
			sheet.addMergedRegion(getCellRangeAddress(row, row, 1, 4));
			
			
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
		cell.setCellStyle(getCellDefaultStyle(wb,color));

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
