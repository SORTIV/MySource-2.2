package com.amwater.mysource.dto;

import java.util.List;

public class IntranetArticle extends BaseIntranetArticle{
	private static final long serialVersionUID = -6461426197513389536L;
	
	private List<String> updateFields;
	private boolean status;
	private String message;
	
	public List<String> getUpdateFields() {
		return updateFields;
	}

	public void setUpdateFields(List<String> updateFields) {
		this.updateFields = updateFields;
	}

	public IntranetArticle() {
		this.articleSourceType = ArticleSourceType.Intranet;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
