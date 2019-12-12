package com.amwater.mysource.dto;
public enum ArticleSourceType {
	Internet,
	Intranet;
	
	public static ArticleSourceType fromValue(String v) {
		for (ArticleSourceType c : ArticleSourceType.values()) {
			if (c.toString().equals(v)) {
				return c;
			}
		}
		throw new IllegalArgumentException(v);
	}


}
