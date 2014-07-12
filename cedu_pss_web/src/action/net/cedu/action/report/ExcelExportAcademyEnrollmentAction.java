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
import net.cedu.student.report.dao.AcademyEnrollmentReport;

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
 * 院校招生统计表
 * @author yangdongdong
 *
 */
public class ExcelExportAcademyEnrollmentAction extends BaseAction {

	@Autowired
	private AcademyEnrollmentReport academyEnrollmentReport;

	private Map<String, Integer> mapParams = new HashMap<String, Integer>();
	
	private Map<String, Date> dateParams = new HashMap<String, Date>();

	@Override
	public String execute() throws Exception {

		Map map=academyEnrollmentReport.statistics(mapParams,dateParams);
		List academyList = (List)map.get("academyList");
		if (academyList != null && academyList.size() != 0) {
			downLoadFile(createExcel(academyList, "院校招生统计表",map), "院校招生统计表"+ ".xls");
			
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
	public HSSFWorkbook createExcel(List academyList, String title,Map resultMap)
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
			
			createCell(wb, head, 0, "学校",HSSFColor.GREY_25_PERCENT.index);
			createCell(wb, head, 1, "学习中心",HSSFColor.GREY_25_PERCENT.index);
			
			createCell(wb, head, 2, "招生指标",HSSFColor.GREY_25_PERCENT.index);
			createCell(wb, head, 3, "",HSSFColor.GREY_25_PERCENT.index);
			createCell(wb, head, 4, "",HSSFColor.GREY_25_PERCENT.index);
			
			
			createCell(wb, head, 5, "录取人数",HSSFColor.GREY_25_PERCENT.index);
			createCell(wb, head, 6, "",HSSFColor.GREY_25_PERCENT.index);
			createCell(wb, head, 7, "",HSSFColor.GREY_25_PERCENT.index);
			
			createCell(wb, head, 8, "缴费人数",HSSFColor.GREY_25_PERCENT.index);
			createCell(wb, head, 9, "",HSSFColor.GREY_25_PERCENT.index);
			createCell(wb, head, 10, "",HSSFColor.GREY_25_PERCENT.index);
			
			createCell(wb, head2, 0, "",HSSFColor.GREY_25_PERCENT.index);
			createCell(wb, head2, 1, "",HSSFColor.GREY_25_PERCENT.index);
			
			createCell(wb, head2, 2, "招生指标",HSSFColor.GREY_25_PERCENT.index);
			createCell(wb, head2, 3, "报名人数",HSSFColor.GREY_25_PERCENT.index);
			createCell(wb, head2, 4, "报名完成率",HSSFColor.GREY_25_PERCENT.index);
			
			
			createCell(wb, head2, 5, "录取人数",HSSFColor.GREY_25_PERCENT.index);
			createCell(wb, head2, 6, "未录取人数",HSSFColor.GREY_25_PERCENT.index);
			createCell(wb, head2, 7, "录取率",HSSFColor.GREY_25_PERCENT.index);
			
			createCell(wb, head2, 8, "缴费人数",HSSFColor.GREY_25_PERCENT.index);
			createCell(wb, head2, 9, "缴费金额",HSSFColor.GREY_25_PERCENT.index);
			createCell(wb, head2, 10, "缴费率",HSSFColor.GREY_25_PERCENT.index);
			
			//标题合并
			sheet.addMergedRegion(getCellRangeAddress(1, 1, 1, 10));
			
			sheet.addMergedRegion(getCellRangeAddress(2, 2, 3, 5));
			sheet.addMergedRegion(getCellRangeAddress(2, 2, 6, 8));
			sheet.addMergedRegion(getCellRangeAddress(2, 2, 9, 11));
			
			sheet.addMergedRegion(getCellRangeAddress(2, 3, 1, 1));
			sheet.addMergedRegion(getCellRangeAddress(2, 3, 2, 2));
			
			int row=3;//行的记录数
			int a=4;
			for (int i = 0; i < academyList.size(); i++) {
				//院校
				Map acadmey = getMap(academyList.get(i));
				//学习中心集合
				List branchList=getList(acadmey.get("academy_branch_list"));
				if(branchList!=null&&branchList.size()!=0){
					for (int j = 0; j < branchList.size(); j++) {
						//学习中心
						Map branch=getMap(branchList.get(j));
						HSSFRow bodyRow = sheet.createRow(row++);
						createCell(wb, bodyRow, 0, acadmey.get("academy_name").toString(),HSSFColor.WHITE.index);
						createCell(wb, bodyRow, 1, branch.get("branch_name").toString(),HSSFColor.WHITE.index);
						
						createCell(wb, bodyRow, 2, branch.get("branch_zhi_biao").toString(),HSSFColor.WHITE.index);
						createCell(wb, bodyRow, 3, branch.get("branch_bao_ming_count").toString(),HSSFColor.WHITE.index);
						createCell(wb, bodyRow, 4, branch.get("branch_bao_ming_count_p").toString(),HSSFColor.WHITE.index);
						
						createCell(wb, bodyRow, 5, branch.get("branch_lu_qu_count").toString(),HSSFColor.WHITE.index);
						createCell(wb, bodyRow, 6, branch.get("branch_no_lu_qu_count").toString(),HSSFColor.WHITE.index);
						createCell(wb, bodyRow, 7, branch.get("branch_lu_qu_count_p").toString(),HSSFColor.WHITE.index);
						
						createCell(wb, bodyRow, 8, branch.get("branch_jiao_fei_count").toString(),HSSFColor.WHITE.index);
						createCell(wb, bodyRow, 9, branch.get("branch_jiao_fei_jin_e").toString(),HSSFColor.WHITE.index);
						createCell(wb, bodyRow, 10, branch.get("branch_jiao_fei_count_p").toString(),HSSFColor.WHITE.index);
						
						
						
						
					}
					
					HSSFRow bodyRow = sheet.createRow(row++);
					createCell(wb, bodyRow, 0, "合计",HSSFColor.SKY_BLUE.index);
					createCell(wb, bodyRow, 1, "",HSSFColor.SKY_BLUE.index);
					
					createCell(wb, bodyRow, 2, acadmey.get("academy_branch_zhi_biao").toString(),HSSFColor.SKY_BLUE.index);
					createCell(wb, bodyRow, 3, acadmey.get("academy_branch_bao_ming_count").toString(),HSSFColor.SKY_BLUE.index);
					createCell(wb, bodyRow, 4, acadmey.get("academy_branch_bao_ming_count_p").toString(),HSSFColor.SKY_BLUE.index);
					
					createCell(wb, bodyRow, 5, acadmey.get("academy_branch_lu_qu_count").toString(),HSSFColor.SKY_BLUE.index);
					createCell(wb, bodyRow, 6, acadmey.get("academy_branch_no_lu_qu_count").toString(),HSSFColor.SKY_BLUE.index);
					createCell(wb, bodyRow, 7, acadmey.get("academy_branch_lu_qu_count_p").toString(),HSSFColor.SKY_BLUE.index);
					
					createCell(wb, bodyRow, 8, acadmey.get("academy_branch_jiao_fei_count").toString(),HSSFColor.SKY_BLUE.index);
					createCell(wb, bodyRow, 9, acadmey.get("academy_branch_jiao_fei_jin_e").toString(),HSSFColor.SKY_BLUE.index);
					createCell(wb, bodyRow, 10, acadmey.get("academy_branch_jiao_fei_count_p").toString(),HSSFColor.SKY_BLUE.index);
					
					//合并单元格
					sheet.addMergedRegion(getCellRangeAddress(row, row, 1, 2));
					
					if(i==0){
						sheet.addMergedRegion(getCellRangeAddress(a, row-1, 1, 1));
						a=row;
					}else{
						sheet.addMergedRegion(getCellRangeAddress(a+1, row-1, 1, 1));
						a=row;
					}
					
				}else{
					//没有授权的学习中心
					HSSFRow bodyRow = sheet.createRow(row++);
					createCell(wb, bodyRow, 0, acadmey.get("academy_name").toString(),HSSFColor.RED.index);
					createCell(wb, bodyRow, 1, "该院校没有授权的学习中心!",HSSFColor.RED.index);
					
					createCell(wb, bodyRow, 2, "",HSSFColor.RED.index);
					createCell(wb, bodyRow, 3, "",HSSFColor.RED.index);
					createCell(wb, bodyRow, 4, "",HSSFColor.RED.index);
					
					createCell(wb, bodyRow, 5, "",HSSFColor.RED.index);
					createCell(wb, bodyRow, 6, "",HSSFColor.RED.index);
					createCell(wb, bodyRow, 7, "",HSSFColor.RED.index);
					
					createCell(wb, bodyRow, 8, "",HSSFColor.RED.index);
					createCell(wb, bodyRow, 9, "",HSSFColor.RED.index);
					createCell(wb, bodyRow, 10, "",HSSFColor.RED.index);
					//合并单元格
					sheet.addMergedRegion(getCellRangeAddress(row, row, 2, 11));

					if(i==0){
						sheet.addMergedRegion(getCellRangeAddress(a, row, 1, 1));
						a=row;
					}else{
						sheet.addMergedRegion(getCellRangeAddress(a+1, row, 1, 1));
						a=row;
					}
				}
			}
			
			//没有授权的学习中心
			HSSFRow bodyRow = sheet.createRow(row++);
			createCell(wb, bodyRow, 0, "总合计",HSSFColor.YELLOW.index);
			createCell(wb, bodyRow, 1, "",HSSFColor.YELLOW.index);
			
			createCell(wb, bodyRow, 2, resultMap.get("sum_academy_branch_zhi_biao").toString(),HSSFColor.YELLOW.index);
			createCell(wb, bodyRow, 3, resultMap.get("sum_academy_branch_bao_ming_count").toString(),HSSFColor.YELLOW.index);
			createCell(wb, bodyRow, 4, resultMap.get("sum_academy_branch_bao_ming_count_p").toString(),HSSFColor.YELLOW.index);
			
			createCell(wb, bodyRow, 5, resultMap.get("sum_academy_branch_lu_qu_count").toString(),HSSFColor.YELLOW.index);
			createCell(wb, bodyRow, 6, resultMap.get("sum_academy_branch_no_lu_qu_count").toString(),HSSFColor.YELLOW.index);
			createCell(wb, bodyRow, 7, resultMap.get("sum_academy_branch_lu_qu_count_p").toString(),HSSFColor.YELLOW.index);
			
			createCell(wb, bodyRow, 8, resultMap.get("sum_academy_branch_jiao_fei_count").toString(),HSSFColor.YELLOW.index);
			createCell(wb, bodyRow, 9, resultMap.get("sum_academy_branch_jiao_fei_jin_e").toString(),HSSFColor.YELLOW.index);
			createCell(wb, bodyRow, 10, resultMap.get("sum_academy_branch_jiao_fei_count_p").toString(),HSSFColor.YELLOW.index);
			
			sheet.addMergedRegion(getCellRangeAddress(row, row, 1,2));
			
			
			// 设置列宽
			sheet.setColumnWidth(0, 5000);// 单位
			sheet.setColumnWidth(1, 5000);// 单位
			sheet.setColumnWidth(2, 2500);// 单位
			sheet.setColumnWidth(3, 2500);// 单位
			sheet.setColumnWidth(4, 5000);// 单位
			sheet.setColumnWidth(5, 2500);// 单位
			sheet.setColumnWidth(6, 2500);// 单位
			sheet.setColumnWidth(7, 5000);// 单位
			sheet.setColumnWidth(8, 2500);// 单位
			sheet.setColumnWidth(9, 5000);// 单位
			sheet.setColumnWidth(10, 5000);// 单位
			
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
