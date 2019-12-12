package com.amwater.mysource.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.constraints.NotNull;

import com.apporchid.core.mso.BaseMSODataObject;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public  class Article implements Serializable {

	private static final long serialVersionUID = 182357268400813959L;

	protected Map<String, Object> additionalData;

	protected ArticleSource articleSource;

	protected String articleUrl;

	protected String bannerUrl;
	
	protected long bookmarks;

	protected List<String> topics;
	
	protected List<String> locationTopics;
	
	protected List<String> functionalTopics;
	
	protected long comments;
	
	protected String sourceFeed;
	
	protected long likes;

	protected String originalContent;
	
	protected String content;
	
	protected Date expirationDate;

	protected String id;

	protected boolean isFeatured;

	protected String lastUpdatedBy;

	protected Date lastUpdatedDate;

	@NotNull
	protected String publishedBy;

	@NotNull
	protected Date publishedDate;
	
	protected ArticlePriority priority;

	protected String summary;
	
	protected List<String> relatedContents;
	
	protected List<String> roleIds;
	
	protected List<String> tags;
	
	protected List<String> suggestedTags;

	protected String thumbNailUrl;
	
	protected long shares;
	
	protected String source;
	
	protected List<String> priorityTags;
	
	protected List<String> categoryParts;
	
	protected Date lastActivityTime;

	@NotNull
	protected String title;
	
	protected String subTitle;

	@NotNull
	protected ArticleType type;
	
	protected boolean displayOverlay;

	protected long views;
	
	protected boolean staticContent;
	


	

	public Article(){
		this.articleSource = ArticleSource.Intranet;
	}

	public Map<String, Object> getAdditionalData() {
		if (additionalData == null) {
			additionalData = new HashMap<String, Object>();
		}
		return additionalData;
	}
	
	public String getOriginalContent() {
		return originalContent;
	}

	public void setOriginalContent(String originalContent) {
		this.originalContent = originalContent;
	}

	public long getBookmarks() {
		return bookmarks;
	}

	public List<String> getTopics() {
		if(topics == null) topics = new ArrayList<String>();
		return topics;
	}

	public long getComments() {
		return comments;
	}

	public String getContent() {
		return content;
	}

	

	public Date getExpirationDate() {
		return expirationDate;
	}

	public String getId() {
		return id;
	}

	public String getLastUpdatedBy() {
		return lastUpdatedBy;
	}

	

	public long getLikes() {
		return likes;
	}

	public ArticlePriority getPriority() {
		if(this.priority == null)
			this.priority = ArticlePriority.NoPriority;
		return priority;
	}

	public String getPublishedBy() {
		return publishedBy;
	}

	

	public List<String> getRelatedContents() {
		if(relatedContents == null){
			this.relatedContents = new ArrayList<String>();
		}
		return relatedContents;
	}

	public List<String> getRoleIds() {
		if (roleIds == null) {
			roleIds = new ArrayList<String>();
		}
		return roleIds;
	}

	public long getShares() {
		return shares;
	}

	public String getSource() {
		return source;
	}

	public String getSummary() {
		return summary;
	}

	public List<String> getTags() {
		if (tags == null) {
			tags = new ArrayList<String>();
		}
		return tags;
	}


	public String getTitle() {
		return title;
	}

	public ArticleType getType() {
		return type;
	}

	public long getViews() {
		return views;
	}

	public boolean isFeatured() {
		return isFeatured;
	}

	public void setAdditionalData(Map<String, Object> additionalData) {
		this.additionalData = additionalData;
	}

	public String getBannerUrl() {
		return bannerUrl;
	}

	public void setBannerUrl(String bannerUrl) {
		this.bannerUrl = bannerUrl;
	}

	public void setBookmarks(long bookmarks) {
		this.bookmarks = bookmarks;
	}



	public void setComments(long comments) {
		this.comments = comments;
	}

	public void setContent(String content) {
		this.content = content;
	}

	

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	public void setFeatured(boolean isFeatured) {
		this.isFeatured = isFeatured;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setLastUpdatedBy(String lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}

	

	public void setLikes(long likes) {
		this.likes = likes;
	}

	public void setPriority(ArticlePriority priority) {
		this.priority = priority;
	}

	public void setPublishedBy(String publishedBy) {
		this.publishedBy = publishedBy;
	}

	public void setPublishedOn(Date publishedDate) {
		this.publishedDate = publishedDate;
	}

	public void setRelatedContents(List<String> relatedContents) {
		if (relatedContents == null) {
			relatedContents = new ArrayList<String>();
		}
		this.relatedContents = relatedContents;
	}

	public void setRoleIds(List<String> roleIds) {
		this.roleIds = roleIds;
	}

	public void setShares(long shares) {
		this.shares = shares;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}



	public String getThumbNailUrl() {
		return thumbNailUrl;
	}

	public void setThumbNailUrl(String thumbNailUrl) {
		this.thumbNailUrl = thumbNailUrl;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setType(ArticleType type) {
		this.type = type;
	}

	public void setViews(long views) {
		this.views = views;
	}

	public Date getLastActivityTime() {
		return lastActivityTime;
	}

	public void setLastActivityTime(Date lastActivityTime) {
		this.lastActivityTime = lastActivityTime;
	}

	public ArticleSource getArticleSource() {
		if(this.articleSource == null) {
			this.articleSource = ArticleSource.Intranet;
		}
		return articleSource;
	}

	public boolean isDisplayOverlay() {
		return displayOverlay;
	}

	public void setDisplayOverlay(boolean displayOverlay) {
		this.displayOverlay = displayOverlay;
	}

	public List<String> getPriorityTags() {
		if(null == this.priorityTags ){
			this.priorityTags = new ArrayList<>();
		}
		return priorityTags;
	}

	public void setPriorityTags(List<String> priorityTags) {
		this.priorityTags = priorityTags;
	}

	
	public Set<String> getAlltags() {
		Set<String> allTags = new HashSet<>();
		allTags.addAll(this.getTags());
		allTags.addAll(this.getPriorityTags());
		return allTags;
	}

	public List<String> getCategoryParts() {
		if(categoryParts == null) {
			categoryParts = new ArrayList<>();
		}
		return categoryParts;
	}

	public void setCategoryParts(List<String> categoryParts) {
		this.categoryParts = categoryParts;
	}

	public String getSubTitle() {
		return subTitle;
	}

	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}

	public List<String> getLocationTopics() {
		if(locationTopics == null) locationTopics = new ArrayList<String>();
		return locationTopics;
	}

	public void setLocationTopics(List<String> locationTopics) {
		this.locationTopics = locationTopics;
	}

	public List<String> getFunctionalTopics() {
		if(functionalTopics == null) 
			functionalTopics = new ArrayList<>();
		return functionalTopics;
	}

	public void setFunctionalTopics(List<String> functionalCategories) {		
		this.functionalTopics = functionalCategories;
	}

	public void setArticleSource(ArticleSource articleSource) {
			this.articleSource = articleSource;
	}
	public void setArticleSource(String articleSource) {
		this.articleSource = ArticleSource.valueOf(articleSource);
	}

	public String getArticleUrl() {
		return articleUrl;
	}

	public void setArticleUrl(String articleUrl) {
		this.articleUrl = articleUrl;
	}

	public String getSourceFeed() {
		return sourceFeed;
	}

	public void setSourceFeed(String sourceFeed) {
		this.sourceFeed = sourceFeed;
	}

	public List<String> getSuggestedTags() {
		return suggestedTags;
	}

	public void setSuggestedTags(List<String> suggestedTags) {
		this.suggestedTags = suggestedTags;
	}

	public boolean isStaticContent() {
		return staticContent;
	}

	public void setStaticContent(boolean staticContent) {
		this.staticContent = staticContent;
	}

	public Date getLastUpdatedDate() {
		return lastUpdatedDate;
	}

	public void setLastUpdatedDate(Date lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
	}

	public Date getPublishedDate() {
		return publishedDate;
	}

	public void setPublishedDate(Date publishedDate) {
		this.publishedDate = publishedDate;
	}

	public void setTopics(List<String> topics) {
		this.topics = topics;
	}



}
