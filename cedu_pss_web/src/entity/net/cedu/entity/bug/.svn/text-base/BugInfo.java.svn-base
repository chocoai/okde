/**
 * 文件名：BugInfo.java
 * 包名：net.cedu.entity.bug
 * 工程：cedu_pss_web
 * 功能： TODO /请自行添加
 *
 * 作者：yangdongdong    
 * 日期：2012-1-13 上午07:27:17
 *
*/
package net.cedu.entity.bug;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @功能：bug详情
 *
 * @作者： 杨栋栋
 * @作成时间：2012-1-13 上午07:28:42
 *
 * @修改者：
 * @修改内容：
 * @修改时间：
 *
 */
@Entity
@Table(name = "tb_e_bug_info")
public class BugInfo implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	// 主建ID
	private int id;
	
	@Column(name = "bug_status")
	private int bugStatus;//bug状态
	//已提交
	//已分配
	//开始进行
	//已解决
	//没有错误
	//重开
	
	
	//优先级
	//提交人
	//提交时间
	//当前进行人
	//分配时间
	//开始进行时间
	//结束时间(包括没有错误，已解决)
	//父级ID
	
	//bug路径 如：子系统》模块》权限
	//bug描述
}
