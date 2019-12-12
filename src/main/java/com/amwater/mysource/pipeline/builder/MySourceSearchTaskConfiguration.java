package com.amwater.mysource.pipeline.builder;

import java.util.List;

import com.amwater.mysource.constants.IMySourcePiplineConstants;
import com.apporchid.cloudseer.common.pipeline.tasktype.DatasinkTaskType;
import com.apporchid.cloudseer.common.pipeline.tasktype.DatasourceTaskType;
import com.apporchid.cloudseer.datasink.elasticsearch.ElasticsearchDatasinkProperties;
import com.apporchid.cloudseer.datasink.output.OutputDatasink;
import com.apporchid.cloudseer.datasink.output.OutputDatasinkProperties;
import com.apporchid.cloudseer.datasource.elasticsearch.ElasticsearchDatasource;
import com.apporchid.cloudseer.datasource.elasticsearch.ElasticsearchDatasourceProperties;
import com.apporchid.cloudseer.datasource.elasticsearch.IElasticsearchDatasourceProperties;
import com.apporchid.foundation.pipeline.tasktype.ITaskType;

public class MySourceSearchTaskConfiguration implements IMySourcePiplineConstants{

	public static ITaskType<?, ?>[] getArticleUserTasks() {
		
			DatasourceTaskType task1 = new DatasourceTaskType.Builder().name("Read data")
					.datasourceType(ElasticsearchDatasource.class)
					.datasourcePropertiesType(ElasticsearchDatasourceProperties.class)
					.variableProperty(IElasticsearchDatasourceProperties.EProperty.clusterName.name(), VARIABLE_CLUSTER_NAME)
					.variableProperty(IElasticsearchDatasourceProperties.EProperty.indexName.name(), VARIABLE_INDEX_NAME_INTRANET_ARTICLE)
					.variableProperty(IElasticsearchDatasourceProperties.EProperty.typeName.name(), VARIABLE_TYPE_NAME_INTRANET_USER)
					.property(ElasticsearchDatasinkProperties.EProperty.referenceMSOClass.name(), MSO_REFRENCE_INTRANET_ARTICLE)
					.variableProperty(IElasticsearchDatasourceProperties.EProperty.esCriteria.name(),VARIABLE_ES_CRITERIA)
					.build();

			DatasinkTaskType task2 = new DatasinkTaskType.Builder().name("Output data").datasinkType(OutputDatasink.class)
					.datasinkPropertiesType(OutputDatasinkProperties.class).build();

			ITaskType<?, ?>[] tasks = { task1, task2 };
			return tasks;

	}
	
	
	
	

}
