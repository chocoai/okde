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
import net.cedu.student.report.dao.BranchDirectorDailyReport;

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
 * @功能：学习中心日报表
 *
 * @作者： 董溟浩
 * @作成时间：2012-03-06 上午10:50:15
 *
 * @修改者：
 * @修改内容：
 * @修改时间：
 *
 */
public class ExcelExportBranchDirectorDailyAction extends BaseAction {

	@Autowired
	private BranchDirectorDailyReport branchDirectorDailyReport;

	private Map<String, Integer> mapParams = new HashMap<String, Integer>();
	
	private Map<String, String> strParams = new HashMap<String, String>();
	
	private Map<String, Date> dateParams = new HashMap<String, Date>();

	@Override
	public String execute() throws Exception {

		mapParams.put("currentUserId", super.getUser().getUserId());
		List reportList = branchDirectorDailyReport.statisticsByDate(mapParams,strParams,dateParams);

		if (reportList != null && reportList.size() != 0) {
			downLoadFile(createExcel(reportList, "学习中心日报表"), "学习中心日报表"+ ".xls");
			
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

			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet sheet = wb.createSheet();
			wb.setSheetName(0, title);
			HSSFRow titleRow = sheet.createRow(0);
			HSSFRow head = sheet.createRow(1);
			HSSFRow head2 = sheet.createRow(2);
			HSSFRow head3 = sheet.createRow(3);

			// 创建标题
			createTitle(wb, titleRow, title);
			
			createCell(wb, head, 0, "",HSSFColor.GREY_25_PERCENT.index);
			createCell(wb, head, 1, "",HSSFColor.GREY_25_PERCENT.index);
			createCell(wb, head, 2, "主任工作数据",HSSFColor.GREY_25_PERCENT.index);
			createCell(wb, head, 3, "",HSSFColor.GREY_25_PERCENT.index);
			createCell(wb, head, 4, "",HSSFColor.GREY_25_PERCENT.index);
			createCell(wb, head, 5, "",HSSFColor.GREY_25_PERCENT.index);
			createCell(wb, head, 6, "",HSSFColor.GREY_25_PERCENT.index);
			createCell(wb, head, 7, "",HSSFColor.GREY_25_PERCENT.index);
			createCell(wb, head, 8, "",HSSFColor.GREY_25_PERCENT.index);
			createCell(wb, head, 9, "",HSSFColor.GREY_25_PERCENT.index);
			createCell(wb, head, 10, "",HSSFColor.GREY_25_PERCENT.index);
			createCell(wb, head, 11, "",HSSFColor.GREY_25_PERCENT.index);
			createCell(wb, head, 12, "",HSSFColor.GREY_25_PERCENT.index);
			createCell(wb, head, 13, "",HSSFColor.GREY_25_PERCENT.index);
			createCell(wb, head, 14, "学习中心总体数据",HSSFColor.GREY_25_PERCENT.index);
			createCell(wb, head, 15, "",HSSFColor.GREY_25_PERCENT.index);
			createCell(wb, head, 16, "",HSSFColor.GREY_25_PERCENT.index);
			createCell(wb, head, 17, "",HSSFColor.GREY_25_PERCENT.index);
			createCell(wb, head, 18, "",HSSFColor.GREY_25_PERCENT.index);
			createCell(wb, head, 19, "",HSSFColor.GREY_25_PERCENT.index);
			createCell(wb, head, 20, "",HSSFColor.GREY_25_PERCENT.index);
			createCell(wb, head, 21, "",HSSFColor.GREY_25_PERCENT.index);
			createCell(wb, head, 22, "",HSSFColor.GREY_25_PERCENT.index);
			createCell(wb, head, 23, "",HSSFColor.GREY_25_PERCENT.index);
			createCell(wb, head, 24, "",HSSFColor.GREY_25_PERCENT.index);
			createCell(wb, head, 25, "",HSSFColor.GREY_25_PERCENT.index);
			
			createCell(wb, head2, 0, "姓名",HSSFColor.GREY_25_PERCENT.index);
			createCell(wb, head2, 1, "当天主要工作",HSSFColor.GREY_25_PERCENT.index);
			createCell(wb, head2, 2, "当天跟踪学生数量",HSSFColor.GREY_25_PERCENT.index);
			createCell(wb, head2, 3, "新增报名人数",HSSFColor.GREY_25_PERCENT.index);
			createCell(wb, head2, 4, "",HSSFColor.GREY_25_PERCENT.index);
			createCell(wb, head2, 5, "",HSSFColor.GREY_25_PERCENT.index);
			createCell(wb, head2, 6, "",HSSFColor.GREY_25_PERCENT.index);
			createCell(wb, head2, 7, "",HSSFColor.GREY_25_PERCENT.index);
			createCell(wb, head2, 8, "",HSSFColor.GREY_25_PERCENT.index);
			createCell(wb, head2, 9, "",HSSFColor.GREY_25_PERCENT.index);
			createCell(wb, head2, 10, "",HSSFColor.GREY_25_PERCENT.index);
			createCell(wb, head2, 11, "指标情况",HSSFColor.GREY_25_PERCENT.index);
			createCell(wb, head2, 12, "",HSSFColor.GREY_25_PERCENT.index);
			createCell(wb, head2, 13, "",HSSFColor.GREY_25_PERCENT.index);
			createCell(wb, head2, 14, "当天跟踪学生数量",HSSFColor.GREY_25_PERCENT.index);
			createCell(wb, head2, 15, "新增报名人数",HSSFColor.GREY_25_PERCENT.index);
			createCell(wb, head2, 16, "",HSSFColor.GREY_25_PERCENT.index);
			createCell(wb, head2, 17, "",HSSFColor.GREY_25_PERCENT.index);
			createCell(wb, head2, 18, "",HSSFColor.GREY_25_PERCENT.index);
			createCell(wb, head2, 19, "",HSSFColor.GREY_25_PERCENT.index);
			createCell(wb, head2, 20, "",HSSFColor.GREY_25_PERCENT.index);
			createCell(wb, head2, 21, "",HSSFColor.GREY_25_PERCENT.index);
			createCell(wb, head2, 22, "",HSSFColor.GREY_25_PERCENT.index);
			createCell(wb, head2, 23, "指标情况",HSSFColor.GREY_25_PERCENT.index);
			createCell(wb, head2, 24, "",HSSFColor.GREY_25_PERCENT.index);
			createCell(wb, head2, 25, "",HSSFColor.GREY_25_PERCENT.index);
			
			createCell(wb, head3, 0, "",HSSFColor.GREY_25_PERCENT.index);
			createCell(wb, head3, 1, "",HSSFColor.GREY_25_PERCENT.index);
			createCell(wb, head3, 2, "",HSSFColor.GREY_25_PERCENT.index);
			createCell(wb, head3, 3, "社招",HSSFColor.GREY_25_PERCENT.index);
			createCell(wb, head3, 4, "渠道",HSSFColor.GREY_25_PERCENT.index);
			createCell(wb, head3, 5, "大客户",HSSFColor.GREY_25_PERCENT.index);
			createCell(wb, head3, 6, "老带新",HSSFColor.GREY_25_PERCENT.index);
			createCell(wb, head3, 7, "老生续读",HSSFColor.GREY_25_PERCENT.index);
			createCell(wb, head3, 8, "加盟",HSSFColor.GREY_25_PERCENT.index);
			createCell(wb, head3, 9, "共建",HSSFColor.GREY_25_PERCENT.index);
			createCell(wb, head3, 10, "当天招生人数合计",HSSFColor.GREY_25_PERCENT.index);
			createCell(wb, head3, 11, "个人招生指标",HSSFColor.GREY_25_PERCENT.index);
			createCell(wb, head3, 12, "个人累计招生人数",HSSFColor.GREY_25_PERCENT.index);
			createCell(wb, head3, 13, "个人指标完成率",HSSFColor.GREY_25_PERCENT.index);
			createCell(wb, head3, 14, "",HSSFColor.GREY_25_PERCENT.index);
			createCell(wb, head3, 15, "社招",HSSFColor.GREY_25_PERCENT.index);
			createCell(wb, head3, 16, "渠道",HSSFColor.GREY_25_PERCENT.index);
			createCell(wb, head3, 17, "大客户",HSSFColor.GREY_25_PERCENT.index);
			createCell(wb, head3, 18, "老带新",HSSFColor.GREY_25_PERCENT.index);
			createCell(wb, head3, 19, "老生续读",HSSFColor.GREY_25_PERCENT.index);
			createCell(wb, head3, 20, "加盟",HSSFColor.GREY_25_PERCENT.index);
			createCell(wb, head3, 21, "共建",HSSFColor.GREY_25_PERCENT.index);
			createCell(wb, head3, 22, "当天招生人数合计",HSSFColor.GREY_25_PERCENT.index);
			createCell(wb, head3, 23, "中心招生指标",HSSFColor.GREY_25_PERCENT.index);
			createCell(wb, head3, 24, "中心累计招生人数",HSSFColor.GREY_25_PERCENT.index);
			createCell(wb, head3, 25, "中心指标完成率  ",HSSFColor.GREY_25_PERCENT.index);
			
			//标题合并
			sheet.addMergedRegion(getCellRangeAddress(1, 1, 1, 26));
			
			sheet.addMergedRegion(getCellRangeAddress(2, 2, 3, 14));
			sheet.addMergedRegion(getCellRangeAddress(2, 2, 15, 26));
			
			sheet.addMergedRegion(getCellRangeAddress(3, 4, 1, 1));
			sheet.addMergedRegion(getCellRangeAddress(3, 4, 2, 2));
			sheet.addMergedRegion(getCellRangeAddress(3, 4, 3, 3));
			
			sheet.addMergedRegion(getCellRangeAddress(3, 3, 4, 11));
			sheet.addMergedRegion(getCellRangeAddress(3, 3, 12, 14));
			sheet.addMergedRegion(getCellRangeAddress(3, 4, 15, 15));
			sheet.addMergedRegion(getCellRangeAddress(3, 3, 16, 23));
			sheet.addMergedRegion(getCellRangeAddress(3, 3, 24, 26));
			
			//动态数据
			int row =4;
			
			for (int i = 0; i < reportList.size(); i++) {
				//区域经理对象
				Map object=getMap(reportList.get(i));
				HSSFRow bodyRow = sheet.createRow(row++);
				createCell(wb, bodyRow, 0, object.get("name").toString(),HSSFColor.WHITE.index);
				createCell(wb, bodyRow, 1, object.get("dang_tian_zhu_yao_gong_zuo").toString(),HSSFColor.WHITE.index);
				createCell(wb, bodyRow, 2, object.get("dang_tian_gen_zong_xue_sheng_count").toString(),HSSFColor.WHITE.index);
				createCell(wb, bodyRow, 3, object.get("she_zhao_count").toString(),HSSFColor.WHITE.index);
				createCell(wb, bodyRow, 4, object.get("qu_dao_count").toString(),HSSFColor.WHITE.index);
				createCell(wb, bodyRow, 5, object.get("da_ke_hu_count").toString(),HSSFColor.WHITE.index);
				createCell(wb, bodyRow, 6, object.get("lao_dai_xin_count").toString(),HSSFColor.WHITE.index);
				createCell(wb, bodyRow, 7, object.get("lao_sheng_xu_du_count").toString(),HSSFColor.WHITE.index);
				createCell(wb, bodyRow, 8, object.get("jia_meng_count").toString(),HSSFColor.WHITE.index);
				createCell(wb, bodyRow, 9, object.get("gong_jian_count").toString(),HSSFColor.WHITE.index);
				createCell(wb, bodyRow, 10, object.get("sum_count").toString(),HSSFColor.WHITE.index);
				createCell(wb, bodyRow, 11, object.get("zhi_biao").toString(),HSSFColor.WHITE.index);
				createCell(wb, bodyRow, 12, object.get("lei_ji").toString(),HSSFColor.WHITE.index);
				createCell(wb, bodyRow, 13, object.get("zhi_biao_avg").toString(),HSSFColor.WHITE.index,HSSFCellStyle.ALIGN_RIGHT);
				createCell(wb, bodyRow, 14, object.get("branch_dang_tian_gen_zong_xue_sheng_count").toString(),HSSFColor.WHITE.index);
				createCell(wb, bodyRow, 15, object.get("branch_she_zhao_count").toString(),HSSFColor.WHITE.index);
				createCell(wb, bodyRow, 16, object.get("branch_qu_dao_count").toString(),HSSFColor.WHITE.index);
				createCell(wb, bodyRow, 17, object.get("branch_da_ke_hu_count").toString(),HSSFColor.WHITE.index);
				createCell(wb, bodyRow, 18, object.get("branch_lao_dai_xin_count").toString(),HSSFColor.WHITE.index);
				createCell(wb, bodyRow, 19, object.get("branch_lao_sheng_xu_du_count").toString(),HSSFColor.WHITE.index);
				createCell(wb, bodyRow, 20, object.get("branch_jia_meng_count").toString(),HSSFColor.WHITE.index);
				createCell(wb, bodyRow, 21, object.get("branch_gong_jian_count").toString(),HSSFColor.WHITE.index);
				createCell(wb, bodyRow, 22, object.get("branch_sum_count").toString(),HSSFColor.WHITE.index);
				createCell(wb, bodyRow, 23, object.get("branch_zhi_biao").toString(),HSSFColor.WHITE.index);
				createCell(wb, bodyRow, 24, object.get("branch_lei_ji").toString(),HSSFColor.WHITE.index);
				createCell(wb, bodyRow, 25, object.get("branch_zhi_biao_avg").toString(),HSSFColor.WHITE.index,HSSFCellStyle.ALIGN_RIGHT);
				
			}
			sheet.setColumnWidth(0, 4000);// 单位
			sheet.setColumnWidth(1, 4000);// 单位
			
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
		font.setFontHeightInPoints((short) 18	);
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
	private HSSFCellStyle getCellDefaultStyle(HSSFWorkbook wb,short color,short align) {
		// 设置单元格样式
		HSSFCellStyle cellstyle = wb.createCellStyle();
		cellstyle.setAlignment(align);// 设置水平对齐方式
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
	private void createCell(HSSFWorkbook wb, HSSFRow row, int col, String val,short color,short align) {

		HSSFCell cell = row.createCell(col);
		cell.setCellValue(val);
		cell.setCellStyle(getCellDefaultStyle(wb,color,align));

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
