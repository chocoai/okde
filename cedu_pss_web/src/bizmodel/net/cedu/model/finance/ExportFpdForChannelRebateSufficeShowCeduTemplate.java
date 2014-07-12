package net.cedu.model.finance;

import net.cedu.common.file.excel.ExcelAnnotation;

/**
 * 导出招生返款符合未返款查询（总部）
 * 
 * @author dongminghao
 *
 */
public class ExportFpdForChannelRebateSufficeShowCeduTemplate {

	// 缴费日期
	// 年
	@ExcelAnnotation(exportName = "年")
	private String nian;
	// 月
	@ExcelAnnotation(exportName = "月")
	private String yue;
	// 日
	@ExcelAnnotation(exportName = "日")
	private String ri;
	// 缴费单号
	@ExcelAnnotation(exportName = "缴费单号")
	private String jiaoFeiDanHao;
	// 姓名
	@ExcelAnnotation(exportName = "姓名")
	private String xingMing;
	// 身份证号
	@ExcelAnnotation(exportName = "身份证号")
	private String shenFenZhengHao;
	// 手机
	@ExcelAnnotation(exportName = "手机")
	private String shouJi;
	// 座机
	@ExcelAnnotation(exportName = "座机")
	private String zuoJi;
	// 学习中心
	@ExcelAnnotation(exportName = "学习中心")
	private String xueXiZhongXin;
	// 院校
	@ExcelAnnotation(exportName = "院校")
	private String yuanXiao;
	// 批次
	@ExcelAnnotation(exportName = "批次")
	private String piCi;
	// 层次
	@ExcelAnnotation(exportName = "层次")
	private String cengCi;
	// 专业
	@ExcelAnnotation(exportName = "专业")
	private String zhuanYe;
	// 招生途径
	@ExcelAnnotation(exportName = "招生途径")
	private String zhaoShengTuJing;
	// 合作方
	@ExcelAnnotation(exportName = "合作方")
	private String heZuoFang;
	// 市场途径
	@ExcelAnnotation(exportName = "市场途径")
	private String shiChangTuJing;
	// 数据来源
	@ExcelAnnotation(exportName = "数据来源")
	private String shuJuLaiYuan;
	// 缴费科目
	@ExcelAnnotation(exportName = "缴费科目")
	private String jiaoFeiKeMu;
	// 实缴金额
	@ExcelAnnotation(exportName = "实缴金额")
	private String shiJiaoJinE;
	// 缴费方式
	@ExcelAnnotation(exportName = "缴费方式")
	private String jiaoFeiFangShi;
	// 状态
	@ExcelAnnotation(exportName = "状态")
	private String zhuangTai;
	// 总部确认时间
	@ExcelAnnotation(exportName = "总部确认时间")
	private String zongBuQueRenShiJian;
	// 开课状态
	@ExcelAnnotation(exportName = "开课状态")
	private String kaiKeZhuangTai;
	// 招生来源复核
	@ExcelAnnotation(exportName = "招生来源复核")
	private String zhaoShengLaiYuanFuHe;
	// 备注
	@ExcelAnnotation(exportName = "备注")
	private String beiZhu;
	// 监控结果
	@ExcelAnnotation(exportName = "监控结果")
	private String jianKongJieGuo;
	// 监控状态
	@ExcelAnnotation(exportName = "监控状态")
	private String jianKongZhuangTai;
	// 回访人
	@ExcelAnnotation(exportName = "回访人")
	private String huiFangRen;
	// 回访内容
	@ExcelAnnotation(exportName = "回访内容")
	private String huiFangNeiRong;
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
	public String getXingMing() {
		return xingMing;
	}
	public void setXingMing(String xingMing) {
		this.xingMing = xingMing;
	}
	public String getShenFenZhengHao() {
		return shenFenZhengHao;
	}
	public void setShenFenZhengHao(String shenFenZhengHao) {
		this.shenFenZhengHao = shenFenZhengHao;
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
	public String getXueXiZhongXin() {
		return xueXiZhongXin;
	}
	public void setXueXiZhongXin(String xueXiZhongXin) {
		this.xueXiZhongXin = xueXiZhongXin;
	}
	public String getYuanXiao() {
		return yuanXiao;
	}
	public void setYuanXiao(String yuanXiao) {
		this.yuanXiao = yuanXiao;
	}
	public String getPiCi() {
		return piCi;
	}
	public void setPiCi(String piCi) {
		this.piCi = piCi;
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
	public String getZhaoShengTuJing() {
		return zhaoShengTuJing;
	}
	public void setZhaoShengTuJing(String zhaoShengTuJing) {
		this.zhaoShengTuJing = zhaoShengTuJing;
	}
	public String getHeZuoFang() {
		return heZuoFang;
	}
	public void setHeZuoFang(String heZuoFang) {
		this.heZuoFang = heZuoFang;
	}
	public String getShiChangTuJing() {
		return shiChangTuJing;
	}
	public void setShiChangTuJing(String shiChangTuJing) {
		this.shiChangTuJing = shiChangTuJing;
	}
	public String getShuJuLaiYuan() {
		return shuJuLaiYuan;
	}
	public void setShuJuLaiYuan(String shuJuLaiYuan) {
		this.shuJuLaiYuan = shuJuLaiYuan;
	}
	public String getJiaoFeiKeMu() {
		return jiaoFeiKeMu;
	}
	public void setJiaoFeiKeMu(String jiaoFeiKeMu) {
		this.jiaoFeiKeMu = jiaoFeiKeMu;
	}
	public String getShiJiaoJinE() {
		return shiJiaoJinE;
	}
	public void setShiJiaoJinE(String shiJiaoJinE) {
		this.shiJiaoJinE = shiJiaoJinE;
	}
	public String getJiaoFeiFangShi() {
		return jiaoFeiFangShi;
	}
	public void setJiaoFeiFangShi(String jiaoFeiFangShi) {
		this.jiaoFeiFangShi = jiaoFeiFangShi;
	}
	public String getZhuangTai() {
		return zhuangTai;
	}
	public void setZhuangTai(String zhuangTai) {
		this.zhuangTai = zhuangTai;
	}
	public String getZongBuQueRenShiJian() {
		return zongBuQueRenShiJian;
	}
	public void setZongBuQueRenShiJian(String zongBuQueRenShiJian) {
		this.zongBuQueRenShiJian = zongBuQueRenShiJian;
	}
	public String getJianKongJieGuo() {
		return jianKongJieGuo;
	}
	public void setJianKongJieGuo(String jianKongJieGuo) {
		this.jianKongJieGuo = jianKongJieGuo;
	}
	public String getJianKongZhuangTai() {
		return jianKongZhuangTai;
	}
	public void setJianKongZhuangTai(String jianKongZhuangTai) {
		this.jianKongZhuangTai = jianKongZhuangTai;
	}
	public String getHuiFangRen() {
		return huiFangRen;
	}
	public void setHuiFangRen(String huiFangRen) {
		this.huiFangRen = huiFangRen;
	}
	public String getHuiFangNeiRong() {
		return huiFangNeiRong;
	}
	public void setHuiFangNeiRong(String huiFangNeiRong) {
		this.huiFangNeiRong = huiFangNeiRong;
	}
	public String getKaiKeZhuangTai() {
		return kaiKeZhuangTai;
	}
	public void setKaiKeZhuangTai(String kaiKeZhuangTai) {
		this.kaiKeZhuangTai = kaiKeZhuangTai;
	}
	public String getZhaoShengLaiYuanFuHe() {
		return zhaoShengLaiYuanFuHe;
	}
	public void setZhaoShengLaiYuanFuHe(String zhaoShengLaiYuanFuHe) {
		this.zhaoShengLaiYuanFuHe = zhaoShengLaiYuanFuHe;
	}
	public String getBeiZhu() {
		return beiZhu;
	}
	public void setBeiZhu(String beiZhu) {
		this.beiZhu = beiZhu;
	}
	
	
}
