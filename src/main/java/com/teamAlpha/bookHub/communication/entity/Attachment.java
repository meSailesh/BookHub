package com.teamAlpha.bookHub.communication.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.teamAlpha.bookHub.communication.model.AttachmentTypes;

@Entity
@Table(name="attachment")
public class Attachment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "a_id")
	private Integer attachmentId; 
	
	@Column(name = "attachment_type")
	private Integer attachmentTypeId;
	
	@Column (name ="uploaded_by")
	private String uploadedBy;
	
	@Column (name = "uploaded_on")
	private String uploadedOn; 
	
	@Column (name = "shop_id")
	private Integer shopId; 
	
	@Column (name = "file_hash")
	private String fileHash;
	
	public Integer getAttachmentId() {
		return attachmentId;
	}
	public void setAttachmentId(Integer attachmentId) {
		this.attachmentId = attachmentId;
	}
	public Integer getAttachmentType() {
		return attachmentTypeId;
	}
	public void setAttachmentType(Integer attachmentTypeId) {
		this.attachmentTypeId = attachmentTypeId;
	}
	public String getUploadedBy() {
		return uploadedBy;
	}
	public void setUploadedBy(String uploadedBy) {
		this.uploadedBy = uploadedBy;
	}
	public String getUploadedOn() {
		return uploadedOn;
	}
	public void setUploadedOn(String uploadedOn) {
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
	

}
