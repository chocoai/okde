/**
 * 文件名：ExcelExportStudentEnrollmentMonitorAction.java
 * 包名：net.cedu.action.report
 * 工程：cedu_pss_web
 * 功能： TODO /请自行添加
 *
 * 作者：dongminghao    
 * 日期：2012-06-27 下午18:26:18
 *
*/
package net.cedu.action.report;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.cedu.action.BaseAction;
import net.cedu.entity.academy.Academy;
import net.cedu.student.report.dao.EnrollmentMonitorReport;
import net.cedu.student.report.dao.TuitionStandardReport;

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
 * 学费标准汇总
 * @author dongminghao
 *
 */
public class ExcelExportTuitionStandardAction extends BaseAction {

	@Autowired
	private TuitionStandardReport tuitionStandardReport;

	private Map<String, Integer> mapParams = new HashMap<String, Integer>();
	
	private Map<String, Date> dateParams = new HashMap<String, Date>();

	@Override
	public String execute() throws Exception {

		Map map=tuitionStandardReport.statistics(mapParams);
		List academyList = (List)map.get("academyList");
		List branchList = (List)map.get("branchList");
		if (branchList != null && branchList.size() != 0) {
			if(academyList != null && academyList.size() != 0) {
				downLoadFile(createExcel(academyList,branchList, "学费标准汇总",map), "学费标准汇总"+ ".xls");
				return null;
			}
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
	public HSSFWorkbook createExcel(List academyList,List branchList, String title,Map resultMap)
			throws Exception {
		try {

			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet sheet = wb.createSheet();
			wb.setSheetName(0, title);
			HSSFRow titleRow = sheet.createRow(0);
			HSSFRow head = sheet.createRow(1);

			// 创建标题
			createTitle(wb, titleRow, title);
			
			// 院校列
			createCell(wb, head, 0, "城市",HSSFColor.GREY_25_PERCENT.index);
			createCell(wb, head, 1, "学习中心/院校",HSSFColor.GREY_25_PERCENT.index);
			for(int i=0;i<academyList.size();i++){
				Academy academy = (Academy)academyList.get(i);
				createCell(wb, head, i+2, academy.getName(),HSSFColor.GREY_25_PERCENT.index);
			}
			
			//标题合并
			sheet.addMergedRegion(getCellRangeAddress(1, 1, 1, academyList.size()+2));
			
			int row=2;//行的记录数
			for (int i = 0; i < branchList.size(); i++) {
				//中心
				Map branch = getMap(branchList.get(i));
				HSSFRow bodyRow = sheet.createRow(row++);
				createCell(wb, bodyRow, 0, branch.get("branch_city").toString(),HSSFColor.WHITE.index);
				createCell(wb, bodyRow, 1, branch.get("branch_name").toString(),HSSFColor.WHITE.index);
				List<String> tuitionStandardList = (List<String>)branch.get("tuitionStandard");
				if(tuitionStandardList!=null && tuitionStandardList.size()!=0){
					for(int j = 0;j<tuitionStandardList.size();j++){
						createCell(wb, bodyRow, j+2, tuitionStandardList.get(j),HSSFColor.WHITE.index);
					}
				}
			}
			
			// 设置列宽
//			sheet.setColumnWidth(0, 5000);// 单位
			
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
