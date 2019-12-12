package com.amwater.mysource.pipeline.builder;

import com.amwater.mysource.constants.IMySourcePiplineConstants;
import com.amwater.mysource.dto.Article;
import com.amwater.mysource.transformer.ContentMSOEnricher;
import com.amwater.mysource.transformer.ContentMSOEnricherProperties;
import com.apporchid.cloudseer.common.pipeline.tasktype.DatasinkTaskType;
import com.apporchid.cloudseer.common.pipeline.tasktype.DatasourceTaskType;
import com.apporchid.cloudseer.common.pipeline.tasktype.TransformerTaskType;
import com.apporchid.cloudseer.datasink.elasticsearch.ElasticsearchDatasink;
import com.apporchid.cloudseer.datasink.elasticsearch.ElasticsearchDatasinkProperties;
import com.apporchid.cloudseer.datasink.output.OutputDatasink;
import com.apporchid.cloudseer.datasink.output.OutputDatasinkProperties;
import com.apporchid.cloudseer.datasource.elasticsearch.ElasticsearchDatasource;
import com.apporchid.cloudseer.datasource.elasticsearch.ElasticsearchDatasourceProperties;
import com.apporchid.cloudseer.datasource.elasticsearch.IElasticsearchDatasourceProperties;
import com.apporchid.cloudseer.datasource.other.AnyCollectionDatasource;
import com.apporchid.cloudseer.datasource.other.AnyCollectionDatasourceProperties;
import com.apporchid.criteria.StringCriteria;
import com.apporchid.foundation.criteria.IElasticsearchCriteria;
import com.apporchid.foundation.criteria.operator.EStringOperator;
import com.apporchid.foundation.mso.common.EDataUpdateType;
import com.apporchid.foundation.pipeline.tasktype.ITaskType;

public class MySourceIndexTaskConfiguration implements IMySourcePiplineConstants {

	public static ITaskType<?, ?>[] articleIndexTasks() {
		DatasourceTaskType task1 = new DatasourceTaskType.Builder().name("Read data")
				.datasourceType(AnyCollectionDatasource.class)
				.datasourcePropertiesType(AnyCollectionDatasourceProperties.class)
				.variableProperty(AnyCollectionDatasourceProperties.EProperty.dataCollection.name(), "articles")
				.build();

		TransformerTaskType task2 = new TransformerTaskType.Builder().name("MSO Datatype Enricher")
				.transformerPropertiesType(ContentMSOEnricherProperties.class)
				.property(ContentMSOEnricherProperties.EProperty.articleClassName.name(), MSO_REFRENCE_INTRANET_ARTICLE)
				.property(ContentMSOEnricherProperties.EProperty.action.name(), "create")
				.transformerType(ContentMSOEnricher.class).build();

		DatasinkTaskType task3 = new DatasinkTaskType.Builder().name("Write data")
				.datasinkType(ElasticsearchDatasink.class).datasinkPropertiesType(ElasticsearchDatasinkProperties.class)
				.variableProperty(ElasticsearchDatasinkProperties.EProperty.clusterName.name(), VARIABLE_CLUSTER_NAME)
				.variableProperty(ElasticsearchDatasinkProperties.EProperty.indexName.name(), VARIABLE_INDEX_NAME_INTRANET_ARTICLE)
				.variableProperty(ElasticsearchDatasinkProperties.EProperty.typeName.name(), VARIABLE_TYPE_NAME_INTRANET_ARTICLE)
				.property(ElasticsearchDatasinkProperties.EProperty.referenceMSOClass.name(), MSO_REFRENCE_INTRANET_ARTICLE)
				.property(ElasticsearchDatasinkProperties.EProperty.dataUpdateType.name(), EDataUpdateType.APPEND)
				.property(ElasticsearchDatasinkProperties.EProperty.idField.name(), "id").build();

		ITaskType<?, ?>[] tasks = { task1, task2, task3 };
		return tasks;
	}
	
