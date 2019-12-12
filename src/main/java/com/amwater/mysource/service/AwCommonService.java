package com.amwater.mysource.service;

import com.amwater.mysource.constants.IMySourceCommonConstants;
import com.apporchid.common.Variables;
import com.apporchid.common.advisory.constants.ECloudseerAdvisoryConstants;
import com.apporchid.common.utils.ExceptionUtils;
import com.apporchid.core.common.cache.PipelineCache;
import com.apporchid.foundation.pipeline.IPipeline;
import com.apporchid.foundation.pipeline.IPipelineResult;

public class AwCommonService implements IAwCommonService, IMySourceCommonConstants{
	
	
	@Override
	public IPipelineResult executePipeline(String pipelineId, Variables variables) {

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
