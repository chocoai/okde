package net.cedu.model.finance;

import net.cedu.common.file.excel.ExcelAnnotation;

/**
 * 充值金额导出
 * @author xiao
 *
 */
public class ExportStuAccountAmTemplate {

	// 年
	@ExcelAnnotation(exportName = "年")
	private String nian;
	// 月
	@ExcelAnnotation(exportName = "月")
	private String yue;
	// 日
	@ExcelAnnotation(exportName = "日")
	private String ri;
	// 学习中心
	@ExcelAnnotation(exportName = "学习中心")
	private String xueXiZhongXing;
	// 录入者姓名
	@ExcelAnnotation(exportName = "录入者姓名")
	private String luRuZheXingMing;
	// 学生姓名
	@ExcelAnnotation(exportName = "学生姓名")
	private String xueShengXingMing;
	// 招生批次
	@ExcelAnnotation(exportName = "招生批次")
	private String zhaoShengPiCi;
	// 院校名称
	@ExcelAnnotation(exportName = "院校名称")
	private String yuanXiaoMingCheng;
	// 层次
	@ExcelAnnotation(exportName = "层次")
	private String cengCi;
	// 专业
	@ExcelAnnotation(exportName = "专业")
	private String zhuanYe;
	// 数据来源
	@ExcelAnnotation(exportName = "数据来源")
	private String shuJuLaiYuan;
	// 招生途径
	@ExcelAnnotation(exportName = "招生途径")
	private String zhaoShengTuJing;
	// 市场途径
	@ExcelAnnotation(exportName = "市场途径")
	private String shiChangTuJing;
	// 学生状态
	@ExcelAnnotation(exportName = "学生状态")
	private String xueShengZhuangTai;
	// 身份证号码
	@ExcelAnnotation(exportName = "身份证号码")
	private String shenFengZhengHaoMa;
	// 手机号码
	@ExcelAnnotation(exportName = "手机号码")
	private String shouJiHaoma;
	// 座机号码
	@ExcelAnnotation(exportName = "座机号码")
	private String zuoJiHaoMa;
	// 费用科目
	@ExcelAnnotation(exportName = "费用科目")
	private String feiYongKeMu;
	// 缴费方式
	//@ExcelAnnotation(exportName = "缴费方式")
	//private String jiaoFeiFangShi;
	
	// 类型
	@ExcelAnnotation(exportName = "类型")
	private String leiXing;
	
	// 充值帐户金额
	@ExcelAnnotation(exportName = "充值金额")
	private String chongZhiJinE;
	
	// 收据号码
	@ExcelAnnotation(exportName = "收据号码")
	private String shouJuHaoMa;
	
	// 缴费单号
	@ExcelAnnotation(exportName = "缴费单号")
	private String jiaoFeiDanHao;

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

	public String getXueXiZhongXing() {
		return xueXiZhongXing;
	}

	public void setXueXiZhongXing(String xueXiZhongXing) {
		this.xueXiZhongXing = xueXiZhongXing;
	}

	public String getLuRuZheXingMing() {
		return luRuZheXingMing;
	}

	public void setLuRuZheXingMing(String luRuZheXingMing) {
		this.luRuZheXingMing = luRuZheXingMing;
	}

	public String getXueShengXingMing() {
		return xueShengXingMing;
	}

	public void setXueShengXingMing(String xueShengXingMing) {
		this.xueShengXingMing = xueShengXingMing;
	}

	public String getZhaoShengPiCi() {
		return zhaoShengPiCi;
	}

	public void setZhaoShengPiCi(String zhaoShengPiCi) {
		this.zhaoShengPiCi = zhaoShengPiCi;
	}

	public String getYuanXiaoMingCheng() {
		return yuanXiaoMingCheng;
	}

	public void setYuanXiaoMingCheng(String yuanXiaoMingCheng) {
		this.yuanXiaoMingCheng = yuanXiaoMingCheng;
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

	public String getShuJuLaiYuan() {
		return shuJuLaiYuan;
	}

	public void setShuJuLaiYuan(String shuJuLaiYuan) {
		this.shuJuLaiYuan = shuJuLaiYuan;
	}

	public String getZhaoShengTuJing() {
		return zhaoShengTuJing;
	}

	public void setZhaoShengTuJing(String zhaoShengTuJing) {
		this.zhaoShengTuJing = zhaoShengTuJing;
	}

	public String getShiChangTuJing() {
		return shiChangTuJing;
	}

	public void setShiChangTuJing(String shiChangTuJing) {
		this.shiChangTuJing = shiChangTuJing;
	}

	public String getXueShengZhuangTai() {
		return xueShengZhuangTai;
	}

	public void setXueShengZhuangTai(String xueShengZhuangTai) {
		this.xueShengZhuangTai = xueShengZhuangTai;
	}

	public String getShenFengZhengHaoMa() {
		return shenFengZhengHaoMa;
	}

	public void setShenFengZhengHaoMa(String shenFengZhengHaoMa) {
		this.shenFengZhengHaoMa = shenFengZhengHaoMa;
	}

	public String getShouJiHaoma() {
		return shouJiHaoma;
	}

	public void setShouJiHaoma(String shouJiHaoma) {
		this.shouJiHaoma = shouJiHaoma;
	}

	public String getZuoJiHaoMa() {
		return zuoJiHaoMa;
	}

	public void setZuoJiHaoMa(String zuoJiHaoMa) {
		this.zuoJiHaoMa = zuoJiHaoMa;
	}

	public String getFeiYongKeMu() {
		return feiYongKeMu;
	}

	public void setFeiYongKeMu(String feiYongKeMu) {
		this.feiYongKeMu = feiYongKeMu;
	}

	//public String getJiaoFeiFangShi() {
	//	return jiaoFeiFangShi;
	//}

	//public void setJiaoFeiFangShi(String jiaoFeiFangShi) {
	//	this.jiaoFeiFangShi = jiaoFeiFangShi;
	//}

	
	public String getJiaoFeiDanHao() {
		return jiaoFeiDanHao;
	}

	public String getLeiXing() {
		return leiXing;
	}

	public void setLeiXing(String leiXing) {
		this.leiXing = leiXing;
	}

	public void setJiaoFeiDanHao(String jiaoFeiDanHao) {
		this.jiaoFeiDanHao = jiaoFeiDanHao;
	}

	public String getChongZhiJinE() {
		return chongZhiJinE;
	}

	public void setChongZhiJinE(String chongZhiJinE) {
		this.chongZhiJinE = chongZhiJinE;
	}

	public String getShouJuHaoMa() {
		return shouJuHaoMa;
	}

	public void setShouJuHaoMa(String shouJuHaoMa) {
		this.shouJuHaoMa = shouJuHaoMa;
	}

	

}
