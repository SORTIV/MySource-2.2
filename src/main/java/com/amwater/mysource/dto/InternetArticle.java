package com.amwater.mysource.dto;

public class InternetArticle extends BaseIntranetArticle {
	
	private static final long serialVersionUID = 8300402424965434920L;
	protected String sourceFeed;
	public InternetArticle() {
		this.articleSourceType = ArticleSourceType.Internet;
	}
}
