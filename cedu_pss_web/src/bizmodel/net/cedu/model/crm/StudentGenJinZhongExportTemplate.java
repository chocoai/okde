/**
 * 文件名：StudentGenJinZhongExportTemplate.java
 * 包名：net.cedu.model.crm
 * 工程：cedu_pss_web
 * 功能： TODO /请自行添加
 *
 * 作者：dongminghao
 * 日期：2012-06-21 上午10:23:32
 *
 */
package net.cedu.model.crm;

import net.cedu.common.file.excel.ExcelAnnotation;

/**
 * 跟进中学生导出业务实体
 * 
 * @author dongminghao
 * 
 */
public class StudentGenJinZhongExportTemplate {

	// 序号
	@ExcelAnnotation(exportName = "序号")
	private String xuHao;
	// 姓名
	@ExcelAnnotation(exportName = "姓名")
	private String xingMing;
	// 证件类型
	@ExcelAnnotation(exportName = "证件类型")
	private String zhengJianLeiXing;
	// 证件号
	@ExcelAnnotation(exportName = "证件号")
	private String zhengJianHao;
	// 手机
	@ExcelAnnotation(exportName = "手机")
	private String shouJi;
	// 座机
	@ExcelAnnotation(exportName = "座机")
	private String zuoJi;
	// 状态
	@ExcelAnnotation(exportName = "状态")
	private String zhuangTai;
	// 呼叫等级
	@ExcelAnnotation(exportName = "呼叫等级")
	private String huJiaoDengJi;
	// 跟进次数
	@ExcelAnnotation(exportName = "跟进次数")
	private String genJinCiShu;
	// 报名时间
	@ExcelAnnotation(exportName = "报名时间")
	private String baoMingShiJian;
	// 当前跟进人
	@ExcelAnnotation(exportName = "当前跟进人")
	private String dangQianGenJinRen;
	// 最新跟进时间
	@ExcelAnnotation(exportName = "最新跟进时间")
	private String zuiXinGenJinShiJian;
	// 最新沟通内容
	@ExcelAnnotation(exportName = "最新沟通内容")
	private String zuiXinGouTongNeiRong;
	
	public String getXuHao() {
		return xuHao;
	}
	public void setXuHao(String xuHao) {
		this.xuHao = xuHao;
	}
	public String getXingMing() {
		return xingMing;
	}
	public void setXingMing(String xingMing) {
		this.xingMing = xingMing;
	}
	public String getZhengJianLeiXing() {
		return zhengJianLeiXing;
	}
	public void setZhengJianLeiXing(String zhengJianLeiXing) {
		this.zhengJianLeiXing = zhengJianLeiXing;
	}
	public String getZhengJianHao() {
		return zhengJianHao;
	}
	public void setZhengJianHao(String zhengJianHao) {
		this.zhengJianHao = zhengJianHao;
	}
	public String getShouJi() {
		return shouJi;
	}
	public void setShouJi(String shouJi) {
		this.shouJi = shouJi;
	}
	public String getZuoJi() {
		return zuoJi;
	}
	public void setZuoJi(String zuoJi) {
		this.zuoJi = zuoJi;
	}
	public String getZhuangTai() {
		return zhuangTai;
	}
	public void setZhuangTai(String zhuangTai) {
		this.zhuangTai = zhuangTai;
	}
	public String getHuJiaoDengJi() {
		return huJiaoDengJi;
	}
	public void setHuJiaoDengJi(String huJiaoDengJi) {
		this.huJiaoDengJi = huJiaoDengJi;
	}
	public String getGenJinCiShu() {
		return genJinCiShu;
	}
	public void setGenJinCiShu(String genJinCiShu) {
		this.genJinCiShu = genJinCiShu;
	}
	public String getBaoMingShiJian() {
		return baoMingShiJian;
	}
	public void setBaoMingShiJian(String baoMingShiJian) {
		this.baoMingShiJian = baoMingShiJian;
	}
	public String getDangQianGenJinRen() {
		return dangQianGenJinRen;
	}
	public void setDangQianGenJinRen(String dangQianGenJinRen) {
		this.dangQianGenJinRen = dangQianGenJinRen;
	}
	public String getZuiXinGenJinShiJian() {
		return zuiXinGenJinShiJian;
	}
	public void setZuiXinGenJinShiJian(String zuiXinGenJinShiJian) {
		this.zuiXinGenJinShiJian = zuiXinGenJinShiJian;
	}
	public String getZuiXinGouTongNeiRong() {
		return zuiXinGouTongNeiRong;
	}
	public void setZuiXinGouTongNeiRong(String zuiXinGouTongNeiRong) {
		this.zuiXinGouTongNeiRong = zuiXinGouTongNeiRong;
	}
	
}
