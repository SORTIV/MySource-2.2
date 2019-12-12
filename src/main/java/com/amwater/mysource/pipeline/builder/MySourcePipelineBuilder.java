package com.amwater.mysource.pipeline.builder;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.amwater.mysource.constants.IMySourcePiplineConstants;
import com.amwater.mysource.dto.IntranetArticle;
import com.amwater.mysource.function.helper.CloudseerMsoFunctionHelper;
import com.apporchid.cloudseer.config.builder.BasePipelineConfigurationBuilder;
import com.apporchid.cloudseer.datasource.html.ExternalArticleDatasourceProperties.EArticleExtractorType;
import com.apporchid.common.Variables;
import com.apporchid.foundation.common.IVariables;
import com.apporchid.foundation.constants.ECacheType;
import com.apporchid.foundation.pipeline.IPipeline;
import com.apporchid.foundation.pipeline.IPipelineLambdaFunctionHelper;
import com.apporchid.foundation.pipeline.event.IPipelineEventListener;

@Component
public class MySourcePipelineBuilder extends BasePipelineConfigurationBuilder implements IMySourcePiplineConstants {

	public MySourcePipelineBuilder() {
		super(DEFAULT_DOMAIN_ID, DEFAULT_SUB_DOMAIN_ID);
		dbTaskBuilderHelper().setDefaultDatasourceName(DEFAULT_SQL_DATASOURCE_NAME);
		searchTaskBuilderHelper().setMaxRecordsToFetch(50);

		otherTaskBuilderHelper().setDefaultArticleExtractorType(EArticleExtractorType.Article);
		dbTaskBuilderHelper().setDefaultDatasourceName(CLOUDSEER_PIPELINES_DATASOURCE_NAME);
		//Same lamda function helper class would be used for all pipelines to pass input params to db functions.
		dbTaskBuilderHelper().setDefaultFunctionHelper(CloudseerMsoFunctionHelper.class);
	}

	@Override
	protected List<IPipeline> getPipelines() {
		List<IPipeline> pipelineList = new ArrayList<>();
		
		/* Article Create Update API Start*/
		IPipeline pipeline = createPipeline(PIPELINE_CREATE_INTRANET_ARTICLE,MySourceIndexTaskConfiguration.articleIndexTasks());
		pipelineList.add(pipeline);
		
		// Article Update Pipeline
		pipeline = createPipeline(PIPELINE_UPDATE_ARTICLE,MySourceIndexTaskConfiguration.updateArticleIndexTasks());
		pipelineList.add(pipeline);
		
		// Article Get Pipeline
		pipeline = createPipeline(PIPELINE_GET_ARTICLE_BY_ID,MySourceIndexTaskConfiguration.getArticleIndexTasks());
		pipelineList.add(pipeline);
		
		// PIPELINE GET ARTICLE TAG ARTCL BY ID
		pipeline = createPipeline(PIPELINE_GET_ARTICLE_TAG_ARTCLE_BY_ID,dbTaskBuilderHelper().getDBDatasourceTasks(PIPELINE_GET_ARTICLE_TAG_ARTCLE_BY_ID,false));
		pipelineList.add(pipeline);
	//	pipelinesList.add(createPipeline(USER_ACTIVITY_QUESTION_MAPPING_PIPELINE,dbTaskBuilderHelper().getDBDatasourceTasks(USER_ACTIVITY_QUESTION_MAPPING_PIPELINE,false)));
		
		// Article Bulk index For tags Pipeline
		/*
		 * pipeline =
		 * createPipeline(PIPELINE_CREATE_ARTICLE_TAG,MySourceIndexTaskConfiguration.
		 * getArticleIndexTasks()); pipelineList.add(pipeline);
		 */
		/* Article Create Update API End*/
		
		/* Search API Start*/
		// Intranet USER Get Pipeline
				pipeline = createPipeline(PIPELINE_GET_ARTICLE_USER_BY_ID,MySourceSearchTaskConfiguration.getArticleUserTasks());
				pipelineList.add(pipeline);
		
		/* Search API End*/
		
		return pipelineList;
	}
	@Override
	protected Class<? extends IPipelineEventListener> getPipelineEventListener(String pipelineName) {

		return super.getPipelineEventListener(pipelineName);
	}

	@Override
	protected String[] getPipelinesToRun() {
		return new String[] { getSystemPipelineId(PIPELINE_NAME_EXCEL_BASED_MUTLI_MSO_CREATOR) };
	}

	@Override
	protected IVariables getInputVariables(String pipelineName, int index) {
		if (index == 0) {
			IVariables inputVariables = new Variables();
			inputVariables.add("msoDirectoryPath", "/intranet/mso/");
			return inputVariables;
		}
		return null;
	}

	@Override
	protected ECacheType getCacheType(String pipelineName) {
		return super.getCacheType(pipelineName);
	}






	@Override
	protected Class<? extends IPipelineLambdaFunctionHelper> getLamdaFunctionHelperClass(String pipelineName) {
		return super.getLamdaFunctionHelperClass(pipelineName);
	}
}
