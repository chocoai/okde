package net.cedu.action.enrollment;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.academy.AcademyBiz;
import net.cedu.biz.basesetting.LevelBiz;
import net.cedu.biz.enrollment.AcademyEnrollBatchBiz;
import net.cedu.biz.enrollment.AcademyMajorBiz;
import net.cedu.common.file.excel.ExcelExport;
import net.cedu.entity.academy.Academy;
import net.cedu.entity.basesetting.Level;
import net.cedu.entity.enrollment.AcademyEnrollBatch;
import net.cedu.entity.enrollment.AcademyLevel;
import net.cedu.model.academy.ViewAcademyLevelAndMajor;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * 
 * @功能：下载每个院校下批次授权的层次，专业
 * 
 * @作者： 杨栋栋
 * @作成时间：2011-11-18 下午02:46:22
 * 
 * @修改者：
 * @修改内容：
 * @修改时间：
 * 
 */
public class DownLoadViewAcademyLevelAndMajorAction extends BaseAction {
	@Autowired
	private AcademyBiz academyBiz;// 院校
	@Autowired
	private AcademyEnrollBatchBiz academyEnrollBatchBiz;// 院校招生批次
	@Autowired
	private AcademyMajorBiz academyMajorBiz;// 专业(院校批次)
	@Autowired
	private LevelBiz levelBiz;// 层次(基础)
	private List<AcademyEnrollBatch> academyEnrollBatchList;// 院校招生批次列表
	private int academyId;// 院校ID

	@Override
	public String execute() throws Exception {
		try {

			Academy academy = academyBiz.findAcademyById(academyId);
			if (academy != null) {

				// 根据院校ID获取该院校对应的全部院校招生批次
				academyEnrollBatchList = academyEnrollBatchBiz
						.findBatchNotInFinishedByAcademyId(academyId);
				// 接收处理后的 专业(院校)名称
				String names = "";
				List<AcademyLevel> academyLeveList = new ArrayList<AcademyLevel>();
				if (academyEnrollBatchList != null
						&& academyEnrollBatchList.size() > 0) {
					// 根据获取的院校批次ID
					// 循环获取其对应的层次(院校批次),然后循环赋给List<AcademyEnrollBatch>
					academyEnrollBatchList = academyEnrollBatchBiz
							.addAcademyLevelforAcademyEnrollBatch(academyEnrollBatchList);

					for (int i = 0; i < academyEnrollBatchList.size(); i++) {
						// 循环获取每个院校批次下的层次List
						academyLeveList = academyEnrollBatchList.get(i)
								.getAcademyLevelList();
						// 调用方法查询层次(院校)对应 层次(基础设置)，并添加到AcademyLevelList里
						levelBiz.addLevelforAcademyLevel(academyLeveList);

						if (academyLeveList != null
								&& academyLeveList.size() > 0) {
							for (int j = 0; j < academyLeveList.size(); j++) {
								// 循环每个院校批次下的层次List,获取其对应的全部专业(院校)名称(处理后的)
								names = academyMajorBiz
										.findMajorNameByLevelId(academyLeveList
												.get(j).getId());
								// 处理后的 专业(院校)名称 循环赋给对应的层次（院校）
								academyLeveList.get(j).setAcademyMajorNames(
										names);

							}
						}
					}
					// 导出的List
					List<ViewAcademyLevelAndMajor> viewAcademyLevelAndMajors = new ArrayList<ViewAcademyLevelAndMajor>();

					for (AcademyEnrollBatch academyEnrollBatch : academyEnrollBatchList) {
						// 院校层次
						List<AcademyLevel> academyLevels = academyEnrollBatch
								.getAcademyLevelList();
						if (academyLevels != null) {
							for (AcademyLevel academyLevel : academyLevels) {
								String academyMajorNames = academyLevel
										.getAcademyMajorNames();
								if (academyMajorNames != null
										&& !"".equals(academyMajorNames)) {
									String[] academyMajorNameArray = academyMajorNames
											.split(",");
									for (String academyMajorName : academyMajorNameArray) {
										Level level = academyLevel.getLevel();
										viewAcademyLevelAndMajors
												.add(new ViewAcademyLevelAndMajor(
														academy.getName(),
														academyEnrollBatch
																.getEnrollmentName(),
														level == null ? ""
																: level.getName(),
														academyMajorName));
									}
								}

							}
						}
					}
					// for (ViewAcademyLevelAndMajor viewAcademyLevelAndMajor :
					// viewAcademyLevelAndMajors) {
					// System.out.println(viewAcademyLevelAndMajor.toString());
					// }

					if (viewAcademyLevelAndMajors != null
							&& viewAcademyLevelAndMajors.size() != 0) {
						
						String r = request.getHeader("User-agent").toLowerCase();
						String u = "";
						if (r.indexOf("mac") != -1) {
							u = "UTF-8";
						} else if (r.indexOf("windows") != -1) {
							u = "gbk";
						} else {
							u = "UTF-8";
						}
						
						OutputStream os = response.getOutputStream();
						response.reset();
						response.setHeader(
								"Content-disposition",
								"attachment; filename="
										+ new String(
												(academy.getName()
														+ ".xls" == null ? ""
														: academy.getName()
																+ ".xls")
														.getBytes(u),
												"ISO-8859-1"));// 设定输出文件头
						response.setContentType("application/msexcel");

						ExcelExport<ViewAcademyLevelAndMajor> ex = new ExcelExport<ViewAcademyLevelAndMajor>();
						ex.exportExcel(academy.getName(),
								viewAcademyLevelAndMajors, os);
						os.close();
						return null;
					}

				}

			}
		} catch (Exception e) {
			e.printStackTrace();

		}
		return null;
	}

	public void setAcademyId(int academyId) {
		this.academyId = academyId;
	}

}
