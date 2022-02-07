package com.teamAlpha.bookHub.communication.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.UpdateTimestamp;

import com.teamAlpha.bookHub.common.entity.Schemas;

@Entity
@Table(name = "attachment", schema = Schemas.COMMUNICATION)
public class Attachment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "a_id")
	private Integer attachmentId;

	@Column(name = "attachment_type_id")
	private Integer attachmentTypeId;

	@Column(name = "uploaded_by")
	private String uploadedBy;

	@Column(name = "uploaded_on")
	@UpdateTimestamp
	private Date uploadedOn;

	@Column(name = "shop_id")
	private Integer shopId;

	@Column(name = "file_hash")
	private String fileHash;

	public Integer getAttachmentId() {
		return attachmentId;
	}

	public void setAttachmentId(Integer attachmentId) {
		this.attachmentId = attachmentId;
	}

	public Integer getAttachmentTypeId() {
		return attachmentTypeId;
	}

	public void setAttachmentTypeId(Integer attachmentTypeId) {
		this.attachmentTypeId = attachmentTypeId;
	}

	public String getUploadedBy() {
		return uploadedBy;
	}

	public void setUploadedBy(String uploadedBy) {
		this.uploadedBy = uploadedBy;
	}

	public Date getUploadedOn() {
		return uploadedOn;
	}

	public void setUploadedOn(Date uploadedOn) {
		this.uploadedOn = uploadedOn;
	}

	public Integer getShopId() {
		return shopId;
	}

	public void setShopId(Integer shopId) {
		this.shopId = shopId;
	}

	public String getFileHash() {
		return fileHash;
	}

	public void setFileHash(String fileHash) {
		this.fileHash = fileHash;
	}

	@Override
	public String toString() {
		return "Attachment [attachmentId=" + attachmentId + ", attachmentTypeId=" + attachmentTypeId + ", uploadedBy="
				+ uploadedBy + ", uploadedOn=" + uploadedOn + ", shopId=" + shopId + ", fileHash=" + fileHash + "]";
	}

}
