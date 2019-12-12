package com.amwater.mysource.function.helper;

import com.amwater.mysource.constants.IMySourcePiplineConstants;
import com.apporchid.cloudseer.common.pipeline.BasePipelineLambdaFunctionHelper;
import com.apporchid.cloudseer.datasource.search.util.GoogleSearchResult;
import com.apporchid.common.Variables;
import com.apporchid.foundation.common.ISerializableFunction;
import com.apporchid.foundation.common.IVariables;

public class MySourceLambdaFunctionHelper extends BasePipelineLambdaFunctionHelper implements IMySourcePiplineConstants {

	@Override
	public ISerializableFunction<Object, IVariables> getPipelineInputVariablesFunction(String functionId) {
		switch (functionId) {
		case "articleUrlFn":
			ISerializableFunction<Object, IVariables> valueFunction = (obj) -> {
				String urlPath = null;
				
				if(obj instanceof GoogleSearchResult) {
					urlPath = ((GoogleSearchResult) obj).getSourceUrl();
				}
				IVariables variables = new Variables();
				variables.add("urlPath", urlPath);
				return variables;
			};
			return valueFunction;
		default:
			break;
		}
		return super.getPipelineInputVariablesFunction(functionId);
	}
}
