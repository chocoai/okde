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
 * @功能：预报名推送模板
 *
 * @作者： 谭必庆
 * @作成时间：2012-4-8 上午10:19:18
 *
 * @修改者：
 * @修改内容：
 * @修改时间：
 *
 */
@Entity
@Table(name = "tb_e_person_tool_yubaoming")
public class DuibiStudentYuBaoMing implements Serializable {
	private static final long serialVersionUID = -5580641356580222834L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	// Excel序号
	transient private String serialNumber;// 序号
	
	// 序号
	@Column(name = "xuhao")
	@ExcelAnnotation(exportName = "序号")
	@SortChineseAnnotation(sort = true)
	private String xuhao;
	
	// 日期
	@Column(name = "riqi")
	@ExcelAnnotation(exportName = "日期")
	private Date riqi;
	
	// 呼叫中心来源
	@Column(name = "hjzx_from")
	@ExcelAnnotation(exportName = "呼叫中心来源")
	private String huJiaoZhongxinLaiYuan;
	
	// 学生姓名
	@Column(name = "name")
	@ExcelAnnotation(exportName = "姓名")
	@SortChineseAnnotation(sort = true)
	private String name;
	
	// 电话
	@Column(name = "dianhua")
	@ExcelAnnotation(exportName = "电话")
	private String shouJiHaoma;
	
	// 申请就读学习中心
	@Column(name = "sqjd_branch")
	@ExcelAnnotation(exportName = "申请就读学习中心")
	private String shenFengZhengHaoMa;
	
	// 报名院校
	@Column(name = "sch_name")
	@ExcelAnnotation(exportName = "报名院校")
	private String baoMingYuanXiao;
	
	// 报名专业
	@Column(name = "zhuanye")
	@ExcelAnnotation(exportName = "报名专业")
	private String baoMingZhuanYe;
	
	// 参与层次
	@Column(name = "cengci")
	@ExcelAnnotation(exportName = "参与层次")
	private String canYuCengCi;
	
	// 推送至学习中习
	@Column(name = "tsz_branch")
	@ExcelAnnotation(exportName = "推送至学习中习")
	private String tuiSongZhiBranch;
	
	
	// 呼叫中心备注
	@Column(name = "hjzx_remark")
	@ExcelAnnotation(exportName = "呼叫中心备注")
	private String huJiaoBranchRemark;
	
	// 状态
	@Column(name = "status")
	@ExcelAnnotation(exportName = "状态")
	private String status;
	
	// 学习中心备注
	@Column(name = "branch_remark")
	@ExcelAnnotation(exportName = "备注")
	private String branchRemark;
	
	// 座机号码
	@Column(name = "zuoji_haoma")
	@ExcelAnnotation(exportName = "座机号码")
	private String zuojiHaoMa;
	
	// 身份证号码
	@Column(name = "id_card")
	@ExcelAnnotation(exportName = "身份证号码")
	private String idCard;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getXuhao() {
		return xuhao;
	}

	public void setXuhao(String xuhao) {
		this.xuhao = xuhao;
	}

	public Date getRiqi() {
		return riqi;
	}

	public void setRiqi(Date riqi) {
		this.riqi = riqi;
	}

	public String getHuJiaoZhongxinLaiYuan() {
		return huJiaoZhongxinLaiYuan;
	}

	public void setHuJiaoZhongxinLaiYuan(String huJiaoZhongxinLaiYuan) {
		this.huJiaoZhongxinLaiYuan = huJiaoZhongxinLaiYuan;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getShouJiHaoma() {
		return shouJiHaoma;
	}

	public void setShouJiHaoma(String shouJiHaoma) {
		this.shouJiHaoma = shouJiHaoma;
	}

	public String getShenFengZhengHaoMa() {
		return shenFengZhengHaoMa;
	}

	public void setShenFengZhengHaoMa(String shenFengZhengHaoMa) {
		this.shenFengZhengHaoMa = shenFengZhengHaoMa;
	}

	public String getBaoMingYuanXiao() {
		return baoMingYuanXiao;
	}

	public void setBaoMingYuanXiao(String baoMingYuanXiao) {
		this.baoMingYuanXiao = baoMingYuanXiao;
	}

	public String getBaoMingZhuanYe() {
		return baoMingZhuanYe;
	}

	public void setBaoMingZhuanYe(String baoMingZhuanYe) {
		this.baoMingZhuanYe = baoMingZhuanYe;
	}

	public String getCanYuCengCi() {
		return canYuCengCi;
	}

	public void setCanYuCengCi(String canYuCengCi) {
		this.canYuCengCi = canYuCengCi;
	}

	public String getTuiSongZhiBranch() {
		return tuiSongZhiBranch;
	}

	public void setTuiSongZhiBranch(String tuiSongZhiBranch) {
		this.tuiSongZhiBranch = tuiSongZhiBranch;
	}

	public String getHuJiaoBranchRemark() {
		return huJiaoBranchRemark;
	}

	public void setHuJiaoBranchRemark(String huJiaoBranchRemark) {
		this.huJiaoBranchRemark = huJiaoBranchRemark;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getBranchRemark() {
		return branchRemark;
	}

	public void setBranchRemark(String branchRemark) {
		this.branchRemark = branchRemark;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getZuojiHaoMa() {
		return zuojiHaoMa;
	}

	public void setZuojiHaoMa(String zuojiHaoMa) {
		this.zuojiHaoMa = zuojiHaoMa;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
}
