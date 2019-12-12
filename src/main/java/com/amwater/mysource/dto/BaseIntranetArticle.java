package com.amwater.mysource.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public  abstract class BaseIntranetArticle extends Article {

	private static final long serialVersionUID = -1487546453861489211L;
	protected String source;
	protected long bookmarks;
	protected long likes;
	protected long shares;
	protected long comments;
	protected ArticleSourceType articleSourceType;
	protected Date lastActivityTime;

	public boolean isDisplayOverlay() {
		return displayOverlay;
	}

	public void setDisplayOverlay(boolean displayOverlay) {
		this.displayOverlay = displayOverlay;
	}
	
	public List<String> getRelatedContents() {
		if (relatedContents == null) {
			this.relatedContents = new ArrayList<String>();
		}
		return relatedContents;
	}

	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}

	public ArticleSourceType getArticleSourceType() {
		if (this.articleSourceType == null) {
			this.articleSourceType = ArticleSourceType.Intranet;
		}
		return articleSourceType;
	}

	public long getBookmarks() {
		return bookmarks;
	}


	public long getComments() {
		return comments;
	}

	public long getLikes() {
		return likes;
	}

	public long getShares() {
		return shares;
	}
	
	public void setBookmarks(long bookmarks) {
		this.bookmarks = bookmarks;
	}
	public void setComments(long comments) {
		this.comments = comments;
	}
	
	public void setLikes(long likes) {
		this.likes = likes;
	}

	public void setRelatedContents(List<String> relatedContents) {
		if (relatedContents == null) {
			relatedContents = new ArrayList<String>();
		}
		this.relatedContents = relatedContents;
	}

	public void setShares(long shares) {
		this.shares = shares;
	}

	public Date getLastActivityTime() {
		return lastActivityTime;
	}

	public void setLastActivityTime(Date lastActivityTime) {
		this.lastActivityTime = lastActivityTime;
	}
}
