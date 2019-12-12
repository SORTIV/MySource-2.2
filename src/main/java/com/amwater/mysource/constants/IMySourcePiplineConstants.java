package com.amwater.mysource.constants;

public interface IMySourcePiplineConstants extends IMySourceCommonConstants {
	
	/*Elastic Search Config Constants*/
	public static final String VARIABLE_CLUSTER_NAME = "ClusterName";
	public static final String VARIABLE_INDEX_NAME_INTRANET_ARTICLE = "IndexNameArticle";
	public static final String VARIABLE_TYPE_NAME_INTRANET_ARTICLE = "TypeNameArticle";
	public static final String VARIABLE_TYPE_NAME_INTRANET_USER = "TypeNameUser";
	public static final String VARIABLE_ES_CRITERIA = "ElasticsearchCriteria";
	
	/*Pipline MSO Constants*/
	public static final String MSO_REFRENCE_INTRANET_ARTICLE= "com.amwater.mysource.IntranetArticle";
	
	
	/*Pipline ID Constants*/
	public static final String PIPELINE_CREATE_INTRANET_ARTICLE = "PipelineCreateIntranetArticle";
	public static final String PIPELINE_GET_ARTICLE_BY_ID = "get_article_by_id";
	public static final String PIPELINE_UPDATE_ARTICLE = "pipeline_article_update";
	public static final String PIPELINE_DELETE_ARTICLE = "pipeline_article_delete";
	public static final String PIPELINE_SEARCH_ARTICLE = "pipeline_article_search";
	public static final String PIPELINE_GET_ARTICLE_TAG_ARTCLE_BY_ID = "get_intranet_article_tags";
	public static final String PIPELINE_GET_ARTICLE_USER_BY_ID = "get_articleuser_by_id";
	
	/*Pipline SQL Queries Constants*/
	public static final String PIPELINE_GET_ARTICLE_TAG_ARTCLE_BY_ID_QUERY = "Select * from intranet_article_tag where article_id={articleId}";
}
