package com.amwater.mysource.dto;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;

@ApiModel(value="PageRequest", description="Model for pagination")
public class PageRequest implements Serializable {
	
	private static final long serialVersionUID = -2103919294994741548L;

	private int pageNumber;
	private int size;
	public PageRequest() {
		
	}

	public PageRequest(int pageNumber,int size) {
		this.pageNumber = pageNumber;
		this.size = size;
	}
	
	public int getPageNumber() {
		return pageNumber;
	}
	public int getSize() {
		return size;
	}
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}
	public void setSize(int size) {
		this.size = size;
	}
	
	
}