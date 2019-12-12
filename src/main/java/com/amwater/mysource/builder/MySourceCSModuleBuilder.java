package com.amwater.mysource.builder;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.amwater.mysource.pipeline.builder.MySourcePipelineBuilder;
import com.apporchid.cloudseer.config.loader.BaseCloudseerModuleConfigurationBuilder;
import com.apporchid.config.builder.BaseConfigurationBuilder;

@Component
public class MySourceCSModuleBuilder extends BaseCloudseerModuleConfigurationBuilder{

	@Override
	protected List<Class<? extends BaseConfigurationBuilder<?>>> getDependentConfigBuilders() {
		List<Class<? extends BaseConfigurationBuilder<?>>> builders = new ArrayList<>();
		
//		builders.add(RestPipelineBuilder.class);
		builders.add(MySourcePipelineBuilder.class);
		return builders;
	}
}
