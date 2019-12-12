package com.amwater.mysource.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.amwater.mysource.constants.IMySourceCommonConstants;
import com.amwater.mysource.constants.IMySourcePiplineConstants;
import com.amwater.mysource.dto.IntranetArticle;
import com.amwater.mysource.pipeline.builder.MySourceIndexTaskConfiguration;
import com.apporchid.common.Variables;
import com.apporchid.common.advisory.constants.ECloudseerAdvisoryConstants;
import com.apporchid.common.utils.ExceptionUtils;
import com.apporchid.core.common.cache.PipelineCache;
import com.apporchid.foundation.criteria.IElasticsearchCriteria;
import com.apporchid.foundation.mso.IMSODataObject;
import com.apporchid.foundation.pipeline.IPipeline;
import com.apporchid.foundation.pipeline.IPipelineResult;

@Component
public class MySourceContentService implements IMySourcePiplineConstants, IMySourceCommonConstants {
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Value("${elasticsearch.cluster-name}")
	private String clusterName;

	@Value("${intranet.index-name}")
	private String indexNameContent;

	@Value("${intranet.type-name}")
	private String typeNameArticle;

	String[] updateFieldArray = { "content", "tags", "topics", "regions", "slug", "relatedArticles", "pdfUrl",
			"company", "siteCodes", "articleSource", "articleUrl", "bannerUrl", "isFeatured", 
			"lastUpdatedBy", "priority", "summary",  "functionalTopics", "locationTopics",
			"staticContent", "roleIds", "priorityTags", "relatedContents", "subTitle", "thumbNailUrl", "title"};

	public void createArticleIndex(IntranetArticle article) throws Exception {
		Variables variables = new Variables();
		variables.add(VARIABLE_CLUSTER_NAME, clusterName);
		variables.add(VARIABLE_INDEX_NAME_INTRANET_ARTICLE, indexNameContent);
		variables.add(VARIABLE_TYPE_NAME_INTRANET_ARTICLE, typeNameArticle);

		variables.add("articles", article);
		this.executePipeline(PIPELINE_CREATE_INTRANET_ARTICLE, variables);
		
		
	}
	public IMSODataObject getArticleById(String id,String userId) throws Exception {
		Variables variables = new Variables();
		variables.add(VARIABLE_CLUSTER_NAME, clusterName);
		variables.add(VARIABLE_INDEX_NAME_INTRANET_ARTICLE, indexNameContent);
		variables.add(VARIABLE_TYPE_NAME_INTRANET_ARTICLE, typeNameArticle);

		IElasticsearchCriteria searchCriteria = MySourceIndexTaskConfiguration.getElaticsearchCriteria(id);
		variables.add(VARIABLE_ES_CRITERIA, searchCriteria);
		IMSODataObject response = null;
		IPipelineResult result = this.executePipeline(PIPELINE_GET_ARTICLE_BY_ID, variables);
		Object object = result.getOutputObject();
		if(object instanceof IMSODataObject) {
			response = (IMSODataObject) object;
		} else if (object instanceof List) {
			List<IMSODataObject>  res = (List)object;
		}
		
		IMSODataObject[] fieldValue1 = response.getFieldValue("myfiled1", IMSODataObject[].class);
		String fieldValue2 = response.getFieldValue("myfiled2", String.class);
		return response;
	}

	private IPipelineResult executePipeline(String pipelineId, Variables variables) {

		try {
			IPipeline pipeline = PipelineCache.INSTANCE.get(DEFAULT_DOMAIN_ID, DEFAULT_SUB_DOMAIN_ID, pipelineId);
			if (pipeline != null) {
				if (variables != null) {
					pipeline.getPipelineOptions().setInputVariables(variables);
				}
			}
			pipeline.build();
			return pipeline.run();
		} catch (Exception e) {
			ExceptionUtils.throwAOException(e,
					ECloudseerAdvisoryConstants.ERROR_PIPELINE_EXECUTION_FAILED.getErrorCode(), e.getLocalizedMessage(),
					pipelineId);
			return null;
		}

	}
	public void addTagsForArticles(IntranetArticle article) {
		
		Variables variables = new Variables();
		variables.add("articleId", article.getId());
		IPipelineResult result = this.executePipeline(PIPELINE_GET_ARTICLE_TAG_ARTCLE_BY_ID, variables);
		IMSODataObject response = null;
		response = (IMSODataObject) result.getOutputObject();
		
		// TODO Auto-generated method stub
		
	}
		public IntranetArticle updateArticleIndex(IntranetArticle request) throws Exception{
		IntranetArticle response= new IntranetArticle();
		try {
		List<String> updateFields = new ArrayList<>();
		Variables variables = new Variables();
		variables.add(VARIABLE_CLUSTER_NAME, clusterName);
		variables.add(VARIABLE_INDEX_NAME_INTRANET_ARTICLE, indexNameContent);
		variables.add(VARIABLE_TYPE_NAME_INTRANET_ARTICLE, typeNameArticle);
		
		if(request.getUpdateFields() == null) {
			updateFields.addAll(Arrays.asList(updateFieldArray));
		} else {
			updateFields = request.getUpdateFields();
		}
		if(updateFields.contains("content") ){
			if(!updateFields.contains("originalContent")) {
				updateFields.add("originalContent");
			}
		} 
		variables.add("updateFields", updateFields);
		
		if(updateFields.contains("content")) {
			request.setLastUpdatedDate(Calendar.getInstance().getTime());
			updateFields.add("lastUpdatedDate");
		}
		request.setId(request.getId());
		variables.add("articles", request);

		this.executePipeline(PIPELINE_UPDATE_ARTICLE, variables);
		//--call get method
		
		
		response.setStatus(true);
		return response;
		}catch (Exception e) {
			response.setStatus(false);
			response.setMessage(e.getCause().toString());
			logger.error("Exception occured while updating document:-"+e);
			return response;
		}
	}
}
