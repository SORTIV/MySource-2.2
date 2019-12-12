package com.amwater.mysource.transformer;

import com.apporchid.cloudseer.common.transformer.properties.TransformerProperties;

public class ContentMSOEnricherProperties extends TransformerProperties{
	private static final long serialVersionUID = -394031320259015615L;
	public enum EProperty {
		articleClassName, action
	}
	private String articleClassName ;
	
	private String action ;
	
	public String getArticleClassName() {
		return articleClassName;
	}
	public void setArticleClassName(String articleClassName) {
		this.articleClassName = articleClassName;
	}
	
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	
	
	
}
