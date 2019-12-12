package com.apporchid.samples.test;

import org.testng.annotations.Test;

import com.amwater.mysource.constants.IMySourcePiplineConstants;
import com.amwater.mysource.pipeline.builder.MySourcePipelineBuilder;
import com.apporchid.pipeline.common.BaseCloudseerTestApplication;

public class IntranetPipelineLoaderTest extends BaseCloudseerTestApplication implements IMySourcePiplineConstants {

	@Test(groups = { "pipelineMain" })
	@Override
	public void setupPipelines() {
		//deploy everything
		deploy(MySourcePipelineBuilder.class);
	}

	@Override
	protected String getDomainId() {
		return DEFAULT_DOMAIN_ID;
	}

	@Override
	protected String getSubDomainId() {
		return DEFAULT_SUB_DOMAIN_ID;
	}
}
