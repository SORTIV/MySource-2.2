package com.amwater.mysource.service;

import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.amwater.mysource.constants.IMySourcePiplineConstants;
import com.amwater.mysource.pipeline.builder.MySourceIndexTaskConfiguration;
import com.apporchid.common.Variables;
import com.apporchid.foundation.criteria.IElasticsearchCriteria;
import com.apporchid.foundation.mso.IMSODataObject;
import com.apporchid.foundation.pipeline.IPipelineResult;

public class UserIndexingService implements IMySourcePiplineConstants{
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Value("${elasticsearch.cluster-name}")
	private String clusterName;

	@Value("${intranet.index-name}")
	private String indexNameContent;
	
	@Value("${user.type-name}")
	private String userTypeName;
	
	@Autowired
	IAwCommonService commonService;
	
	public Set<String> getUserLocationInterests(String userId) {
		IMSODataObject userDetails = this.findById(userId);
		
		Set<String> interestAndLocations = new HashSet<>();
		/*if (null != userDetails && null != userDetails.getInterests() && !userDetails.getInterests().isEmpty()) {
			for (String interest : userDetails.getInterests()) {
				if (interest.startsWith("Business and Locations/")) {
					interestAndLocations.add(interest);
				}
			}
		}*/

		return interestAndLocations;
	}

	private IMSODataObject findById(String userId) {
		
		Variables variables = new Variables();
		variables.add(VARIABLE_CLUSTER_NAME, clusterName);
		variables.add(VARIABLE_INDEX_NAME_INTRANET_ARTICLE, indexNameContent);
		variables.add(VARIABLE_TYPE_NAME_INTRANET_USER, userTypeName);

		IElasticsearchCriteria searchCriteria = MySourceIndexTaskConfiguration.getElaticsearchCriteria(userId);
		variables.add(VARIABLE_ES_CRITERIA, searchCriteria);
		IMSODataObject response = null;
		IPipelineResult result = commonService.executePipeline(PIPELINE_GET_ARTICLE_USER_BY_ID, variables);
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
		
		return null;
		
	}

	
	
	
	
	
	

}
