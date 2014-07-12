package net.cedu.entity.persontool;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import net.cedu.common.file.excel.ExcelAnnotation;
import net.cedu.common.hibernate.SortChineseAnnotation;

/**
 * 
 * @功能：公服平台导出模板
 *
 * @作者： 谭必庆
 * @作成时间：2012-4-8 上午10:19:55
 *
 * @修改者：
 * @修改内容：
 * @修改时间：
 *
 */
@Entity
@Table(name = "tb_e_person_tool_gongfu")
public class DuibiStudentGongfu implements Serializable {
	private static final long serialVersionUID = -393211662892102765L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	// Excel序号
	transient private String serialNumber;// 序号
	
	// 学生姓名
	@Column(name = "name")
	@ExcelAnnotation(exportName = "姓名")
	@SortChineseAnnotation(sort = true)
	private String name;
	
	// 身份证号码
	@Column(name = "id_card")
	@ExcelAnnotation(exportName = "身份证号码")
	private String shenFengZhengHaoMa;
	
	// 院校名称
	@Column(name = "sch_name")
	@ExcelAnnotation(exportName = "院校名称")
	private String yuanXiaoMingCheng;
	
	// 专业
	@Column(name = "zhuanye")
	@ExcelAnnotation(exportName = "专业")
	private String zhuanYe;
	
	// 层次
	@Column(name = "cengci")
	@ExcelAnnotation(exportName = "层次")
	private String cengCi;
	
	// 招生批次
	@Column(name = "zs_batch")
	@ExcelAnnotation(exportName = "招生批次")
	private String zhaoShengPiCi;
	
	// 学生状态
	@Column(name = "stu_status")
	@ExcelAnnotation(exportName = "学生状态")
	private String xueShengZhuangTai;
	
	// 监控状态
	@Column(name = "jk_status")
	@ExcelAnnotation(exportName = "监控状态")
	private String jianKongZhuangTai;
	
	// 学习中心
	@Column(name = "branch")
	@ExcelAnnotation(exportName = "学习中心")
	private String xueXiZhongXing;
	
	// 数据来源
	@Column(name = "data_from")
	@ExcelAnnotation(exportName = "数据来源")
	private String shuJuLaiYuan;
	
	// 招生途径
	@Column(name = "zs_tj")
	@ExcelAnnotation(exportName = "招生途径")
	private String zhaoShengTuJing;
	
	// 市场途径
	@Column(name = "sc_tj")
	@ExcelAnnotation(exportName = "市场途径")
	private String shiChangTuJing;
	

	// 手机号码
	@Column(name = "mobile")
	@ExcelAnnotation(exportName = "手机号码")
	private String shouJiHaoma;
	
	// 座机号码
	@Column(name = "tel")
	@ExcelAnnotation(exportName = "座机号码")
	private String zuoJiHaoMa;
	
	// 当前跟进人
	@Column(name = "dq_gjr")
	@ExcelAnnotation(exportName = "当前跟进人")
	private String genJinRen;

	// 已缴纳报名费
	@Column(name = "yjnbmf")
	@ExcelAnnotation(exportName = "报名费缴纳金额")
	private String yiJiaoNaBaoMingFei;
	
	// 已缴纳测试费
	@Column(name = "yjncsf")
	@ExcelAnnotation(exportName = "测试费缴纳金额")
	private String yiJiaoNaCeShiFei;
	
	// 已缴纳学费
	@Column(name = "yjnxf")
	@ExcelAnnotation(exportName = "学费缴纳金额")
	private String yiJiaoNaXueFei;
	
	// 已缴纳教材费
	@Column(name = "yjnjcf")
	@ExcelAnnotation(exportName = "教材费缴纳金额")
	private String yiJiaoNaJiaoCaiFei;
	
	//监控结果
	@Column(name = "jk_jg")
	@ExcelAnnotation(exportName = "监控结果")
	private String jianKongJieGuo;
	
	//回访内容
	@Column(name = "hf_content")
	@ExcelAnnotation(exportName = "回访内容")
	private String huiFangNeiRong;
	
	//日期
	@Column(name = "riqi")
	@ExcelAnnotation(exportName = "日期")
	private Date riQi;
	
