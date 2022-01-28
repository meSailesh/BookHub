package com.teamAlpha.bookHub.communication.entity;

import com.teamAlpha.bookHub.communication.model.AttachmentTypes;


public class Attachment {
	
	private Integer attachmentId; 
	private AttachmentTypes attachmentType; 
	private String uploadedBy;
	private String uploadedOn; 
	private Integer shopId; 
	private String fileHash;
	
	public Integer getAttachmentId() {
		return attachmentId;
	}
	public void setAttachmentId(Integer attachmentId) {
		this.attachmentId = attachmentId;
	}
	public AttachmentTypes getAttachmentType() {
		return attachmentType;
	}
	public void setAttachmentType(AttachmentTypes attachmentType) {
		this.attachmentType = attachmentType;
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
