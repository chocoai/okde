package net.cedu.model.finance;

import net.cedu.common.file.excel.ExcelAnnotation;

/**
 * 导出预缴费单
 * 
 * @author xiao
 *
 */
public class ExportFeePaymentTemplate 
{
	
	// 年
	@ExcelAnnotation(exportName = "年")
	private String nian;
	// 月
	@ExcelAnnotation(exportName = "月")
	private String yue;
	// 日
	@ExcelAnnotation(exportName = "日")
	private String ri;
	// 缴费单Id
	@ExcelAnnotation(exportName = "缴费单Id")
	private String jiaoFeiDanId;
	// 缴费单号
	@ExcelAnnotation(exportName = "缴费单号")
	private String jiaoFeiDanHao;
	// 学生姓名
	@ExcelAnnotation(exportName = "学生姓名")
	private String xueShengXingMing;
	// 学习中心
	@ExcelAnnotation(exportName = "学习中心")
	private String xueXiZhongXing;
	// 院校名称
	@ExcelAnnotation(exportName = "院校名称")
	private String yuanXiaoMingCheng;
	// 招生批次
	@ExcelAnnotation(exportName = "招生批次")
	private String zhaoShengPiCi;
	// 层次
	@ExcelAnnotation(exportName = "层次")
	private String cengCi;
	// 专业
	@ExcelAnnotation(exportName = "专业")
	private String zhuanYe;
	// 身份证号码
	@ExcelAnnotation(exportName = "身份证号码")
	private String shenFengZhengHaoMa;
	// 缴费方式
	@ExcelAnnotation(exportName = "缴费方式")
	private String jiaoFeiFangShi;
	// 费用科目
	@ExcelAnnotation(exportName = "费用科目")
	private String feiYongKeMu;
	// 缴费金额
	@ExcelAnnotation(exportName = "缴费金额")
	private String jiaoFeiJinE;
	// 充值金额
	@ExcelAnnotation(exportName = "充值金额")
	private String chongZhiJinE;
	// 总金额
	@ExcelAnnotation(exportName = "总金额")
	private String zongJinE;
	// 收据号码
	@ExcelAnnotation(exportName = "收据号码")
	private String shouJuHaoMa;	
	// 缴费单类别
	@ExcelAnnotation(exportName = "缴费单类别")
	private String jiaoFeiDanLeiBie;
	// 缴费单状态
	@ExcelAnnotation(exportName = "缴费单状态")
	private String jiaoFeiDanZhuangTai;
	public String getNian() {
		return nian;
	}
	public void setNian(String nian) {
		this.nian = nian;
	}
	public String getYue() {
		return yue;
	}
	public void setYue(String yue) {
		this.yue = yue;
	}
	public String getRi() {
		return ri;
	}
	public void setRi(String ri) {
		this.ri = ri;
	}
	public String getJiaoFeiDanHao() {
		return jiaoFeiDanHao;
	}
	public void setJiaoFeiDanHao(String jiaoFeiDanHao) {
		this.jiaoFeiDanHao = jiaoFeiDanHao;
	}
	public String getXueShengXingMing() {
		return xueShengXingMing;
	}
	public void setXueShengXingMing(String xueShengXingMing) {
		this.xueShengXingMing = xueShengXingMing;
	}
	public String getXueXiZhongXing() {
		return xueXiZhongXing;
	}
	public void setXueXiZhongXing(String xueXiZhongXing) {
		this.xueXiZhongXing = xueXiZhongXing;
	}
	public String getYuanXiaoMingCheng() {
		return yuanXiaoMingCheng;
	}
	public void setYuanXiaoMingCheng(String yuanXiaoMingCheng) {
		this.yuanXiaoMingCheng = yuanXiaoMingCheng;
	}
	public String getZhaoShengPiCi() {
		return zhaoShengPiCi;
	}
	public void setZhaoShengPiCi(String zhaoShengPiCi) {
		this.zhaoShengPiCi = zhaoShengPiCi;
	}
	public String getCengCi() {
		return cengCi;
	}
	public void setCengCi(String cengCi) {
		this.cengCi = cengCi;
	}
	public String getZhuanYe() {
		return zhuanYe;
	}
	public void setZhuanYe(String zhuanYe) {
		this.zhuanYe = zhuanYe;
	}
	public String getShenFengZhengHaoMa() {
		return shenFengZhengHaoMa;
	}
	public void setShenFengZhengHaoMa(String shenFengZhengHaoMa) {
		this.shenFengZhengHaoMa = shenFengZhengHaoMa;
	}
	public String getJiaoFeiFangShi() {
		return jiaoFeiFangShi;
	}
	public void setJiaoFeiFangShi(String jiaoFeiFangShi) {
		this.jiaoFeiFangShi = jiaoFeiFangShi;
	}
	public String getFeiYongKeMu() {
		return feiYongKeMu;
	}
	public void setFeiYongKeMu(String feiYongKeMu) {
		this.feiYongKeMu = feiYongKeMu;
	}
	public String getJiaoFeiJinE() {
		return jiaoFeiJinE;
	}
	public void setJiaoFeiJinE(String jiaoFeiJinE) {
		this.jiaoFeiJinE = jiaoFeiJinE;
	}
	public String getChongZhiJinE() {
		return chongZhiJinE;
	}
	public void setChongZhiJinE(String chongZhiJinE) {
		this.chongZhiJinE = chongZhiJinE;
	}
	public String getZongJinE() {
		return zongJinE;
	}
	public void setZongJinE(String zongJinE) {
		this.zongJinE = zongJinE;
	}
	public String getShouJuHaoMa() {
		return shouJuHaoMa;
	}
	public void setShouJuHaoMa(String shouJuHaoMa) {
		this.shouJuHaoMa = shouJuHaoMa;
	}
	public String getJiaoFeiDanLeiBie() {
		return jiaoFeiDanLeiBie;
	}
	public void setJiaoFeiDanLeiBie(String jiaoFeiDanLeiBie) {
		this.jiaoFeiDanLeiBie = jiaoFeiDanLeiBie;
	}
	public String getJiaoFeiDanZhuangTai() {
		return jiaoFeiDanZhuangTai;
	}
	public void setJiaoFeiDanZhuangTai(String jiaoFeiDanZhuangTai) {
		this.jiaoFeiDanZhuangTai = jiaoFeiDanZhuangTai;
	}
	public String getJiaoFeiDanId() {
		return jiaoFeiDanId;
	}
	public void setJiaoFeiDanId(String jiaoFeiDanId) {
		this.jiaoFeiDanId = jiaoFeiDanId;
	}
	
	

}
