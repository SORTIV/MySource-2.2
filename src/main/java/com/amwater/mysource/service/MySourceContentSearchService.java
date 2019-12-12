package com.amwater.mysource.service;

import java.util.HashSet;
import java.util.Set;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.amwater.mysource.constants.IMySourcePiplineConstants;
import com.amwater.mysource.dto.SearchRequest;
import com.amwater.mysource.pipeline.builder.MySourceIndexTaskConfiguration;
import com.apporchid.common.Variables;
import com.apporchid.common.advisory.constants.ECloudseerAdvisoryConstants;
import com.apporchid.common.utils.ExceptionUtils;
import com.apporchid.core.common.cache.PipelineCache;
import com.apporchid.criteria.StringCriteria;
import com.apporchid.elasticsearch.dto.SearchResponse;
import com.apporchid.foundation.criteria.IElasticsearchCriteria;
import com.apporchid.foundation.criteria.operator.EStringOperator;
import com.apporchid.foundation.mso.IMSODataObject;
import com.apporchid.foundation.pipeline.IPipeline;
import com.apporchid.foundation.pipeline.IPipelineResult;

@Service
@Transactional
public class MySourceContentSearchService implements IMySourcePiplineConstants{

	@Value("${elasticsearch.cluster-name}")
	private String clusterName;

	@Value("${intranet.index-name}")
	private String indexNameContent;
	
	@Value("${user.type-name}")
	private String userTypeName;
	
	@Inject
	UserIndexingService userIndexingService;
	
	public SearchResponse search(SearchRequest searchRequest) throws Exception {
		searchValidations(searchRequest);
		//String query = synonymService.getSynonym(searchRequest.getQuery());
		//searchRequest.setQuery(query);
		IElasticsearchCriteria request = this.decaySearch(searchRequest);
		
		return null;
	}

	private IElasticsearchCriteria decaySearch(SearchRequest searchRequest) {
		Set<String> userLocations = userIndexingService.getUserLocationInterests(searchRequest.getUserId());
		
		return new StringCriteria("_id", EStringOperator.Equals, searchRequest.getUserId());
	}

	private Set<String> getUserLocationInterests(String userId) {
		//IntranetUser userDetails = this.findById(userId);
		Variables variables = new Variables();
		variables.add(VARIABLE_CLUSTER_NAME, clusterName);
		variables.add(VARIABLE_INDEX_NAME_INTRANET_ARTICLE, indexNameContent);
		variables.add(VARIABLE_TYPE_NAME_INTRANET_USER, userTypeName);

		IElasticsearchCriteria searchCriteria = MySourceIndexTaskConfiguration.getElaticsearchCriteria(userId);
		variables.add(VARIABLE_ES_CRITERIA, searchCriteria);
		IMSODataObject response = null;
		IPipelineResult result = this.executePipeline(PIPELINE_GET_ARTICLE_USER_BY_ID, variables);
		response = (IMSODataObject) result.getOutputObject();
		//IntranetUser userDetails=(IntranetUser) response;
		
		
		Set<String> interestAndLocations = new HashSet<>();
		if (null != response && null != response.getFieldValue("interests") ) {
			System.out.println("User Data"+response);
			System.out.println("User intrests Data"+response.getFieldValue("interests"));
			/*
			 * for (String interest : userDetails.getInterests()) { if
			 * (interest.startsWith("Business and Locations/")) {
			 * interestAndLocations.add(interest); }
			 */
			//}
		}

		return interestAndLocations;
	}

	private void searchValidations(SearchRequest searchRequest) throws Exception {
		if (null != searchRequest) {
			if (StringUtils.isBlank(searchRequest.getSearchId()) && (StringUtils.isBlank(searchRequest.getQuery())
					|| StringUtils.isBlank(searchRequest.getUserId()))) {
				throw new Exception("invalidSearchData");
			}
		}
		
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
	
	
}
