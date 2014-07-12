/**
 * 文件名：ExcelExportManagerWorkScoreAction.java
 * 包名：net.cedu.action.report
 * 工程：cedu_pss_web
 * 功能： TODO /请自行添加
 *
 * 作者：dongminghao
 * 日期：2012-02-11 上午09:33:30
 *
 */
package net.cedu.action.report;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.cedu.action.BaseAction;
import net.cedu.student.report.dao.ManagerWorkScoreReport;

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

public class ExcelExportManagerWorkScoreAction extends BaseAction {

	@Autowired
	private ManagerWorkScoreReport managerWorkScoreReport;

	private Map<String, Integer> mapParams = new HashMap<String, Integer>();
	
	private Map<String, Date> dateParams = new HashMap<String, Date>();

	@Override
	public String execute() throws Exception {

		List reportList = managerWorkScoreReport.statistics(mapParams,dateParams);

		if (reportList != null && reportList.size() != 0) {
			downLoadFile(createExcel(reportList, "工作评分统计报表"), "工作评分统计报表"+ ".xls");
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
			
			// 创建标题
			createTitle(wb, titleRow, title);
			createCell(wb, head, 0, "区域经理",HSSFColor.GREY_25_PERCENT.index);
			createCell(wb, head, 1, "学习中心",HSSFColor.GREY_25_PERCENT.index);
			createCell(wb, head, 2, "服务站",HSSFColor.GREY_25_PERCENT.index);
			createCell(wb, head, 3, "人员",HSSFColor.GREY_25_PERCENT.index);
			createCell(wb, head, 4, "月平均分",HSSFColor.GREY_25_PERCENT.index);
			createCell(wb, head, 5, "",HSSFColor.GREY_25_PERCENT.index);
			createCell(wb, head, 6, "",HSSFColor.GREY_25_PERCENT.index);
			createCell(wb, head, 7, "",HSSFColor.GREY_25_PERCENT.index);
			createCell(wb, head, 8, "",HSSFColor.GREY_25_PERCENT.index);
			createCell(wb, head, 9, "",HSSFColor.GREY_25_PERCENT.index);
			createCell(wb, head, 10, "",HSSFColor.GREY_25_PERCENT.index);
			createCell(wb, head, 11, "",HSSFColor.GREY_25_PERCENT.index);
			createCell(wb, head, 12, "",HSSFColor.GREY_25_PERCENT.index);
			createCell(wb, head, 13, "",HSSFColor.GREY_25_PERCENT.index);
			createCell(wb, head, 14, "",HSSFColor.GREY_25_PERCENT.index);
			createCell(wb, head, 15, "",HSSFColor.GREY_25_PERCENT.index);
			createCell(wb, head, 16, "总平均分",HSSFColor.GREY_25_PERCENT.index);
			
			//标题
			sheet.addMergedRegion(getCellRangeAddress(1, 1, 1, 17));
			//表头
			sheet.addMergedRegion(getCellRangeAddress(2, 3, 1, 1));
			sheet.addMergedRegion(getCellRangeAddress(2, 3, 2, 2));
			sheet.addMergedRegion(getCellRangeAddress(2, 3, 3, 3));
			sheet.addMergedRegion(getCellRangeAddress(2, 3, 4, 4));

			sheet.addMergedRegion(getCellRangeAddress(2, 2, 5, 16));
			
			sheet.addMergedRegion(getCellRangeAddress(2, 3, 17, 17));
			
			//表头第二行
			createCell(wb, head2, 0, "",HSSFColor.GREY_25_PERCENT.index);
			createCell(wb, head2, 1, "",HSSFColor.GREY_25_PERCENT.index);
			createCell(wb, head2, 2, "",HSSFColor.GREY_25_PERCENT.index);
			createCell(wb, head2, 3, "",HSSFColor.GREY_25_PERCENT.index);
			
			createCell(wb, head2, 4, "1月",HSSFColor.GREY_25_PERCENT.index);
			createCell(wb, head2, 5, "2月",HSSFColor.GREY_25_PERCENT.index);
			createCell(wb, head2, 6, "3月",HSSFColor.GREY_25_PERCENT.index);
			createCell(wb, head2, 7, "4月",HSSFColor.GREY_25_PERCENT.index);
			createCell(wb, head2, 8, "5月",HSSFColor.GREY_25_PERCENT.index);
			createCell(wb, head2, 9, "6月",HSSFColor.GREY_25_PERCENT.index);
			createCell(wb, head2, 10, "7月",HSSFColor.GREY_25_PERCENT.index);
			createCell(wb, head2, 11, "8月",HSSFColor.GREY_25_PERCENT.index);
			createCell(wb, head2, 12, "9月",HSSFColor.GREY_25_PERCENT.index);
			createCell(wb, head2, 13, "10月",HSSFColor.GREY_25_PERCENT.index);
			createCell(wb, head2, 14, "11月",HSSFColor.GREY_25_PERCENT.index);
			createCell(wb, head2, 15, "12月",HSSFColor.GREY_25_PERCENT.index);
			
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
							createCell(wb, bodyRow, 4, userObject.get("userScoreYi").toString(),HSSFColor.WHITE.index);
							createCell(wb, bodyRow, 5, userObject.get("userScoreEr").toString(),HSSFColor.WHITE.index);
							createCell(wb, bodyRow, 6, userObject.get("userScoreSan").toString(),HSSFColor.WHITE.index);
							createCell(wb, bodyRow, 7, userObject.get("userScoreSi").toString(),HSSFColor.WHITE.index);
							createCell(wb, bodyRow, 8, userObject.get("userScoreWu").toString(),HSSFColor.WHITE.index);
							createCell(wb, bodyRow, 9, userObject.get("userScoreLiu").toString(),HSSFColor.WHITE.index);
							createCell(wb, bodyRow, 10, userObject.get("userScoreQi").toString(),HSSFColor.WHITE.index);
							createCell(wb, bodyRow, 11, userObject.get("userScoreBa").toString(),HSSFColor.WHITE.index);
							createCell(wb, bodyRow, 12, userObject.get("userScoreJiu").toString(),HSSFColor.WHITE.index);
							createCell(wb, bodyRow, 13, userObject.get("userScoreShi").toString(),HSSFColor.WHITE.index);
							createCell(wb, bodyRow, 14, userObject.get("userScoreSY").toString(),HSSFColor.WHITE.index);
							createCell(wb, bodyRow, 15, userObject.get("userScoreSE").toString(),HSSFColor.WHITE.index);
							createCell(wb, bodyRow, 16, userObject.get("userAvg").toString(),HSSFColor.YELLOW.index);
						}
						HSSFRow bodyRow = sheet.createRow(row++);
						Map fuwuzhanHeJiMap=getMap(fuwuzhanObject.get("fuwuzhanHeJiMap"));
						createCell(wb, bodyRow, 0, quyuObject.get("quyuName").toString(),HSSFColor.GREY_25_PERCENT.index);
						createCell(wb, bodyRow, 1, xuexizhongxinObject.get("xuexiName").toString(),HSSFColor.GREY_25_PERCENT.index);
						createCell(wb, bodyRow, 2, fuwuzhanObject.get("fuwuName").toString(),HSSFColor.GREY_25_PERCENT.index);
						createCell(wb, bodyRow, 3, "小计",HSSFColor.GREY_25_PERCENT.index);
						createCell(wb, bodyRow, 4, fuwuzhanHeJiMap.get("userScoreYiSum").toString(),HSSFColor.GREY_25_PERCENT.index);
						createCell(wb, bodyRow, 5, fuwuzhanHeJiMap.get("userScoreErSum").toString(),HSSFColor.GREY_25_PERCENT.index);
						createCell(wb, bodyRow, 6, fuwuzhanHeJiMap.get("userScoreSanSum").toString(),HSSFColor.GREY_25_PERCENT.index);
						createCell(wb, bodyRow, 7, fuwuzhanHeJiMap.get("userScoreSiSum").toString(),HSSFColor.GREY_25_PERCENT.index);
						createCell(wb, bodyRow, 8, fuwuzhanHeJiMap.get("userScoreWuSum").toString(),HSSFColor.GREY_25_PERCENT.index);
						createCell(wb, bodyRow, 9, fuwuzhanHeJiMap.get("userScoreLiuSum").toString(),HSSFColor.GREY_25_PERCENT.index);
						createCell(wb, bodyRow, 10, fuwuzhanHeJiMap.get("userScoreQiSum").toString(),HSSFColor.GREY_25_PERCENT.index);
						createCell(wb, bodyRow, 11, fuwuzhanHeJiMap.get("userScoreBaSum").toString(),HSSFColor.GREY_25_PERCENT.index);
						createCell(wb, bodyRow, 12, fuwuzhanHeJiMap.get("userScoreJiuSum").toString(),HSSFColor.GREY_25_PERCENT.index);
						createCell(wb, bodyRow, 13, fuwuzhanHeJiMap.get("userScoreShiSum").toString(),HSSFColor.GREY_25_PERCENT.index);
						createCell(wb, bodyRow, 14, fuwuzhanHeJiMap.get("userScoreSYSum").toString(),HSSFColor.GREY_25_PERCENT.index);
						createCell(wb, bodyRow, 15, fuwuzhanHeJiMap.get("userScoreSESum").toString(),HSSFColor.GREY_25_PERCENT.index);
						createCell(wb, bodyRow, 16, fuwuzhanHeJiMap.get("hejiScoreAvg").toString(),HSSFColor.GREY_25_PERCENT.index);
						
						if(k==0){
							sheet.addMergedRegion(getCellRangeAddress(f, row, 3, 3));
							f=row;
						}else{
							
							sheet.addMergedRegion(getCellRangeAddress(f+1, row, 3, 3));
							f=row;
						}
					}
					HSSFRow bodyRow = sheet.createRow(row++);
					Map fuwuzhanHeJiMap=getMap(xuexizhongxinObject.get("fuwuzhanHeJiMap"));
					createCell(wb, bodyRow, 0, quyuObject.get("quyuName").toString(),HSSFColor.SKY_BLUE.index);
					createCell(wb, bodyRow, 1, "合计",HSSFColor.SKY_BLUE.index);
					createCell(wb, bodyRow, 2, "",HSSFColor.SKY_BLUE.index);
					createCell(wb, bodyRow, 3, "",HSSFColor.SKY_BLUE.index);
					createCell(wb, bodyRow, 4, fuwuzhanHeJiMap.get("x_userScoreYiSum").toString(),HSSFColor.SKY_BLUE.index);
					createCell(wb, bodyRow, 5, fuwuzhanHeJiMap.get("x_userScoreErSum").toString(),HSSFColor.SKY_BLUE.index);
					createCell(wb, bodyRow, 6, fuwuzhanHeJiMap.get("x_userScoreSanSum").toString(),HSSFColor.SKY_BLUE.index);
					createCell(wb, bodyRow, 7, fuwuzhanHeJiMap.get("x_userScoreSiSum").toString(),HSSFColor.SKY_BLUE.index);
					createCell(wb, bodyRow, 8, fuwuzhanHeJiMap.get("x_userScoreWuSum").toString(),HSSFColor.SKY_BLUE.index);
					createCell(wb, bodyRow, 9, fuwuzhanHeJiMap.get("x_userScoreLiuSum").toString(),HSSFColor.SKY_BLUE.index);
					createCell(wb, bodyRow, 10, fuwuzhanHeJiMap.get("x_userScoreQiSum").toString(),HSSFColor.SKY_BLUE.index);
					createCell(wb, bodyRow, 11, fuwuzhanHeJiMap.get("x_userScoreBaSum").toString(),HSSFColor.SKY_BLUE.index);
					createCell(wb, bodyRow, 12, fuwuzhanHeJiMap.get("x_userScoreJiuSum").toString(),HSSFColor.SKY_BLUE.index);
					createCell(wb, bodyRow, 13, fuwuzhanHeJiMap.get("x_userScoreShiSum").toString(),HSSFColor.SKY_BLUE.index);
					createCell(wb, bodyRow, 14, fuwuzhanHeJiMap.get("x_userScoreSYSum").toString(),HSSFColor.SKY_BLUE.index);
					createCell(wb, bodyRow, 15, fuwuzhanHeJiMap.get("x_userScoreSESum").toString(),HSSFColor.SKY_BLUE.index);
					createCell(wb, bodyRow, 16, fuwuzhanHeJiMap.get("x_hejiScoreAvg").toString(),HSSFColor.SKY_BLUE.index);
					//合并单元格
					sheet.addMergedRegion(getCellRangeAddress(row, row, 2, 4));
					
