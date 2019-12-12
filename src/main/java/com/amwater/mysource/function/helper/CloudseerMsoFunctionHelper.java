
package com.amwater.mysource.function.helper;

import org.springframework.stereotype.Component;

import com.amwater.mysource.constants.IMySourcePiplineConstants;
import com.apporchid.cloudseer.common.config.BasePropertyValueFunctionHelper;
import com.apporchid.foundation.common.property.IPropertyValueFunction;

@Component
public class CloudseerMsoFunctionHelper extends BasePropertyValueFunctionHelper implements IMySourcePiplineConstants{

	@Override
	public IPropertyValueFunction getPropertyValueFunction(String functionId) {
		IPropertyValueFunction valueFunction = (inputData, inputVariables) -> {

          
            switch (functionId) {
            case PIPELINE_GET_ARTICLE_TAG_ARTCLE_BY_ID: {
                String articleId = FunctionHelperUtil
                    .getRequestParameterValue("articleId")
                    .toString();

                return PIPELINE_GET_ARTICLE_TAG_ARTCLE_BY_ID_QUERY
                    .replace("{articleId}",
                    		articleId)
                    .toString();
            }
            
		  default:
              return null;
      }
  };
  return valueFunction;
	}

}