	//呼叫中心来源
	@Column(name = "hjzx_from")
	@ExcelAnnotation(exportName = "呼叫中心来源")
	private String huJiaoZhongXinFrom;
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getShenFengZhengHaoMa() {
		return shenFengZhengHaoMa;
	}

	public void setShenFengZhengHaoMa(String shenFengZhengHaoMa) {
		this.shenFengZhengHaoMa = shenFengZhengHaoMa;
	}

	public String getYuanXiaoMingCheng() {
		return yuanXiaoMingCheng;
	}

	public void setYuanXiaoMingCheng(String yuanXiaoMingCheng) {
		this.yuanXiaoMingCheng = yuanXiaoMingCheng;
	}

	public String getZhuanYe() {
		return zhuanYe;
	}

	public void setZhuanYe(String zhuanYe) {
		this.zhuanYe = zhuanYe;
	}

	public String getCengCi() {
		return cengCi;
	}

	public void setCengCi(String cengCi) {
		this.cengCi = cengCi;
	}

	public String getZhaoShengPiCi() {
		return zhaoShengPiCi;
	}

	public void setZhaoShengPiCi(String zhaoShengPiCi) {
		this.zhaoShengPiCi = zhaoShengPiCi;
	}

	public String getXueShengZhuangTai() {
		return xueShengZhuangTai;
	}

	public void setXueShengZhuangTai(String xueShengZhuangTai) {
		this.xueShengZhuangTai = xueShengZhuangTai;
	}

	public String getJianKongZhuangTai() {
		return jianKongZhuangTai;
	}

	public void setJianKongZhuangTai(String jianKongZhuangTai) {
		this.jianKongZhuangTai = jianKongZhuangTai;
	}

	public String getXueXiZhongXing() {
		return xueXiZhongXing;
	}

	public void setXueXiZhongXing(String xueXiZhongXing) {
		this.xueXiZhongXing = xueXiZhongXing;
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

	public String getGenJinRen() {
		return genJinRen;
	}

	public void setGenJinRen(String genJinRen) {
		this.genJinRen = genJinRen;
	}

	public String getYiJiaoNaBaoMingFei() {
		return yiJiaoNaBaoMingFei;
	}

	public void setYiJiaoNaBaoMingFei(String yiJiaoNaBaoMingFei) {
		this.yiJiaoNaBaoMingFei = yiJiaoNaBaoMingFei;
	}

	public String getYiJiaoNaCeShiFei() {
		return yiJiaoNaCeShiFei;
	}

	public void setYiJiaoNaCeShiFei(String yiJiaoNaCeShiFei) {
		this.yiJiaoNaCeShiFei = yiJiaoNaCeShiFei;
	}

	public String getYiJiaoNaXueFei() {
		return yiJiaoNaXueFei;
	}

	public void setYiJiaoNaXueFei(String yiJiaoNaXueFei) {
		this.yiJiaoNaXueFei = yiJiaoNaXueFei;
	}

	public String getYiJiaoNaJiaoCaiFei() {
		return yiJiaoNaJiaoCaiFei;
	}

	public void setYiJiaoNaJiaoCaiFei(String yiJiaoNaJiaoCaiFei) {
		this.yiJiaoNaJiaoCaiFei = yiJiaoNaJiaoCaiFei;
	}

	public String getJianKongJieGuo() {
		return jianKongJieGuo;
	}

	public void setJianKongJieGuo(String jianKongJieGuo) {
		this.jianKongJieGuo = jianKongJieGuo;
	}

	public String getHuiFangNeiRong() {
		return huiFangNeiRong;
	}

	public void setHuiFangNeiRong(String huiFangNeiRong) {
		this.huiFangNeiRong = huiFangNeiRong;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public Date getRiQi() {
		return riQi;
	}

	public void setRiQi(Date riQi) {
		this.riQi = riQi;
	}

	public String getHuJiaoZhongXinFrom() {
		return huJiaoZhongXinFrom;
	}

	public void setHuJiaoZhongXinFrom(String huJiaoZhongXinFrom) {
		this.huJiaoZhongXinFrom = huJiaoZhongXinFrom;
	}
}
