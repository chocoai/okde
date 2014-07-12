package net.cedu.model.academy;

import net.cedu.common.file.excel.ExcelAnnotation;

public class ViewAcademyLevelAndMajor {

	@ExcelAnnotation(exportName = "院校名称")
	private String academyName;// 院校名称
	@ExcelAnnotation(exportName = "学籍批次")
	private String academyEnrollBatch;// 学籍批次
	@ExcelAnnotation(exportName = "院校层次")
	private String academyLevel;// 院校层次
	@ExcelAnnotation(exportName = "院校专业")
	private String academyMajor;// 院校专业

	public ViewAcademyLevelAndMajor() {
	}

	public ViewAcademyLevelAndMajor(String academyName,
			String academyEnrollBatch, String academyLevel, String academyMajor) {
		this.academyName = academyName;
		this.academyEnrollBatch = academyEnrollBatch;
		this.academyLevel = academyLevel;
		this.academyMajor = academyMajor;
	}

	public String getAcademyName() {
		return academyName;
	}

	public void setAcademyName(String academyName) {
		this.academyName = academyName;
	}

	public String getAcademyEnrollBatch() {
		return academyEnrollBatch;
	}

	public void setAcademyEnrollBatch(String academyEnrollBatch) {
		this.academyEnrollBatch = academyEnrollBatch;
	}

	public String getAcademyLevel() {
		return academyLevel;
	}

	public void setAcademyLevel(String academyLevel) {
		this.academyLevel = academyLevel;
	}

	public String getAcademyMajor() {
		return academyMajor;
	}

	public void setAcademyMajor(String academyMajor) {
		this.academyMajor = academyMajor;
	}
	@Override
	public String toString() {
		
		return academyName+"_"+academyEnrollBatch+"_"+academyLevel+"_"+academyMajor;
	}
}
