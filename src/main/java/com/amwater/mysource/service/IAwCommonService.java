package com.amwater.mysource.service;

import com.apporchid.common.Variables;
import com.apporchid.foundation.pipeline.IPipelineResult;

public interface IAwCommonService {

	public IPipelineResult executePipeline(String pipelineId, Variables variables);
}