	public static ITaskType<?, ?>[] updateArticleIndexTasks() {
		DatasourceTaskType task1 = new DatasourceTaskType.Builder().name("Read data")
				.datasourceType(AnyCollectionDatasource.class)
				.datasourcePropertiesType(AnyCollectionDatasourceProperties.class)
				.variableProperty(AnyCollectionDatasourceProperties.EProperty.dataCollection.name(), "articles")
				.build();

		TransformerTaskType task2 = new TransformerTaskType.Builder().name("MSO Datatype Enricher")
				.transformerPropertiesType(ContentMSOEnricherProperties.class)
				.property(ContentMSOEnricherProperties.EProperty.articleClassName.name(), MSO_REFRENCE_INTRANET_ARTICLE)
				.property(ContentMSOEnricherProperties.EProperty.action.name(), "update")
				.transformerType(ContentMSOEnricher.class).build();
		
		DatasinkTaskType task3 = new DatasinkTaskType.Builder().name("Write data")
				.datasinkType(ElasticsearchDatasink.class)
				.datasinkPropertiesType(ElasticsearchDatasinkProperties.class)
				.variableProperty(ElasticsearchDatasinkProperties.EProperty.clusterName.name(), VARIABLE_CLUSTER_NAME)
				.variableProperty(ElasticsearchDatasinkProperties.EProperty.indexName.name(), VARIABLE_INDEX_NAME_INTRANET_ARTICLE)
				.variableProperty(ElasticsearchDatasinkProperties.EProperty.typeName.name(), VARIABLE_TYPE_NAME_INTRANET_ARTICLE)
				.property(ElasticsearchDatasinkProperties.EProperty.dataUpdateType.name(), EDataUpdateType.UPDATE)
				.property(ElasticsearchDatasinkProperties.EProperty.idField.name(), "id")
				.variableProperty(ElasticsearchDatasinkProperties.EProperty.updateFields.name(), "updateFields")
				.build();

		ITaskType<?, ?>[] tasks = { task1, task2, task3 };
		return tasks;
	}
	public static ITaskType<?, ?>[] getArticleIndexTasks() {
		DatasourceTaskType task1 = new DatasourceTaskType.Builder().name("Read data")
				.datasourceType(ElasticsearchDatasource.class)
				.datasourcePropertiesType(ElasticsearchDatasourceProperties.class)
				.variableProperty(IElasticsearchDatasourceProperties.EProperty.clusterName.name(), VARIABLE_CLUSTER_NAME)
				.variableProperty(IElasticsearchDatasourceProperties.EProperty.indexName.name(), VARIABLE_INDEX_NAME_INTRANET_ARTICLE)
				.variableProperty(IElasticsearchDatasourceProperties.EProperty.typeName.name(), VARIABLE_TYPE_NAME_INTRANET_ARTICLE)
				.property(ElasticsearchDatasinkProperties.EProperty.referenceMSOClass.name(), MSO_REFRENCE_INTRANET_ARTICLE)
				.variableProperty(IElasticsearchDatasourceProperties.EProperty.esCriteria.name(),VARIABLE_ES_CRITERIA)
				.build();

		DatasinkTaskType task2 = new DatasinkTaskType.Builder().name("Output data").datasinkType(OutputDatasink.class)
				.datasinkPropertiesType(OutputDatasinkProperties.class).build();

		ITaskType<?, ?>[] tasks = { task1, task2 };
		return tasks;
	}

	public static IElasticsearchCriteria getElaticsearchCriteria(String id) {
		return new StringCriteria("_id", EStringOperator.Equals, id);
	}

/*	public static List<ITaskType<?, ?>> getArticleTagsTasks() {
		
		  DatasourceTaskType getArticleTagsData = getDBDatasourceTask(
				  PIPELINE_GET_ARTICLE_TAG_ARTCLE_BY_ID);
		
		TransformerTaskType task2 = new TransformerTaskType.Builder().name("MSO Datatype Enricher")
				.transformerPropertiesType(ContentMSOEnricherProperties.class)
				.property(ContentMSOEnricherProperties.EProperty.articleClassName.name(), articleClass.getName())
				.property(ContentMSOEnricherProperties.EProperty.action.name(), "update")
				.transformerType(ContentMSOEnricher.class).build();

		
		return null;
	}

	private static DatasourceTaskType getDBDatasourceTask(String pipelineGetArticleTagArtcleById) {
		  DatasourceTaskType datasourceTask = new DatasourceTaskType.Builder().name("Read data")
	                .datasourceType(RelationalDBDatasource.class)
	                .datasourcePropertiesType(RelationalDBDatasourceProperties.class)
	                .property(RelationalDBDatasourceProperties.EProperty.sqlDatasourceName.name(), CLOUDSEER_PIPELINES_DATASOURCE_NAME)
	                .functionProperty(RelationalDBDatasourceProperties.EProperty.sqlQuery.name(),
	                        dbTaskBuilderHelper().getDefaultFunctionHelper(), pipelineGetArticleTagArtcleById)
	                .outputDataType(EDataType.MSO_DATA).build();
	        return datasourceTask;
	}*/
}
