package com.amwater.mysource.dto;

public enum ArticleSource {
	Internet,
	Intranet;
	
	public static ArticleSource fromValue(String v) {
		for (ArticleSource c : ArticleSource.values()) {
			if (c.toString().equals(v)) {
				return c;
			}
		}
		throw new IllegalArgumentException(v);
	}


}
