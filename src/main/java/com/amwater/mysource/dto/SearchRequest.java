package com.amwater.mysource.dto;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.apporchid.elasticsearch.core.query.SortDirection;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "SearchRequest", description = "Model for searches, supply the search id")
@JsonInclude(Include.NON_NULL)
public class SearchRequest implements Serializable {
	private static final long serialVersionUID = 7882320427529996082L;

	/**
	 * Page Request , set to null if no pagination is required
	 */
	@ApiModelProperty(value = "Pagination Details", dataType = "PageRequest")
	private PageRequest pageRequest;

	/**
	 * Search text
	 */
	@ApiModelProperty(value = "Search Text", dataType = "string")
	private String query;

	/**
	 * Set it to null if it is initial request.
	 */
	@ApiModelProperty(value = "Set it to null if it is initial search request", dataType = "string")
	private String searchId;

	private SortDirection sortDirection;

	private List<String> tags;

	private List<String> type;
	
	private boolean autoCorrect;
	private String location;
	


	private String articleSource;

	/**
	 * Id of the user doing the search
	 */
	@ApiModelProperty(value = "Id of the user who is performing the search", dataType = "String")
	private String userId;

	public PageRequest getPageRequest() {
		return pageRequest;
	}

	public String getQuery() {
		return query;
	}

	public String getSearchId() {
		return searchId;
	}

	public SortDirection getSortDirection() {
		return sortDirection;
	}

	public List<String> getTags() {
		if(tags == null) tags = new ArrayList<String>();
		return tags;
	}


	public String getUserId() {
		return userId;
	}

	public void setPageRequest(PageRequest page) {
		this.pageRequest = page;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public void setSearchId(String searchId) {
		this.searchId = searchId;
	}

	public void setSortDirection(SortDirection sortDirection) {
		this.sortDirection = sortDirection;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}

	public List<String> getType() {
		return type;
	}

	public void setType(List<String> type) {
		this.type = type;
	}

	public boolean isAutoCorrect() {
		return autoCorrect;
	}

	public void setAutoCorrect(boolean autoCorrect) {
		this.autoCorrect = autoCorrect;
	}

	public String getArticleSource() {
		return articleSource;
	}

	public void setArticleSource(String articleSource) {
		this.articleSource = articleSource;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
}

