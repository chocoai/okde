/**
 * 文件名：StudentDataSourceAction.java
 * 包名：net.cedu.action.report
 * 工程：cedu_pss_web
 * 功能： TODO /请自行添加
 *
 * 作者：yangdongdong    
 * 日期：2011-12-26 下午04:06:56
 *
 */
package net.cedu.action.report;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.cedu.action.BaseAction;
import net.cedu.student.report.dao.DataSourceReport;

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

public class ExcelExportStudentDataSourceAction extends BaseAction {

	@Autowired
	private DataSourceReport dataSourceReport;

	private Map<String, Integer> mapParams = new HashMap<String, Integer>();

	@Override
	public String execute() throws Exception {

		Map map=dataSourceReport.statistics(mapParams);
		List reportList = (List)map.get("quyuList");
		if (reportList != null && reportList.size() != 0) {
			downLoadFile(createExcel(reportList, "学生数据来源统计报表",map), "学生数据来源统计报表"+ ".xls");
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
			HSSFRow head2 = sheet.createRow(2);

			// 创建标题
			createTitle(wb, titleRow, title);
			createCell(wb, head, 0, "区域经理",HSSFColor.GREY_25_PERCENT.index);
			createCell(wb, head, 1, "学习中心",HSSFColor.GREY_25_PERCENT.index);
			createCell(wb, head, 2, "服务站",HSSFColor.GREY_25_PERCENT.index);
			createCell(wb, head, 3, "人员",HSSFColor.GREY_25_PERCENT.index);
			createCell(wb, head, 4, "招生指标",HSSFColor.GREY_25_PERCENT.index);
			createCell(wb, head, 5, "网络报名",HSSFColor.GREY_25_PERCENT.index);
			createCell(wb, head, 6, "",HSSFColor.GREY_25_PERCENT.index);
			createCell(wb, head, 7, "呼叫中心",HSSFColor.GREY_25_PERCENT.index);
			createCell(wb, head, 8, "",HSSFColor.GREY_25_PERCENT.index);
			createCell(wb, head, 9, "学习中心",HSSFColor.GREY_25_PERCENT.index);
			createCell(wb, head, 10, "",HSSFColor.GREY_25_PERCENT.index);
			createCell(wb, head, 11, "历史数据",HSSFColor.GREY_25_PERCENT.index);
			createCell(wb, head, 12, "",HSSFColor.GREY_25_PERCENT.index);
			createCell(wb, head, 13, "合计",HSSFColor.GREY_25_PERCENT.index);
			createCell(wb, head, 14, "",HSSFColor.GREY_25_PERCENT.index);

			sheet.addMergedRegion(getCellRangeAddress(1, 1, 1, 15));

			sheet.addMergedRegion(getCellRangeAddress(2, 3, 1, 1));
			sheet.addMergedRegion(getCellRangeAddress(2, 3, 2, 2));
			sheet.addMergedRegion(getCellRangeAddress(2, 3, 3, 3));
			sheet.addMergedRegion(getCellRangeAddress(2, 3, 4, 4));
			sheet.addMergedRegion(getCellRangeAddress(2, 3, 5, 5));

			sheet.addMergedRegion(getCellRangeAddress(2, 2, 6, 7));
			sheet.addMergedRegion(getCellRangeAddress(2, 2, 8, 9));
			sheet.addMergedRegion(getCellRangeAddress(2, 2, 10, 11));
			sheet.addMergedRegion(getCellRangeAddress(2, 2, 12, 13));
			sheet.addMergedRegion(getCellRangeAddress(2, 2, 14, 15));

			// 表头第二行

			createCell(wb, head2, 0, "",HSSFColor.GREY_25_PERCENT.index);
			createCell(wb, head2, 1, "",HSSFColor.GREY_25_PERCENT.index);
			
			createCell(wb, head2, 2, "",HSSFColor.GREY_25_PERCENT.index);
			createCell(wb, head2, 3, "",HSSFColor.GREY_25_PERCENT.index);
			createCell(wb, head2, 4, "",HSSFColor.GREY_25_PERCENT.index);
			
			createCell(wb, head2, 5, "人数",HSSFColor.GREY_25_PERCENT.index);
			createCell(wb, head2, 6, "比例",HSSFColor.GREY_25_PERCENT.index);

			createCell(wb, head2, 7, "人数",HSSFColor.GREY_25_PERCENT.index);
			createCell(wb, head2, 8, "比例",HSSFColor.GREY_25_PERCENT.index);

			createCell(wb, head2, 9, "人数",HSSFColor.GREY_25_PERCENT.index);
			createCell(wb, head2, 10, "比例",HSSFColor.GREY_25_PERCENT.index);

			createCell(wb, head2, 11, "人数",HSSFColor.GREY_25_PERCENT.index);
			createCell(wb, head2, 12, "比例",HSSFColor.GREY_25_PERCENT.index);

			createCell(wb, head2, 13, "人数",HSSFColor.GREY_25_PERCENT.index);
			createCell(wb, head2, 14, "比例",HSSFColor.GREY_25_PERCENT.index);
			
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
							createCell(wb, bodyRow, 4, userObject.get("userZhaoShengZhiBiao").toString(),HSSFColor.WHITE.index);
							createCell(wb, bodyRow, 5, userObject.get("wangluobaomingCount").toString(),HSSFColor.WHITE.index);
							createCell(wb, bodyRow, 6, userObject.get("wangluobaomingCountP").toString(),HSSFColor.WHITE.index);
							createCell(wb, bodyRow, 7, userObject.get("hujiaozhongxinCount").toString(),HSSFColor.WHITE.index);
							createCell(wb, bodyRow, 8, userObject.get("hujiaozhongxinCountP").toString(),HSSFColor.WHITE.index);
							createCell(wb, bodyRow, 9, userObject.get("xuexizhongxinCount").toString(),HSSFColor.WHITE.index);
							createCell(wb, bodyRow, 10, userObject.get("xuexizhongxinCountP").toString(),HSSFColor.WHITE.index);
							createCell(wb, bodyRow, 11, userObject.get("lishishujuCount").toString(),HSSFColor.WHITE.index);
							createCell(wb, bodyRow, 12, userObject.get("lishishujuCountP").toString(),HSSFColor.WHITE.index);
							createCell(wb, bodyRow, 13, userObject.get("hejiCount").toString(),HSSFColor.YELLOW.index);
							createCell(wb, bodyRow, 14, userObject.get("hejiCountP").toString(),HSSFColor.YELLOW.index);
						}
						HSSFRow bodyRow = sheet.createRow(row++);
						Map fuwuzhanHeJiMap=getMap(fuwuzhanObject.get("fuwuzhanHeJiMap"));
						createCell(wb, bodyRow, 0, quyuObject.get("quyuName").toString(),HSSFColor.GREY_25_PERCENT.index);
						createCell(wb, bodyRow, 1, xuexizhongxinObject.get("xuexiName").toString(),HSSFColor.GREY_25_PERCENT.index);
						createCell(wb, bodyRow, 2, fuwuzhanObject.get("fuwuName").toString(),HSSFColor.GREY_25_PERCENT.index);
						createCell(wb, bodyRow, 3, "小计",HSSFColor.GREY_25_PERCENT.index);
						createCell(wb, bodyRow, 4, fuwuzhanHeJiMap.get("userZhaoShengZhiBiaoSum").toString(),HSSFColor.GREY_25_PERCENT.index);
						createCell(wb, bodyRow, 5, fuwuzhanHeJiMap.get("wangluobaomingCountSum").toString(),HSSFColor.GREY_25_PERCENT.index);
						createCell(wb, bodyRow, 6, fuwuzhanHeJiMap.get("wangluobaomingCountPSum").toString(),HSSFColor.GREY_25_PERCENT.index);
						createCell(wb, bodyRow, 7, fuwuzhanHeJiMap.get("hujiaozhongxingCountSum").toString(),HSSFColor.GREY_25_PERCENT.index);
						createCell(wb, bodyRow, 8, fuwuzhanHeJiMap.get("hujiaozhongxingCountPSum").toString(),HSSFColor.GREY_25_PERCENT.index);
						createCell(wb, bodyRow, 9, fuwuzhanHeJiMap.get("xuexizhongxinCountSum").toString(),HSSFColor.GREY_25_PERCENT.index);
						createCell(wb, bodyRow, 10, fuwuzhanHeJiMap.get("xuexizhongxinCountPSum").toString(),HSSFColor.GREY_25_PERCENT.index);
						createCell(wb, bodyRow, 11, fuwuzhanHeJiMap.get("lishishujuCountSum").toString(),HSSFColor.GREY_25_PERCENT.index);
						createCell(wb, bodyRow, 12, fuwuzhanHeJiMap.get("lishishujuCountPSum").toString(),HSSFColor.GREY_25_PERCENT.index);
						createCell(wb, bodyRow, 13, fuwuzhanHeJiMap.get("hejiCountSum").toString(),HSSFColor.GREY_25_PERCENT.index);
						createCell(wb, bodyRow, 14, fuwuzhanHeJiMap.get("hejiCountPSum").toString(),HSSFColor.GREY_25_PERCENT.index);
						
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
					createCell(wb, bodyRow, 4, fuwuzhanHeJiMap.get("x_userZhaoShengZhiBiaoSum").toString(),HSSFColor.SKY_BLUE.index);
					createCell(wb, bodyRow, 5, fuwuzhanHeJiMap.get("x_wangluobaomingCountSum").toString(),HSSFColor.SKY_BLUE.index);
					createCell(wb, bodyRow, 6, fuwuzhanHeJiMap.get("x_wangluobaomingCountPSum").toString(),HSSFColor.SKY_BLUE.index);
					createCell(wb, bodyRow, 7, fuwuzhanHeJiMap.get("x_hujiaozhongxingCountSum").toString(),HSSFColor.SKY_BLUE.index);
					createCell(wb, bodyRow, 8, fuwuzhanHeJiMap.get("x_hujiaozhongxingCountPSum").toString(),HSSFColor.SKY_BLUE.index);
					createCell(wb, bodyRow, 9, fuwuzhanHeJiMap.get("x_xuexizhongxinCountSum").toString(),HSSFColor.SKY_BLUE.index);
					createCell(wb, bodyRow, 10, fuwuzhanHeJiMap.get("x_xuexizhongxinCountPSum").toString(),HSSFColor.SKY_BLUE.index);
					createCell(wb, bodyRow, 11, fuwuzhanHeJiMap.get("x_lishishujuCountSum").toString(),HSSFColor.SKY_BLUE.index);
					createCell(wb, bodyRow, 12, fuwuzhanHeJiMap.get("x_lishishujuCountPSum").toString(),HSSFColor.SKY_BLUE.index);
					createCell(wb, bodyRow, 13, fuwuzhanHeJiMap.get("x_hejiCountSum").toString(),HSSFColor.SKY_BLUE.index);
					createCell(wb, bodyRow, 14, fuwuzhanHeJiMap.get("x_hejiCountPSum").toString(),HSSFColor.SKY_BLUE.index);
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
				createCell(wb, bodyRow, 0, "合计",HSSFColor.CORAL.index);
				createCell(wb, bodyRow, 1, "",HSSFColor.CORAL.index);
				createCell(wb, bodyRow, 2, "",HSSFColor.CORAL.index);
				createCell(wb, bodyRow, 3, "",HSSFColor.CORAL.index);
				createCell(wb, bodyRow, 4, fuwuzhanHeJiMap.get("z_x_userZhaoShengZhiBiaoSum").toString(),HSSFColor.CORAL.index);
				createCell(wb, bodyRow, 5, fuwuzhanHeJiMap.get("z_x_wangluobaomingCountSum").toString(),HSSFColor.CORAL.index);
				createCell(wb, bodyRow, 6, fuwuzhanHeJiMap.get("z_x_wangluobaomingCountPSum").toString(),HSSFColor.CORAL.index);
				createCell(wb, bodyRow, 7, fuwuzhanHeJiMap.get("z_x_hujiaozhongxingCountSum").toString(),HSSFColor.CORAL.index);
				createCell(wb, bodyRow, 8, fuwuzhanHeJiMap.get("z_x_hujiaozhongxingCountPSum").toString(),HSSFColor.CORAL.index);
				createCell(wb, bodyRow, 9, fuwuzhanHeJiMap.get("z_x_xuexizhongxinCountSum").toString(),HSSFColor.CORAL.index);
				createCell(wb, bodyRow, 10, fuwuzhanHeJiMap.get("z_x_xuexizhongxinCountPSum").toString(),HSSFColor.CORAL.index);
				createCell(wb, bodyRow, 11, fuwuzhanHeJiMap.get("z_x_lishishujuCountSum").toString(),HSSFColor.CORAL.index);
				createCell(wb, bodyRow, 12, fuwuzhanHeJiMap.get("z_x_lishishujuCountPSum").toString(),HSSFColor.CORAL.index);
				createCell(wb, bodyRow, 13, fuwuzhanHeJiMap.get("z_x_hejiCountSum").toString(),HSSFColor.CORAL.index);
				createCell(wb, bodyRow, 14, fuwuzhanHeJiMap.get("z_x_hejiCountSumP").toString(),HSSFColor.CORAL.index);
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
			
			HSSFRow bodyRow = sheet.createRow(row++);
			createCell(wb, bodyRow, 0, "总合计",HSSFColor.CORAL.index);
			createCell(wb, bodyRow, 1, "",HSSFColor.CORAL.index);
			createCell(wb, bodyRow, 2, "",HSSFColor.CORAL.index);
			createCell(wb, bodyRow, 3, "",HSSFColor.CORAL.index);
			createCell(wb, bodyRow, 4, result.get("zhubiaoSum").toString(),HSSFColor.CORAL.index);
			createCell(wb, bodyRow, 5, result.get("wangluobaomingSum").toString(),HSSFColor.CORAL.index);
			createCell(wb, bodyRow, 6, result.get("wangluobaomingSumP").toString(),HSSFColor.CORAL.index);
			createCell(wb, bodyRow, 7, result.get("hujiaozhongxinSum").toString(),HSSFColor.CORAL.index);
			createCell(wb, bodyRow, 8, result.get("hujiaozhongxinSumP").toString(),HSSFColor.CORAL.index);
			createCell(wb, bodyRow, 9, result.get("xuexizhongxinSum").toString(),HSSFColor.CORAL.index);
			createCell(wb, bodyRow, 10, result.get("xuexizhongxinSumP").toString(),HSSFColor.CORAL.index);
			createCell(wb, bodyRow, 11,result.get("lishishujuSum").toString(),HSSFColor.CORAL.index);
			createCell(wb, bodyRow, 12,result.get("lishishujuSumP").toString(),HSSFColor.CORAL.index);
			createCell(wb, bodyRow, 13, result.get("heji").toString(),HSSFColor.CORAL.index);
			createCell(wb, bodyRow, 14,"",HSSFColor.CORAL.index);

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
	
	
}