					if(j==0){
						//System.out.println((x)+","+(row-1));
						sheet.addMergedRegion(getCellRangeAddress(x, row-1, 2, 2));
						x=row;
					}else{
						//System.out.println((x+1)+","+(row-1));
						sheet.addMergedRegion(getCellRangeAddress(x+1, row-1, 2, 2));
						x=row;
					}
					f++;
					f++;
				}
				HSSFRow bodyRow = sheet.createRow(row++);
				Map fuwuzhanHeJiMap=getMap(quyuObject.get("fuwuzhanHeJiMap"));
				createCell(wb, bodyRow, 0, "总合计",HSSFColor.YELLOW.index);
				createCell(wb, bodyRow, 1, "",HSSFColor.YELLOW.index);
				createCell(wb, bodyRow, 2, "",HSSFColor.YELLOW.index);
				createCell(wb, bodyRow, 3, "",HSSFColor.YELLOW.index);
				createCell(wb, bodyRow, 4, fuwuzhanHeJiMap.get("z_x_userScoreYiSum").toString(),HSSFColor.YELLOW.index);
				createCell(wb, bodyRow, 5, fuwuzhanHeJiMap.get("z_x_userScoreErSum").toString(),HSSFColor.YELLOW.index);
				createCell(wb, bodyRow, 6, fuwuzhanHeJiMap.get("z_x_userScoreSanSum").toString(),HSSFColor.YELLOW.index);
				createCell(wb, bodyRow, 7, fuwuzhanHeJiMap.get("z_x_userScoreSiSum").toString(),HSSFColor.YELLOW.index);
				createCell(wb, bodyRow, 8, fuwuzhanHeJiMap.get("z_x_userScoreWuSum").toString(),HSSFColor.YELLOW.index);
				createCell(wb, bodyRow, 9, fuwuzhanHeJiMap.get("z_x_userScoreLiuSum").toString(),HSSFColor.YELLOW.index);
				createCell(wb, bodyRow, 10, fuwuzhanHeJiMap.get("z_x_userScoreQiSum").toString(),HSSFColor.YELLOW.index);
				createCell(wb, bodyRow, 11, fuwuzhanHeJiMap.get("z_x_userScoreBaSum").toString(),HSSFColor.YELLOW.index);
				createCell(wb, bodyRow, 12, fuwuzhanHeJiMap.get("z_x_userScoreJiuSum").toString(),HSSFColor.YELLOW.index);
				createCell(wb, bodyRow, 13, fuwuzhanHeJiMap.get("z_x_userScoreShiSum").toString(),HSSFColor.YELLOW.index);
				createCell(wb, bodyRow, 14, fuwuzhanHeJiMap.get("z_x_userScoreSYSum").toString(),HSSFColor.YELLOW.index);
				createCell(wb, bodyRow, 15, fuwuzhanHeJiMap.get("z_x_userScoreSESum").toString(),HSSFColor.YELLOW.index);
				createCell(wb, bodyRow, 16, fuwuzhanHeJiMap.get("z_x_hejiScoreAvg").toString(),HSSFColor.YELLOW.index);
				//合并单元格
				sheet.addMergedRegion(getCellRangeAddress(row, row, 1, 4));
				
				if(i==0){
					sheet.addMergedRegion(getCellRangeAddress(q, row-1, 1, 1));
					q=row;
				}else{
					sheet.addMergedRegion(getCellRangeAddress(q+1, row-1, 1, 1));
					q=row;
				}
				x++;
				x++;
				f++;
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
