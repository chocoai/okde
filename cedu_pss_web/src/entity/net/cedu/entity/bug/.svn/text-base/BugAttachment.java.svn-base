/**
 * 文件名：BugAttachment.java
 * 包名：net.cedu.entity.bug
 * 工程：cedu_pss_web
 * 功能： TODO /请自行添加
 *
 * 作者：yangdongdong    
 * 日期：2012-1-13 上午07:28:19
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
 * @功能：bug附件
 * 
 * @作者： 杨栋栋
 * @作成时间：2012-1-13 上午07:28:25
 * 
 * @修改者：
 * @修改内容：
 * @修改时间：
 * 
 */
@Entity
@Table(name = "tb_e_bug_attachment")
public class BugAttachment implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	// 主建ID
	private int id;
	@Column(name = "bug_info_id")
	private int bugInfoId;// bug详情ID
	@Column(name = "attachment_path")
	private String attachmentPath;// 附件路径
	@Column(name = "attachment_description")
	private String attachmentDescription;// 附件描述

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getBugInfoId() {
		return bugInfoId;
	}

	public void setBugInfoId(int bugInfoId) {
		this.bugInfoId = bugInfoId;
	}

	public String getAttachmentPath() {
		return attachmentPath;
	}

	public void setAttachmentPath(String attachmentPath) {
		this.attachmentPath = attachmentPath;
	}

	public String getAttachmentDescription() {
		return attachmentDescription;
	}

	public void setAttachmentDescription(String attachmentDescription) {
		this.attachmentDescription = attachmentDescription;
	}

}
